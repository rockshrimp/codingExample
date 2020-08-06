package com.lucasplissonnier.codingexample.services;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
 
@Service("codingExampleService")
public class CodingExampleService {
	public void processInputFile(String inputFileName, String outputFileName, ArrayList<String> forbiddenWords) throws IOException {
		File inputFile = new File(inputFileName);
        if(inputFile.exists() && inputFile.isFile()) { 
        	
        	FileWriter outputFileWriter = new FileWriter(outputFileName);
        	
            // read file line by line 
        	try( Stream<String> lines = Files.lines(Paths.get(inputFileName))){
        		for(String line : (Iterable<String>) lines::iterator ) {
        			
        			writeToOutputFile(line, forbiddenWords, outputFileWriter);		 
        			
        		} 
        	} 
        	
        	outputFileWriter.close();
        } 
        else throw new FileNotFoundException("Failed to find the input file");	
	}
	
	public void writeToOutputFile(String word, ArrayList<String> forbiddenWords, FileWriter outputFileWriter) {
		if(!forbiddenWords.contains(word)) {
			try {
				outputFileWriter.write(word + System.lineSeparator());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

	
	
}