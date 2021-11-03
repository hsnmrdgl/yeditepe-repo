#include <stdio.h>

//Print "a b" a times
void printLine(int a, int b){

	if (a == 2){
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
	}

	else if (a == 3){
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
	}

	else if (a == 4){
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
	}

	else if (a == 5){
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
		printf("%d   %d\n", a, b );
	}
	else{
		printf("An error!\n");
	}

}


//Print triangle with x's (upper if odd, lower if even)
void printTriangle(int x){

	//Lower triangle (even)
	if (x%2 == 0){
		printf("%d %d %d\n", x, x, x);
		printf(" %d %d \n", x, x);
		printf("  %d  \n", x);
	}

	//Upper triangle (odd)
	else if (x%2 != 0){
		printf("  %d  \n", x);
		printf(" %d %d \n", x, x);
		printf("%d %d %d\n", x, x, x);
	}

	else{
		printf("An error!");
	}

}



void main(){
	int a,b,x;
	
	printf( "> Enter a and b for printLine : ");
    scanf( "%d %d", &a, &b );
	printf( "> Enter x for printTriangle : ");
    scanf( "%d", &x );


    if(a < 1 || a > 5){
    	printf("\"a\" can be between 2-5!!\n");
    	return;
    }

    else{
    	printLine(a, b);
	    printLine(b, a);
	    printTriangle(x);
	    printLine(a, b);
	    printTriangle(x);
    }
}