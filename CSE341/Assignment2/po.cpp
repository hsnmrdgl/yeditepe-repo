#include "po.h"


PO::PO(int table_size){
	data_vec.resize(table_size);
}


void PO::insert(int new_data){

    int size = data_vec.size();
	po_entry * p = new po_entry();
    p->data = new_data;
    int mod = new_data % size;

    if(data_vec[mod].valid == false){

        p->valid = true;
        data_vec[mod].valid = true;
        data_vec[mod] = *p;
    }

    else{

    	for(int i = mod; i <= size; i++){

            i = i%size;

    		if(data_vec[i].valid == false){

                data_vec[i] = *p;
                data_vec[i].valid = true;
                p->valid = true;
                break;
            }
    	}
    }
}



int PO::find_num_probes(int key) const{
    int size = data_vec.size();
    int mod = key%size;
    int count = 1;

    for(int i = mod; i <= size; i++){
        i = i%size;
        
        if(data_vec[i].data == key){
            break;
        }
        count++;
    }

    return count;
}



double PO::find_average_num_probes() const{

    int total = 0;
    int size = data_vec.size();
    
    for(int i = 0; i < size; i++){
        
        total = total + find_num_probes(data_vec[i].data);
    }

    double avg = (double)total/size;

    return avg;
}