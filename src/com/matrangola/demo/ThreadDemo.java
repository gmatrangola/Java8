package com.matrangola.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {
	
	public void traditional() {
		Thread thread = new Thread(() ->  {
			for(int i = 0; i<10; i++) {
				System.out.println("Running in thread: " + Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
		System.out.println("Running Thread from owener thread: " + Thread.currentThread().getId());
	}
	
	public void executorDemo() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(10, r -> {
            Thread thread = new Thread(r);
            thread.setName("Demo");
            thread.setDaemon(true);
            return thread;
        });
		ScheduledFuture<?> result = executor.scheduleAtFixedRate( () -> {
			System.out.println("Running in thread: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
			try {
				Thread.sleep(1000); // simulate work
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}, 0, 1, TimeUnit.SECONDS);
		
		
	}

	public static final void main(String[] args) {
		ThreadDemo threadDemo = new ThreadDemo();
		// threadDemo.traditional();
		threadDemo.executorDemo();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
