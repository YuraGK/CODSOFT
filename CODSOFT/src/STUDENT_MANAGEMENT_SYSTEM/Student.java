package STUDENT_MANAGEMENT_SYSTEM;

public class Student {
	private String name;
	private int roll_number; 
	private String grade;
	
	public Student(String name,int roll_number,String grade) {
		this.setGrade(grade);
		this.setRoll_number(roll_number);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoll_number() {
		return roll_number;
	}

	public void setRoll_number(int roll_number) {
		this.roll_number = roll_number;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
