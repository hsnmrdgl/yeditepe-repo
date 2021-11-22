#include "lisch.h"


LISCH::LISCH(int table_size){
    
    data_vec.resize(table_size);
}


void LISCH::insert(int new_data){
    
    lisch_entry * l = new lisch_entry();
    l->data = new_data;
    int mod = new_data%data_vec.size();
    
    if(data_vec[mod].valid == false){

        l->valid = true;
        data_vec[mod].valid = true;
        data_vec[mod] = *l;
    }

    else{

        int i = data_vec.size()-1;
        
        while((i>=0)){

            if(data_vec[i].valid == false){

                data_vec[i] = *l;
                data_vec[i].valid = true;
                l->valid = true;
                break;
            }
            i--;
        }

        while(true){

            if(data_vec[mod].link==0){

                data_vec[mod].link = i;
                break;
            }

            else{
                mod = data_vec[mod].link;
            }
        }
    }
}


int LISCH::find_num_probes(int key) const{
    
    int mod = key%data_vec.size();
    int index_to_look = mod;
    int probe = 1;
    
    while(true){

        if(data_vec[index_to_look].data==key){

            return probe;
        }

        else{

           index_to_look = data_vec[index_to_look].link;
           probe++;
        }
    }
}


double LISCH::find_average_num_probes() const{
    
    int total = 0;
    int keys = 0;
    
    for(int i = 0;i<data_vec.size();i++){

        if(data_vec[i].valid == true){

            total+=find_num_probes(data_vec[i].data);
            keys++;
        }
    }

    return static_cast<double>(total)/keys;
}


bool LISCH::does_include(int key) const{
    
    int mod = key%data_vec.size();
    int index_to_look = mod;
    
    while(index_to_look >=0){
        
        if(data_vec[index_to_look].data==key){

            return true;
        }

        else{
            if(data_vec[index_to_look].link!=0){
                 index_to_look = data_vec[index_to_look].link;
            }

            else{
               return false;
            }
        }
    }
    return false;
}