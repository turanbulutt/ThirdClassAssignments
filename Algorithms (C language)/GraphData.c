//Ahmet Turan Bulut 2315174
#include <stdio.h>
#include <stdlib.h>

struct graphHead{
	struct graphVertex *first;
	int count; //counts number of vertex
	struct graphArc *firstArc;
};

struct graphVertex{
	struct graphVertex *next;
	char data; //Data to save in a node/vertex
	int index; //to understand which scanpath you own
};

struct graphArc{
	struct graphVertex *destination;
	float weight;
	struct graphArc *next;
};

//to print scanpant relations i use that structer
struct printArrayStructer{
    int from;
    int to;
    float weight;
};

//return min value
int min(int x, int y,int z)
{
    if(x<=y && x<=z)
        return x;
    else if(y<=z)
        return y;
    else
        return z;
}

//return max value
int max(int x,int y)
{
    if(x>=y)
        return x;
    else
        return y;
}

//edit distance algorithm
float editDist(char s1[], char s2[], int m, int n) {
    if (m == 0)
        return n;
    if (n == 0)
        return m;
    if (s1[m - 1] == s2[n - 1])
        return editDist (s1, s2, m - 1, n - 1);
    return 1 + min(editDist(s1, s2, m, n - 1), editDist(s1, s2, m - 1, n),editDist(s1, s2, m - 1, n - 1));
}

//it creates connection if similarity is bigger or equal to 50%
void createConnection(struct graphHead *scanpaths,int first,int second,int S)
{
    struct graphArc *temp=malloc(sizeof(struct graphArc));
    //checking if there is any arc or not if has directly put temp to it
    if(scanpaths[first].firstArc==NULL)
    {
        temp->destination=scanpaths[second].first;
        temp->next=NULL;
        temp->weight=S;
        scanpaths[first].firstArc=temp;
    }
    //if there is already arc i skip the last one and add temp to it
    else
    {
        temp=scanpaths[first].firstArc;
        while(temp!=NULL)
            temp=temp->next;
        temp->destination=scanpaths[second].first;
        temp->next=NULL;
        temp->weight=S;
    }
}

//creating edges(arcs)
void createEdges(struct graphHead *scanPaths,int lines)
{
    int x=0,y;
    struct graphVertex *temp;
    for(x;x<lines-1;x++)
    {
        for(y=x+1;y<lines;y++)
        {
            char first[scanPaths[x].count];
            char second[scanPaths[y].count];
            temp=scanPaths[x].first;
            int z;

            //firstly copying the values to temp arrays and send it to edit dist
            for(z=0;z<scanPaths[x].count;z++)
            {
                first[z]=temp->data;
                temp=temp->next;
            }
            temp=scanPaths[y].first;
            for(z=0;z<scanPaths[y].count;z++)
            {
                second[z]=temp->data;
                temp=temp->next;
            }
            float d;//edit distance
            d=editDist(first,second,scanPaths[x].count,scanPaths[y].count);
            int n;//the number of characters in the longer string respectively
            n=max(scanPaths[x].count,scanPaths[y].count);
            float S;//similarity score
            S=100*(1-(d/n));
            if(S>=50.0){
                createConnection(scanPaths,x,y,S);
            }
        }
    }
}

void printSimilarScanpaths(struct graphHead *scanpaths,int size)
{
    int x=0,numberOfConnections=0;
    struct graphArc *temp;
    //first i need to know how many connection(arc) do i have in my scanpaths
    for(x;x<size;x++)
    {
        if(scanpaths[x].firstArc!=NULL)
        {
            numberOfConnections++;
            temp=scanpaths[x].firstArc;
            while(temp->next!=NULL)
            {
                temp=temp->next;
                numberOfConnections++;
            }
        }

    }
    struct printArrayStructer *myArray;
    myArray=malloc(numberOfConnections*(sizeof(struct printArrayStructer)));
    int count=0;
    //after learn the connection number i can create my array and put them in
    for(x=0;x<size;x++)
    {
        if(scanpaths[x].firstArc!=NULL)
        {
            myArray[count].from=x;
            myArray[count].to=scanpaths[x].firstArc->destination->index;
            myArray[count].weight=scanpaths[x].firstArc->weight;
            count++;
            temp=scanpaths[x].firstArc;
            while(temp->next!=NULL)
            {
                myArray[count].from=x;
                myArray[count].to=temp->destination->index;
                myArray[count].weight=temp->weight;
                count++;
                temp=temp->next;
            }
        }
    }
    int y;
    //sorting my array according to similarity
    for(x=0;x<numberOfConnections;x++)
    {
        for(y=0;y<numberOfConnections-x-1;y++)
        {
            if(myArray[y].weight<myArray[x].weight)
            {
                struct printArrayStructer temp=myArray[y];
                myArray[y]=myArray[x];
                myArray[x]=temp;
            }
        }
    }

    //print
    for(x=0;x<numberOfConnections;x++)
        printf("Scanpath %d and Scanpath %d - Similarity %f\n",myArray[x].from,myArray[x].to,myArray[x].weight);
}

struct graphHead* createVertices(char* fileName)
{
    FILE* myFile;

    //getting number of lines from file to allocate the scanpaths array
    myFile=fopen(fileName,"r");
    char c=getc(myFile);
    int lines=0;
    while (c!=EOF)
    {
        if (c=='\n')
            lines++;
        c=getc(myFile);
    }
    fclose(myFile);

    //making array
    struct graphHead *scanPaths;
    scanPaths=malloc(lines*sizeof(struct graphHead));
    int x=0;
    for(x;x<lines;x++)
    {
        scanPaths[x].count=0;
        scanPaths[x].first=NULL;
        scanPaths[x].firstArc=NULL;
    }

    //getting datas from file and giving values of the arrays
    myFile=fopen(fileName,"r");
    c=getc(myFile);
    int index=0;
    while(c!=EOF)
    {
        if(c!='\n')
        {
            struct graphVertex *vertex=malloc(sizeof(struct graphVertex));
            vertex->next=NULL;
            vertex->data=c;
            vertex->index=index;
            scanPaths[index].count++;
            if(scanPaths[index].first==NULL){
                scanPaths[index].first=vertex;
            }
            else{
                struct graphVertex *temp=scanPaths[index].first;
                while(temp->next!=NULL)
                    temp=temp->next;
                temp->next=vertex;
            }
        }
        else
            index++;
        c=getc(myFile);
    }
    fclose(myFile);
    createEdges(scanPaths,lines);
    printSimilarScanpaths(scanPaths,lines);
    return scanPaths;

}


int main()
{
    char fileName[50];
    printf("Enter a file name: ");
    gets(fileName);
    struct graphHead *scanPaths;
    scanPaths=createVertices(fileName);



    return 0;
}
