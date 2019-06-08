package com.matrangola.school.app;

import com.matrangola.school.domain.Course;
import com.matrangola.school.domain.Student;
import com.matrangola.school.service.CourseService;
import com.matrangola.school.service.StudentService;

import java.util.List;

public class RegistrationApp {

	int value;

	public static void main(String[] args) {
		primeAndPrintBoth();
		 //postRequestToAddAStudent();
		 //getRequestForAllStudents();
	}


	public static void postRequestToAddAStudent() {
		StudentService ss = new StudentService();
		ss.createStudent("New One", "282 484 9944", Student.Status.FULL_TIME);

		List<Student> students = ss.getAllStudents();
		students.forEach(System.out::println);
	}

	public static void getRequestForAllStudents() {
		StudentService ss = new StudentService();
		List<Student> students = ss.getAllStudents();
		System.out.println("All Students: " + students.size());
		students.forEach(System.out::println);
	}

	public static void primeAndPrintBoth() {
		StudentService ss = new StudentService();
		init(ss);
		List<Student> students = ss.getAllStudents();
		students.forEach(System.out::println);

		CourseService cs = new CourseService();
		init(cs);
		List<Course> courses = cs.getAllCourses();
		courses.forEach(System.out::println);

	}

	public static void init(StudentService ss) {
		ss.createStudent("Manoj", "282 939 9944", Student.Status.FULL_TIME);
		ss.createStudent("Charlene", "282 898 2145", Student.Status.FULL_TIME);
		ss.createStudent("Firoze", "228 678 8765", Student.Status.HIBERNATING);
		ss.createStudent("Joe", "3838 678 3838", Student.Status.PART_TIME);
	}

	public static void init(CourseService cs) {
		cs.createCourse("Math-101", "Intro To Math");
		cs.createCourse("Math-201", "More Math");
		cs.createCourse("Phys-101", "Baby Physics");
	}

}