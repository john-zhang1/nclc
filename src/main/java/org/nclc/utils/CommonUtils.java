package org.nclc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;


public class CommonUtils {

    public static Map<String, Integer> count(File file) throws FileNotFoundException, IOException{
        Map<String, Integer> map = new LinkedHashMap<>();

        FileInputStream fileStream = new FileInputStream(file); 
        InputStreamReader input = new InputStreamReader(fileStream); 
        BufferedReader reader = new BufferedReader(input);

        String line;
        int wordCount = 0; 
        int characterCount = 0; 
        int sentenceCount = 0;
        int paragraphCount = 1; 
        int whitespaceCount = 0;

        while((line = reader.readLine()) != null){
            if(line.equals("")){
                paragraphCount++; 
            }
            if(!(line.equals(""))){
                characterCount += line.length(); 
                String[] wordList = line.split("\\s+"); 
                wordCount += wordList.length; 
                whitespaceCount += wordCount -1; 
                String[] sentenceList = line.split("[!?.:]+"); 
                sentenceCount += sentenceList.length; 
            }
        }
        map.put("word", wordCount);
        map.put("character", characterCount);
        map.put("sentence", sentenceCount);
        map.put("paragraph", paragraphCount);
        map.put("whitespace", whitespaceCount);

        return map;
    }

}
