package com.hyland.automation;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LogMonitor {
	private static InputStream fis;
	private static Properties prop;
	private static int numOfFiles;
	private static String allLogFiles[]; 
	private static String searchedTerm[]; 
	
	public static void main(String[] args){
		try {
			fis = new FileInputStream(args[0]);
			if(fis == null) {
				System.out.println("Can not find data.properties");
				System.exit(1);
			}
			prop = new Properties();
			prop.load(fis);
			
			if(prop.containsKey("FILE_PATH")) {
				String logFiles = prop.getProperty("FILE_PATH");
				allLogFiles = logFiles.split(",");
				numOfFiles = allLogFiles.length;
			}
			
			if(prop.containsKey("SEARCHED_STRING")) {
				String searchedTerms = prop.getProperty("SEARCHED_STRING");
				searchedTerm = searchedTerms.split(",");
			}	
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		for(int i=0; i<numOfFiles; i++){
			String filePath = allLogFiles[i];
			System.out.println(filePath);
			String searchedString = searchedTerm[i];
			System.out.println(searchedString);
			new Thread(new Crawler(filePath, searchedString)).start();
		}
		System.out.println("Starting watcher");
		new Thread(new LogWatcher()).start();
	}
}