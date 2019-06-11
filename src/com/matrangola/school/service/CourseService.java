package com.matrangola.school.service;

import com.matrangola.school.dao.BaseDAO;
import com.matrangola.school.dao.inmemory.InMemoryCourseDAO;
import com.matrangola.school.domain.Course;
import com.matrangola.school.domain.IdentObject;

import java.util.List;

public class CourseService {

	private InMemoryCourseDAO courseDAO;
	
	public CourseService() {
		courseDAO = new InMemoryCourseDAO();
	}
	
	public IdentObject createCourse(String code, String title) {
		Course course = new Course(code, title);
		course = courseDAO.create(course);
		
		return course;
	}
	
	public IdentObject createCourse(Course course) {
		course = courseDAO.create(course);
		
		return course;
	}
	
	public void deleteCourse(int id) {
		IdentObject course = courseDAO.get(id);
		if(course != null) {
//			courseDAO.delete(course);
		}
	}
	
	public void updateCourse(Course course) {
		courseDAO.update(course);
	}
	
	public IdentObject getCourseByCode(String code) {
		List<Course> courses = courseDAO.getAll();
		for(Course course : courses) {
			if(course.getCode().equals(code)) {
				return course;
			}
		}
		return null;
	}
	
	public IdentObject getCourse(int id) {
		return courseDAO.get(id);
	}
	
	public List<Course> getAllCourses() {
		return courseDAO.getAll();
	}
	
	public BaseDAO<Course> getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(InMemoryCourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
}