// Ahmet Turan Bulut 2315174

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

struct Date{
    int day;
    int month;
    int year;
};

struct Dog{
    int id;
    char name[30];
    float weight;
    float height;
    struct Date enrty_date;
    struct Date leave_date;
};

int addDog(struct Dog** dogs,int* totalDogs,int totalSize,int n_id,char * n_name, float n_weight, float n_height, int n_day, int n_month, int n_year)
{
    //creating dog structer and setting it's value's
    struct Dog* newDog;
    newDog=malloc(sizeof(struct Dog));
    newDog->enrty_date.day=n_day;
    newDog->enrty_date.month=n_month;
    newDog->enrty_date.year=n_year;
    newDog->height=n_height;
    newDog->id=n_id;
    newDog->weight=n_weight;
    newDog->leave_date.day=0;
    newDog->leave_date.month=0;
    newDog->leave_date.year=0;
    strcpy(newDog->name,n_name);
    int i=0,key;
    for(i;i<totalSize;i++)
    {
        //founding the dog's place according to it is id
        key=(int)(n_id+pow(i,2))%totalSize;
        if(dogs[key]==NULL)
            break;
    }
    //setting dog in the hash table
    dogs[key]=newDog;
    // increasing number of dogs
    (*totalDogs)++;
    //checking if hash table need to re hashing or not
    if((double)(*totalDogs)/totalSize>0.5)
    {
        totalSize=reHashing(dogs,totalSize);
    }
    return totalSize;
}

int reHashing(struct Dog** dogs,int totalSize)
{
    int oldSize=totalSize,trash,a=0; // a is for the number of dogs while i send the addDog it is not important because in addDog i am using dog number for load factor
    // and use it for if re hashing necessary or not but when i am call add dog function in re hashing it will not call re hashing again.
    //setting total size for it is double and next prime number
    totalSize*=2;
    int i,check;
    do
    {
        check=0;
        for(i=2;i<totalSize;i++)
        {
            if(totalSize%i==0)
                check=1;
        }
        if(check)
            totalSize++;
    }
    while(check);
    // creating new table according new size and setting null inside of it
    struct Dog** newList=malloc(sizeof(struct Dog*)*totalSize);
    for(i=0;i<totalSize;i++)
        newList[i]=NULL;
    for(i=0;i<oldSize;i++)
    {
        // checking old list and if dog is exist i will insert it to new list with new place
        if(dogs[i]!=NULL)
        {
            trash=addDog(newList,&a,totalSize,dogs[i]->id,dogs[i]->name,dogs[i]->weight,dogs[i]->height,dogs[i]->enrty_date.day,dogs[i]->enrty_date.month,dogs[i]->enrty_date.year); // i said what "a" is doing
        }
    }
    //chancing my main list to new size and setting null inside of it
    *dogs=malloc(sizeof(struct Dog*)*totalSize);
    for(i=0;i<totalSize;i++)
    {
        dogs[i]=NULL;
    }
    a=0;
    for(i=0;i<totalSize;i++)
    {
        //i am setting my main list according to i created new list
        if(newList[i]!=NULL)
        {
            trash=addDog(dogs,&a,totalSize,newList[i]->id,newList[i]->name,newList[i]->weight,newList[i]->height,newList[i]->enrty_date.day,newList[i]->enrty_date.month,newList[i]->enrty_date.year);
        }
    }
    return totalSize;

}

void SearchDog(struct Dog** dogs,int totalSize,int id)
{
    int check=0,i=0,key;
    for(i;i<totalSize;i++)
    {
        // first i will found the dog according it is id
        key=(int)(id+pow(i,2))%totalSize;
        if(dogs[key]->id==id)
        {
            //printing dogs name and other things for the dog
            printf("%d\n",key);
            check=1;
            printf("Name: ");
            puts(dogs[key]->name);
            printf("Weight: %f\nHeight: %f\nEntry Date: %d.%d.%d\n",dogs[key]->weight,dogs[key]->height,dogs[key]->enrty_date.day,dogs[key]->enrty_date.month,dogs[key]->enrty_date.year);
            break;
        }
    }
    if(!check)
        //if id is not exist i print error message
        printf("No dog is found!\n");
}

void AdoptDog(struct Dog** dogs,int totalSize,int id)
{
    int i=0,key,check=0;
    for(i;i<totalSize;i++)
    {
        // first i will found the dog according it is id
        key=(int)(id+pow(i,2))%totalSize;
        if(dogs[key]->id==id)
        {
            //printing dogs name and other things for the dog
            check=1;
            printf("Name: ");
            puts(dogs[key]->name);
            printf("Weight: %f\nHeight: %f\nEntry Date: %d.%d.%d\n",dogs[key]->weight,dogs[key]->height,dogs[key]->enrty_date.day,dogs[key]->enrty_date.month,dogs[key]->enrty_date.year);
            // i ask user the leave date o the dog
            printf("Enter leave date: ");
            scanf("%d.%d.%d",&dogs[key]->leave_date.day,&dogs[key]->leave_date.month,&dogs[key]->leave_date.year);
            printf("%s is adopted\n",dogs[key]->name);
            break;
        }
    }
    if(!check)
        //if id is not exist i print error message
        printf("No dog is found!\n");
}

int checkID(struct Dog** dogs,int totalSize,int id)
{
    // checking id if it is exist or not
    int i;
    for(i=0;i<totalSize;i++)
    {
        if(dogs[i]!=NULL && dogs[i]->id==id)
            return 1;
    }
    return 0;
}

int main()
{
    int totalSize=11;
    struct Dog** dogs;
    dogs=malloc(sizeof(struct Dog*)*totalSize);
    int i;
    //creating the hash table and set null inside of it
    for(i=0;i<totalSize;i++)
    {
        dogs[i]=NULL;
    }
    int op=1,totalDogs=0;
    int id,day,month,year;
    float weight, height;
    char name[30];
    while(op!=4)
    {
        printf("\nWelcome to dog shelter application\n1)Add a dog\n2)Search for a dog\n3)Adopt a dog\n4)Exit\nSelect: ");
        scanf("%d",&op);
        switch(op)
        {
        case 1:
            // if user want to add dog, i will ask id and check it if it is already exist or not. If it is exist i will ask again
            //if it is not exist i will ask rest of the things. and will add dog through addDog() function.
            do
            {
                printf("Please enter the unique identifier: ");
                scanf("%d",&id);
            }
            while(checkID(dogs,totalSize,id));
            fflush(stdin);
            printf("Enter name: ");
            gets(name);
            printf("Enter weight: ");
            scanf("%f",&weight);
            printf("Enter height: ");
            scanf("%f",&height);
            printf("Enter entry date: ");
            scanf("%d.%d.%d",&day,&month,&year);
            totalSize=addDog(dogs,&totalDogs,totalSize,id,name,weight,height,day,month,year);
            break;
        case 2:
            // if user want to search the dog i will ask id and search dog via SearchDog() function
            printf("Please enter the unique identifier: ");
            scanf("%d",&id);
            SearchDog(dogs,totalSize,id);
            break;
        case 3:
            // if user want to adopt dog i will ask id and adopt dog via AdoptDog() function
            printf("Please enter the unique identifier: ");
            scanf("%d",&id);
            AdoptDog(dogs,totalSize,id);
            break;
        }
    }
    return 0;
}
