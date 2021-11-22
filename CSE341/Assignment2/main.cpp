#include <iostream>
#include <fstream>

#include "po.cpp"

using std::cout;
using std::endl;
using std::cerr;

void print_table(const PO & tbl){

	for(int i = 0; i < tbl.data_vec.size(); i++){
		cout << i << ":\t";
		if(tbl.data_vec[i].valid){
			cout << tbl.data_vec[i].data <<  "\t" << tbl.find_num_probes(tbl.data_vec[i].data);
		}
		cout << endl;
	}

	cout << endl << "Average # of probes: " << tbl.find_average_num_probes() << endl;
}

int main(){

	std::ifstream fin("numbers");
	int number;
	int cnt = 0;

	PO tbl(11);

	while(fin >> number){
		tbl.insert(number);
	}
	cout << endl << "---Table---" <<endl;
	print_table(tbl);

	fin.close();

	return 0;
}
