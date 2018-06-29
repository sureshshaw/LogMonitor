package com.hyland.automation;

public class LogWatcher implements Runnable{

	@Override
	public void run() {		
		while(true) {
			if(isChanged()) {
				synchronized (Crawler.LOCK) {
					System.out.println("Watcher notified");
					Crawler.LOCK.notifyAll();
					try {
						Crawler.LOCK.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private boolean isChanged() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;	
	}
}
