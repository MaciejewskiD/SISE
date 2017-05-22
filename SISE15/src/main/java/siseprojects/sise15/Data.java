package siseprojects.sise15;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data {

     public static int[][] loadFile(String path) throws FileNotFoundException {
         
        int[][] startingArray;
        int Xsize;
        int Ysize;

        File file = new File(path);
        Scanner inFile = new Scanner(file);
        String valuesLine;
        String[] values;

        valuesLine = inFile.nextLine();
        values = valuesLine.split(" ");

        Xsize = Integer.parseInt(values[0]);
        System.out.println(Xsize);

        Ysize = Integer.parseInt(values[1]);
        System.out.println(Ysize);

        startingArray = new int[Xsize][Ysize];
        int i = 0;

        while (inFile.hasNext()) {

            valuesLine = inFile.nextLine();
            System.out.println(valuesLine);
            values = valuesLine.split(" ");
            for (int j = 0; j < Xsize; j++)
                startingArray[i][j] = Integer.parseInt(values[j]);
            if (i == Ysize) {
                break;
            }
            i++;
        }
        inFile.close();
        return startingArray;
    
    }
     public static void saveFile(String pathToResult, String output, Integer length) throws FileNotFoundException {
        String outputPath = output;
        try (PrintWriter out = new PrintWriter(outputPath)) {
          
            out.println(length);
            out.println(pathToResult);
        }
    }
    
    public static void saveOutputFile(List<String> outputsList, String path, String algorithm) throws FileNotFoundException {
    	     FileWriter fw = null;

    	    try {
    	       fw = new FileWriter(algorithm+"."+path);
    	          } catch (IOException e) {
    	        e.printStackTrace();
    	     }
    	 
    	   BufferedWriter bw = new BufferedWriter(fw);
    	   try {
    	       for (int i = 0; i < outputsList.size(); i++) {
    	         bw.write(outputsList.get(i));
    	       }
    	     } catch (IOException e) {
    	        e.printStackTrace();
    	     }

    	     try {
    	        bw.close();
    	        fw.close();
    	     } catch (IOException e) {
    	           e.printStackTrace();
    	     }
    	  }

}
