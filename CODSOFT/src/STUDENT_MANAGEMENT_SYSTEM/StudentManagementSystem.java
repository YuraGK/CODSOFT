package STUDENT_MANAGEMENT_SYSTEM;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentManagementSystem {
	
	List<Student> students;

	public StudentManagementSystem() {
		students = new LinkedList<Student>();
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public Student findStudent(int roll_number) {
		try {
		return students.stream().filter(n->n.getRoll_number()==(roll_number)).findFirst().get();
	
		}catch(NoSuchElementException e) {
			return null;
		}
		}

	public void addStudent(Student student) {
		if(findStudent(student.getRoll_number()) != null) {
			students.remove(findStudent(student.getRoll_number()));
		}
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}
}
