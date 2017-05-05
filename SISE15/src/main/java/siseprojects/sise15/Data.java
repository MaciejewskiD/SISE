package siseprojects.sise15;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Data {
    public static int[][] loadDataFromFile(String filename) throws FileNotFoundException{

        File file = new File(filename);
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
        Scanner input;
        input = new Scanner(file);
        while(input.hasNextLine()){
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList<Integer> col = new ArrayList<Integer>();
            while(colReader.hasNextDouble())
            {
                col.add(colReader.nextInt());
            }
            arrayList.add(col);
            colReader.close();
        }
        input.close();
        int[][] tmp = new int[arrayList.size()][arrayList.get(0).size()];
        int[][] data = new int[arrayList.size()][arrayList.get(0).size()];
        for(int i=0;i<arrayList.size();i++)
            arrayList.get(i).toArray(tmp);
        for(int i=0;i<tmp.length;i++)
            for(int j=0;j<tmp[i].length;j++)
                data[i][j] = tmp[i][j];
        
//          for (int i = 0; i < data.length; i++) {
//                System.err.println(" ");
//                for (int j = 0; j < data[i].length; j++) {
//
//                System.err.print(data[i][j]+" ");
//            }
//        }
        return data;
    }

}

