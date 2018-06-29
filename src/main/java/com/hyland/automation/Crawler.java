package com.hyland.automation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

public class Crawler implements Runnable {
	public static final Object LOCK = new Object();
	
	private static final Logger LOGGER = Logger.getLogger(Crawler.class);
	private String filePath;
	private String searchTerm;
	public Crawler(String filePath, String searchTerm) {
		this.searchTerm = searchTerm;
		this.filePath =filePath;
	}

	@Override
	public void run() {		
		String line = null;
		FileReader fr = null;
		try {
			fr = new FileReader(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		
		while(true){
			try {
				line = br.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();				
			}

			if (line == null)
			{
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
			else
			{
				if(line.contains(searchTerm)) {
					LOGGER.info(filePath + " : " + line);
				}
			}
		}
	}
}