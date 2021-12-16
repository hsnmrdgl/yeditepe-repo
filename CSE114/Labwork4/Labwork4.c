#include <stdio.h>

int is_letter(char ch){
	
	if(ch >= 'a' && ch <= 'z'){
		return 1;
	}
	else
		return 0;
	
}

void letters(char * dest, char * source){
	
	if(*source!='\0'){
		if(is_letter(*source)){
			*dest = *source;
			letters(dest+1,source+1);
		}
		else
			letters(dest,source+1);
	}	
}

int main(){
	
	char input[] = "mx90456453abxxx cxd,,,, fxxxxx xxgxxx";
	char output[50];
	
	printf("Input  : %s\n", input);
	letters(output, input);
	printf("Output : %s\n", output);
}