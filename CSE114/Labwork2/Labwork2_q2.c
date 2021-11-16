#include <stdio.h>

int main(){
	
	int limit, number;
	int positives=0, negatives=0, zeros=0;
	
	printf("Enter the limit: ");
	scanf("%d",&limit);
	
	while(limit){
		
		printf("Enter the number: ");
		scanf("%d",&number);
		
		if(number < 0){
			negatives++;
		}
		else if (number > 0){
			positives++;
		}
		else if (number == 0){
			zeros++;
		}
		
		limit--;
	}
	
	printf("\n");
	printf("Positive numbers : %d\n", positives);
	printf("Negative numbers : %d\n", negatives);
	printf("Number of zeros : %d\n", zeros);
	
}