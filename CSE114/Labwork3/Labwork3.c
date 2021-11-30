#include <stdio.h>

void  print(int arr[], int size)
{
    int i;
	for(i=0; i<size ; i++)
		printf("%d ",arr[i]);
	printf("\n");
}


int countN(int arr[], int size, int n)
{
	int i,count=0;
	for(i=0; i<size ; i++)
		if(arr[i] == n)
			count++;
		else if (arr[i+1] != n)
			return count;
}


void compressArray(int *ptr, int size, int arr[]){
	
	int count = 0;
	int i, j = 0;
	
	for(i=0; i<size ; i += count){
		count = countN(ptr, size, *ptr);
		arr[j++] = count;
		arr[j++] = *ptr;
		ptr += count;
	}
	
	arr[j] = -1;
}


void findSize(int arr[], int size, int *ptr){
	
	for(int i=0; i<size ; i++){
		if(arr[i] != -1){
			(*ptr)++;
		}
		else
			break;
	}
}


int main(){
	
	int maxCapacity = 50;
	
	int myArray[] = {3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 9, 9, 7, 8, 8, 8, 8, 8, 8, 8};
	int compressedArray[maxCapacity];
	int compressedSize = 0;
	
	print(myArray, 35);
	
	compressArray(myArray, 35, compressedArray);

	findSize(compressedArray, maxCapacity, &compressedSize);
	
	print(compressedArray, compressedSize);
}





























