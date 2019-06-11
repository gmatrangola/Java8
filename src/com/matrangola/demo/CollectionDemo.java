package com.matrangola.demo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingByConcurrent;

import java.io.IOException;


public class CollectionDemo {
	
	@FunctionalInterface
	public interface Printish {
		String printit(String message, String value);
	}
	
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
		
		hasSet.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println("forEach: " + t);
				
			}
		});
		
		hasSet.forEach((s) -> { 
			System.out.println ("s -> "+ s);
		});
		
		hasSet.forEach((String s) -> System.out.println("oneliner: " + s));
		hasSet.forEach(s -> System.out.println("no peren" + s));
		hasSet.forEach(this::info);
		
		
		info(hasSet, "From Lambda: ", (msg, val) -> {
			String big = msg.toUpperCase();
			String small = msg.toLowerCase();
			return big + small + " --- " + val;	
		});
	}
	
	public void info(String s) {
		System.out.println("Info: " + s);
	}
	
	public void info(Set<String> toPrint, String message, Printish printIt) {
		for(String val : toPrint) {
			System.out.println(printIt.printit(message, val));
		}
	}
	
	public void demoList() {
		List<String> computers = new ArrayList<>();
		for (String classic : classics) {
			computers.add(classic);
		}
		
		System.out.println("first + " + computers.get(0));
	}
	
	public static class Computer {
        String name;
        int ram;

        public Computer(String name, int k) {
            this.name = name;
            ram = k;
        }
    }
	
	public void demoText() throws IOException {
		Path path = Paths.get("Sherlock.txt");
		
		long start = System.currentTimeMillis();
		Map<String, Long> wordCount = Files.lines(path).parallel()
//				.peek(line -> {
//					System.out.println(Thread.currentThread().getId() + ": " + line);
//				})
				.flatMap(line -> Arrays.stream(line.trim().split("\\s")))
				.map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
				.filter(word -> {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return word.length() > 0;
				})
				.parallel()
				.peek(word -> {
					System.out.println(Thread.currentThread().getId() + ": " + word);
				})
				.map(word -> new AbstractMap.SimpleEntry<>(word, 1))
				.collect(groupingByConcurrent(AbstractMap.SimpleEntry::getKey, counting()));
		
		long time = System.currentTimeMillis() - start;
		
//		wordCount.forEach((k,v) -> System.out.println(String.format("%s : %d", k, v)));
		System.out.println("Time: " + time);
	}

	
	public void demoMap() {
        Map<String, Computer> computerMap = new HashMap<>();

        addComputer(computerMap, "TI/99-4a", 8);
        addComputer(computerMap, "Apple", 8);
        addComputer(computerMap, "Apple II", 16);
        addComputer(computerMap, "Apple //c", 128);
        addComputer(computerMap, "PCjr", 256);

        Computer pcJr = computerMap.get("PCjr");
        System.out.println("Memory for PCjr = " + pcJr.ram + "k");

        for (Map.Entry<String, Computer> entry : computerMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().ram + "k");
        }

	}
	
	private static void addComputer(Map<String, Computer> computerMap, String name, int k) {
        computerMap.put(name, new Computer(name, k));
    }
	
	public void demoStream() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Stream<Integer> stream = list.stream();
		
		int sum = stream.reduce(0, (a, b) -> a+b);
		System.out.println("sum = " + sum);
		
		int sum2 = 0;
		for(Integer val : list) {
			sum2 += val;
		}
		System.out.println("sum 2 = " + sum2);
		
	}
	
	public static void main(String[] args) throws IOException {
		CollectionDemo ourDemo = new CollectionDemo();
		// ourDemo.demo();
		// ourDemo.demoList();
		// ourDemo.demoMap();
		// ourDemo.demoStream();
		ourDemo.demoText();
	}

}
