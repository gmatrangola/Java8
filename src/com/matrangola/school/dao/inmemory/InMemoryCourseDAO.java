package com.matrangola.school.dao.inmemory;

import com.matrangola.school.dao.BaseDAO;
import com.matrangola.school.domain.Course;
import com.matrangola.school.domain.IdentObject;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCourseDAO extends BaseImplementation<Course> implements BaseDAO<Course> {

	public Map<Integer, Course> getCourses() {
		return data;
	}

	public void setCourses(Map<Integer, Course> courses) {
		this.data = courses;
	}
}