#include <stdio.h>

int squared_diff1(int x, int y){
	
	int result = (x*x)-(y*y);
	
	if(result < 0)
		return result*(-1);
	else
		return result;
}

int squared_diff2 (int x, int y){
	
	int result = (x+y)*(x-y);

	if(result < 0)
		return result*(-1);
	else
		return result;
}


void main(){
	
	int x,y;
	int sqdiff1,sqdiff2;
	
	printf( "> Enter x : ");
    scanf( "%d", &x );
	printf( "> Enter y : ");
    scanf( "%d", &y );
	
	sqdiff1 = squared_diff1(x,y);
	printf("squared_diff1 returns : %d\n", sqdiff1);
	
	if(sqdiff1 < 20){
		printf( "Result is smaller than 20!\n");
		printf( "> Enter y again : ");
		scanf( "%d", &y );
		
		sqdiff1 = squared_diff1(x,y);
		printf("squared_diff1 returns : %d\n", sqdiff1);
		
		if(sqdiff1 < 20){
			y = x+5;
			printf("Setting y value : %d\n", y);
			
			sqdiff1 = squared_diff1(x,y);
			printf("squared_diff1 returns : %d\n", sqdiff1);
			
		}
		
	}
		
	
	sqdiff2 = squared_diff2(x,y);		
	printf("squared_diff2 returns : %d\n", sqdiff2);
			
	if(sqdiff1 == sqdiff2)
		printf("SAME!\n");
	else
		printf("DIFFERENT !\n");

}