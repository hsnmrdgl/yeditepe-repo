#include <vector>

using std::vector;



class PO{
public:					// Would normally be private. Decleared public for testing purposes.
	class po_entry{
	public:
		bool valid;
		int data;

		po_entry(){
			valid = false;
		}
	};

	vector<po_entry> data_vec;


public:

	PO(int);
	void insert(int);
	int find_num_probes(int) const;
	double find_average_num_probes() const;
	bool does_include(int) const;

};
