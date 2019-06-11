package com.matrangola.demo;

import java.util.*;

public class CollectionDemo {
	
	String[] classics = {"TI/99-4a", "Apple", "Apple II", "Apple //c", "PCjr"};
	
	public void demo() {
		Set<String> hasSet = new HashSet();
		for (String classic : classics) {
			hasSet.add(classic);
		}
		
		if (hasSet.contains("Apple")) System.out.println("Has Apple");
		if (hasSet.contains("Commodore 64")) System.out.println("Has C64");
		
		Iterator it = hasSet.iterator();
		while(it.hasNext()) {
			System.out.println("it = " + it.next());
		}
		
		for(String s : hasSet) {
			System.out.println("s = " + s);
		}
		
		hasSet.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		CollectionDemo ourDemo = new CollectionDemo();
		ourDemo.demo();
		
	}

}
