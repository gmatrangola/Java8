package com.matrangola.school.dao.inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matrangola.school.dao.BaseDAO;
import com.matrangola.school.domain.Course;
import com.matrangola.school.domain.IdentObject;

public class BaseImplementation<T extends IdentObject> {

	private static int nextId = 0;
	Map<Integer, T> data = new HashMap<Integer, T>();

	public BaseImplementation() {
		super();
	}

	public void update(T updateObject) {
		if(data.containsKey(updateObject.getId())) {
			data.put(updateObject.getId(), updateObject);
		}
	}

	public void delete(T item) {
		data.remove(item.getId());
	}

	public T create(T newObject) {
		//Create a new Id
		int newId = nextId++;
		newObject.setId(newId);
		data.put(newId, newObject);
		
		return newObject;
	}

	public T get(int id) {
		return data.get(id);
	}

	public List<T> getAll() {
		return new ArrayList<T>(data.values());
	}

	public void deleteStore() {
		data = null;
	}

	public void createStore() {
		data = new HashMap<Integer, T>();
	}

}