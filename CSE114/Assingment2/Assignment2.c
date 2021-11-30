#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int getNumberOfDigits(int n){

    int counter = 0;
    for (; n > 0; n /= 10)
        counter++;
    return counter;
}

int nthdig(int n, int k){
    while(n--)
        k/=10;
    return k%10;
}


int function(int num){

    int size = getNumberOfDigits(num);
    int half = size/2;
    int count = 0;

    int firstArray[half];
    int lastArray[half];
    int digitArray[size];

    for(int i=size-1; i>=0; i--){
        digitArray[i] = nthdig(count, num);
        count++;
    }

    if(size == 2){
        firstArray[0] = digitArray[0];
        lastArray[0] = digitArray[1];
    }

    else if(size == 3){
        firstArray[0] = digitArray[0];
        lastArray[0] = digitArray[2];
    }


    else if(size%2 == 0){
        for(int i=0; i<half; i++){
            firstArray[i] = digitArray[i];
        }

        for(int i=half, j=0; i<size; i++,j++){
            lastArray[j] = digitArray[i];
        }
    }

    else{
        for(int i=0; i<half; i++){
            firstArray[i] = digitArray[i];
        }

        for(int i=half+1, j=0; i<size; i++,j++){
            lastArray[j] = digitArray[i];
        }
    }

    int a = 0;
    int b = 0;

    for (int i = 0; i < half; i++)
        a = 10 * a + firstArray[i];

    for (int i = 0; i < half; i++)
        b = 10 * b + lastArray[i];

    if(a > b){
        int tmp;
        tmp = b;
        b = a;
        a = tmp;
    }

    printf("%d %d\n", a, b);


    bool flag = true;

    for(int i = 0; i <=10; i++){    
        a = 3*a +2;
        b = 2*b +3;

        if(a == b){
            printf("Congrulations you caught one!\n");
            flag = false;
            return 1;
        }

        if(a > b){
            int tmp;
            tmp = b;
            b = a;
            a = tmp;
        }
    }

    if(flag){
        printf("10 tries were not enough!\n");
        return 2;
    }
}


int main(void){
    
	int number;

    printf("Enter a number : ");
    scanf("%d", &number);
    function(number);

    while(1){
        printf("Enter a number : ");
        scanf("%d", &number);
        if(function(number) == 1){
            break;
        } 
    }
    
}