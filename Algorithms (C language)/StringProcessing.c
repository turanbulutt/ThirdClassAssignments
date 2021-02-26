//Ahmet Turan Bulut 2315174
#include <stdio.h>
#include <stdlib.h>

struct sensorData{
    int id;
    int time;
};

struct sensorData* createSensorDataArray(char *filepath)
{
    FILE* myFile;
    myFile=fopen(filepath,"r");
    char c=getc(myFile);
    int lines=0;
    //getting number of lines in the file
    while (c!=EOF)
    {
        if (c=='\n')
            lines++;
        c=getc(myFile);
    }
    fclose(myFile);

    //dividing to 2 because on/off there are actually line/2 data
    lines/=2;
    //creating mydata
    struct sensorData *myData;
    myData=malloc(lines*sizeof(struct sensorData));
    int x;
    for(x=0;x<lines;x++)
    {
        myData[x].id=0;
        myData[x].time=0;
    }
    myFile=fopen(filepath,"r");
    int id,time,count=0,id2,time2,activeTime;
    char string[5];

    for(x=0;x<lines;x++)
    {
        //taking datas from file
        activeTime=0;
        fscanf (myFile, "%d %s %d\n", &id,string,&time);
        fscanf (myFile, "%d %s %d\n", &id2,string,&time2);

        //if time>time2, that means we getting next day so i subtract from 86399
        if(time>time2)
            activeTime=86399-time+time2;
        else
            activeTime=time2-time;
        //if sensor time bigger than 120 seconds, i am adding data to my array
        if(activeTime>=120)
        {
            myData[count].id=id;
            myData[count].time=activeTime;
            count++;
        }
    }

    return myData;
};

void printPattern(struct sensorData *T,int index,int lenght)
{
    int i,totalTime=0;
    //getting total time
    for(i=0;i<lenght;i++)
    {
        totalTime+=T[index+i].time;
    }
    //splitting total time to hours,minutes and seconds
    int hour=totalTime/3600;
    totalTime=totalTime%3600;
    int minute=totalTime/60;
    totalTime=totalTime%60;
    printf("%d hour(s), %d minute(s), %d second(s)",hour,minute,totalTime);
}

void rabinKarp(struct sensorData *T,char *P,int d,int q)
{
    int n=0,x;
    //calculating lenght of T and P
    for(x=0;T[x].id!=NULL;x++){
        n++;}
    int m=0;
    for(x=0;P[x]!='\0';x++)
        m++;
    int i,h=1;
    //initial h
    for(i=0;i<m-1;i++)
    {
        h=(h*d)%q;
    }
    int p=0,t=0;
    //decide initial p and t
    for(i=0;i<m;i++)
    {
        char c[3];
        sprintf(c,"%d",T[i].id);
        p=(d*p+P[i])% q;
        t=(d*t+c[0])% q;
    }

    for(i=0;i<=n-m;i++)
    {
        //hash values are same check the all pattern
        if(p==t)
        {
            int j;
            for(j=0;j<m;j++)
            {
                char c[3];
                sprintf(c,"%d",T[i+j].id);
                //if it is not equal, thais is mean that we don't have pattern break it!
                if((c[0])!=(P[j]))
                    break;
            }
            //if j=m that is mean i have match up, call print pattern function
            if(j==m)
            {
                printPattern(T,i,m);
            }
        }
        //of there are still possible matches i am changing the pattern with next one and check
        if(i<n-m)
        {
            char c1[3],c2[3];
            sprintf(c1,"%d",T[i].id);
            sprintf(c2,"%d",T[i+m].id);
            int c1ascii=c1[0];
            int c2ascii=c2[0];
            t = ((d * (t - ((c1[0]) * h))) + (c2[0])) % q;
            if(t<0)
                t+=q;
        }
    }
}

void searchPattern(struct sensorData *myData,char *pattern)
{
    rabinKarp(myData,pattern,10,11);
}

int main()
{
    //taking the datapath
    char fileName[50];
    printf("Enter a file name: ");
    gets(fileName);

    //checking the file and filtering the sensor which is below 120 second
    struct sensorData *myData;
    myData=createSensorDataArray(fileName);

    //pattern menu
    char pattern[10];
    printf("Create a pattern now.\n0. Watering the flowers\n1. Sleeping\n2. Eating\n3. Relaxing\n4. Studying\n5. Washing\n6. Cooking\n7. Bathroom\n8. Getting Dressed\n9. Entering/Leaving\nEnter a pattern: ");
    gets(pattern);
    printf("Pattern is created: ");
    int i;
    //to show the user which pattern it is choosed
    for(i=0;pattern[i]!='\0';i++)
    {
        switch(pattern[i])
        {
            case '0':printf("Watering the flowers >");break;
            case '1':printf("Sleeping >");break;
            case '2':printf("Eating >");break;
            case '3':printf("Relaxing >");break;
            case '4':printf("Studying >");break;
            case '5':printf("Washing >");break;
            case '6':printf("Cooking >");break;
            case '7':printf("Bathroom >");break;
            case '8':printf("Getting Dressed >");break;
            case '9':printf("Entering/Leaving >");break;
        }
    }

    //searching the pattern
    searchPattern(myData,pattern);

    return 0;
}
