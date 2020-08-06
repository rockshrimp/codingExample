package com.lucasplissonnier.codingexample;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lucasplissonnier.codingexample.services.CodingExampleService; 
 
public class CodingExample {
 
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		// loading the definitions from the given XML file
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
 
		CodingExampleService service = (CodingExampleService) context
				.getBean("codingExampleService");
 
		String inputFile;
		ArrayList<String> forbiddenWords;
		String outputFile;
		
		//Get parameters
		if(args.length >= 2) {
			inputFile = args[0];
		   
			forbiddenWords = new ArrayList<String>();
			for(int i = 1; i < args.length; i++) {
				forbiddenWords.add(args[i]);
	        }
			outputFile = "output.txt";
		} else {
			String newline = System.getProperty("line.separator");
			throw new IllegalArgumentException("At least two parameters required :  " + newline
					+ "	- the name of the input file" + newline
					+ "	- the words to be filtered from the input file");
		}
		
		try {
			service.processInputFile(inputFile, outputFile, forbiddenWords);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("The file has been copied");
	}
}