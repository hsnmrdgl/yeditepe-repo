
public class Date {
	
	//FIELDS
	private int day;
	private int month;
	private int year;
	
	
	//CONSTRUCTOR
	public Date(int theDay, int theMonth, int theYear) {
		
		month = checkMonth(theMonth);
		day = checkDay(theDay);
		year = theYear;
	}
	
	
	//DAY VALIDATION METHOD
	private int checkDay(int testDay) {
		
		int daysPerMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		if(testDay > 0 && testDay <= daysPerMonth[month]) {
			return testDay;
		}
		if( month == 2 && testDay == 29 && (year % 400 == 0 || (year % 4 == 0 && year % 100  != 0)) ) {
			return testDay;
		}
		
		else {
			System.out.print("Invalid day! Set to 1.\n");
		}
		
		return 1;
	}
	
	//MONTH VALIDATION METHOD
	private int checkMonth(int testMonth) {
		
		if(testMonth > 0 && testMonth <= 12) {
			return testMonth;
		}
		else {
			System.out.print("Invalid month! Set to 1.\n");
			return 1;
		}
	}
	
	
	public String toString() {
		return String.format("%02d/%02d/%02d", day, month, year);
	}


	//GETTER & SETTER
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

}