//AHMET TURAN BULUT 2315174
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct Aois{
    char name;
    int TopLeftX;
    int width;
    int TopLeftY;
    int height;
    int fixationCount;
    int dwellTime;
};

int CheckNumberOfLine(char* path)
{
    FILE* Aoisfile;
    Aoisfile=fopen(path,"r");
    if(Aoisfile==NULL)
    {
        printf("File couldn't open!");
        return 0;
    }
    int NumberOfLines=0;
    char c=fgetc(Aoisfile);
    //checking the file for the \n until the end
    while(c!=EOF)
    {
        if(c=='\n')
            NumberOfLines++;
        c=fgetc(Aoisfile);
    }
    fclose(Aoisfile);
    NumberOfLines++;// for the last line
    NumberOfLines++;// index of 0 is dummy node
    return NumberOfLines;
}

void SaveAoisToArray(struct Aois* myAois,char * path,int NumberOfLines)
{
    FILE* Aoisfile;
    Aoisfile=fopen(path,"r");
    if(Aoisfile==NULL)
    {
        printf("File couldn't open");
    }
    else
    {
        int x;
        char ForNewLine;
        for(x=1;x<NumberOfLines;x++)
        {
            //taking each line according to file
            fscanf(Aoisfile,"%c %d %d %d %d%c",&myAois[x].name,&myAois[x].TopLeftX,&myAois[x].width,&myAois[x].TopLeftY,&myAois[x].height,&ForNewLine);
            myAois[x].fixationCount=0;
            myAois[x].dwellTime=0;
        }
        fclose(Aoisfile);
    }
}

void PrintAois(int NumberOfLines,struct Aois* myAois)
{
    int x;
    printf("Name\tTopX\tWidth\tTopY\tHeight\tFixCount\tDwellTime\n");
    //printing reverse order because when i do heap sort it does smaller to bigger
    for(x=NumberOfLines-1;x>=1;x--)
    {
        printf("%c\t%d\t%d\t%d\t%d\t%d\t\t%d\n",myAois[x].name,myAois[x].TopLeftX,myAois[x].width,myAois[x].TopLeftY,myAois[x].height,myAois[x].fixationCount,myAois[x].dwellTime);
    }
}

void ComputeFixationCount(struct Aois *myAois,int AosisLenght ,char* path,int NumberOfPeople)
{
    int b;
    for(b=0;b<NumberOfPeople;b++)
    {
        //trying to create full path for the data file. I assuming the all files goes like 1.txt 2.txt 3.txt
        //and in command line user should give full path for the datafile mine is D:\315_assignment\data
        char* IntToStr[3];
        char* FullPath[30];
        strcpy(FullPath,path);
        sprintf(IntToStr,"%d",b+1);
        strcat(FullPath,IntToStr);
        strcat(FullPath,".txt");
        FILE* DataFile;
        DataFile=fopen(FullPath,"r");
        if(DataFile==NULL)
            printf("File couldn't open");
        else
        {
            char c=fgetc(DataFile);
            int NumberOfLines=0;
            while(c!=EOF)
            {
                if(c=='\n')
                    NumberOfLines++;
                c=fgetc(DataFile);
            }
            fclose(DataFile);
            DataFile=fopen(FullPath,"r");
            int a;
            char* takeStrings[30];
            //assuming all files has same char at the start of file
            fgets(takeStrings,19,DataFile);
            for(a=0;a<NumberOfLines;a++)
            {
                int index,x,y,duration;
                fscanf(DataFile,"%d %d %d %d",&index,&x,&y,&duration);
                int c;
                for(c=1;c<=AosisLenght;c++)
                {
                    // if data in the border of any aoi it increments the fix count of that aoi
                    if(x>=myAois[c].TopLeftX && x<=myAois[c].TopLeftX+myAois[c].width && y>=myAois[c].TopLeftY &&y<=myAois[c].TopLeftY+myAois[c].height)
                    {
                        myAois[c].fixationCount++;
                        break;
                    }

                }
            }
            fclose(DataFile);
        }

    }
}

void ComputeDwellTime(struct Aois *myAois,int AosisLenght ,char* path,int NumberOfPeople)
{
    int b;
    for(b=0;b<NumberOfPeople;b++)
    {
        char* IntToStr[3];
        char* FullPath[30];
        strcpy(FullPath,path);
        sprintf(IntToStr,"%d",b+1);
        strcat(FullPath,IntToStr);
        strcat(FullPath,".txt");
        FILE* DataFile;
        DataFile=fopen(FullPath,"r");
        if(DataFile==NULL)
            printf("File couldn't open");
        else
        {
            char c=fgetc(DataFile);
            int NumberOfLines=0;
            while(c!=EOF)
            {
                if(c=='\n')
                    NumberOfLines++;
                c=fgetc(DataFile);
            }
            fclose(DataFile);
            DataFile=fopen(FullPath,"r");
            int a;
            char* takeStrings[30];
            fgets(takeStrings,19,DataFile);
            for(a=0;a<NumberOfLines;a++)
            {
                int index,x,y,duration;
                fscanf(DataFile,"%d %d %d %d",&index,&x,&y,&duration);
                int c;
                printf("%d %d %d %d\n",index,x,y,duration);
                for(c=1;c<AosisLenght;c++)
                {
                    if(x>=myAois[c].TopLeftX && x<=myAois[c].TopLeftX+myAois[c].width && y>=myAois[c].TopLeftY &&y<=myAois[c].TopLeftY+myAois[c].height)
                    {
                        myAois[c].dwellTime+=duration;
                        break;
                    }

                }
            }
            fclose(DataFile);
        }

    }
}

void HeapSort(struct Aois* myAois,int AoisLenght,int SortType)
{
    // Build heap (rearrange array)
    BuildHeap(myAois,AoisLenght,SortType);

    // One by one extract an element from heap
    int i;
    for (i = AoisLenght - 1; i > 0; i--) {
        // Move current root to end
        struct Aois temp=myAois[1];
        myAois[1]=myAois[i];
        myAois[i]=temp;

        // call max heapify on the reduced heap
        Heapify(myAois,1,SortType,i);
    }
}

void BuildHeap(struct Aois* myAois,int AoisLenght,int SortType)
{
    for (int i = AoisLenght / 2 - 1; i > 0; i--)
        Heapify(myAois,i,SortType, AoisLenght);
}

void Heapify(struct Aois* myAois,int root,int SortType,int AoisLenght)
{
    int largest = root; // Initialize largest as root
    int l = 2 * root; // left = 2*root
    int r = 2 * root + 1; // right = 2*root + 1

    // If left child is larger than root
    if (SortType==1 && l < AoisLenght && myAois[l].fixationCount > myAois[largest].fixationCount)
        largest = l;

    // If right child is larger than largest so far
    if (SortType==1 && r < AoisLenght && myAois[r].fixationCount > myAois[largest].fixationCount)
        largest = r;
        // If left child is larger than root
    if (SortType==2 && l < AoisLenght && myAois[l].dwellTime > myAois[largest].dwellTime)
        largest = l;

    // If right child is larger than largest so far
    if (SortType==2 && r < AoisLenght && myAois[r].dwellTime > myAois[largest].dwellTime)
        largest = r;

    // If largest is not root
    if (largest != root) {
        struct Aois temp=myAois[root];
        myAois[root]=myAois[largest];
        myAois[largest]=temp;
        // Recursively heapify the affected sub-tree
        Heapify(myAois,largest,SortType,AoisLenght);
    }
}


int main(int argc, char *argv[])
{
    if(argc<5 && argc>5)
    {
        printf("You entered less or more argument than excepted!");
    }
    else
    {

        int NumberOfLines=CheckNumberOfLine(argv[2]);
        struct Aois *myAois;
        myAois=malloc(sizeof(struct Aois)*NumberOfLines);
        SaveAoisToArray(myAois,argv[2],NumberOfLines);
        ComputeFixationCount(myAois,NumberOfLines,argv[3],atoi(argv[4]));
        ComputeDwellTime(myAois,NumberOfLines,argv[3],atoi(argv[4]));
        HeapSort(myAois,NumberOfLines,atoi(argv[1]));
        PrintAois(NumberOfLines,myAois);
    }

    return 0;
}
