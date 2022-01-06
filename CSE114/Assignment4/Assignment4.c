#include <stdio.h>

int isInBetween(char ch1, char ch2, char * str){
	int flag1 = 0, flag2 = 0;
	int i=0;

	while(str[i]!='\0')
    {
        if(ch1==str[i])
        {
            printf("%c character is found at %d location\n",ch1,(i+1));
            flag1=1;
        }
        if(ch2==str[i])
        {
            printf("%c character is found at %d location\n",ch2,(i+1));
            flag2=1;
        }
        i++;
    }
    if(!(flag1 && flag2))
    {
        printf("Character %c and %c is not found in the string %s\n", ch1, ch2, str);
    }
    return 0;

}


void moveToEnd(char * s, int left, int right, char c1, char c2)
{
	if (left >= right)
		return;

	char curr = s[left];
	char lowerC1 = c1 + 32;
	char lowerC2 = c2 + 32;


	if((curr > lowerC2 && curr < 123) || (curr > c2 && curr < 91))
		printf("%c", curr);

	moveToEnd(s, left+1, right, c1, c2);

	if((curr >= lowerC1 && curr <= lowerC2) || (curr >= c1 && curr <= c2))
		printf("%c", curr);

	return;
}



int main(){

	char myString[] = "This is a ABc Test";
	char char1, char2;
	char *p = myString;

	int strLength = 0;
	int i, j;

	printf("Input String : %s\n", myString);

	printf("Enter first char : ");
	scanf(" %c", &char1);

	printf("Enter second char : ");
	scanf(" %c", &char2);

	isInBetween(char1, char2, p);

	//STRING LENGTH
	for(i=0; p[i] != '\0'; i++){
		strLength++;
	}
	printf("String Length : %d\n", strLength );

	moveToEnd(myString, 0, strLength, char1, char2);
	printf("\n");
}