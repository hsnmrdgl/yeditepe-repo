#include <stdio.h>

int main(){
	
	int i,j,rows,column = 1, last = 1;
	
	printf("Enter rows: ");
	scanf("%d",&rows);
	
	for(i=1; i<=rows; i++){
		for(j=1; j<=column; j++){
			if(last == 10) last = 1;
			printf("%d ", last);
			last++;
		}
		
		column = column*2;
		printf("\n");
	}
}