package siseprojects.sise15;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {

    public static int[][] loadDataFromFile(String filename) throws FileNotFoundException {

        File file = new File(filename);
        ArrayList<ArrayList<Double>> arrayList = new ArrayList<ArrayList<Double>>();
        Scanner input;
        input = new Scanner(file);
        Scanner colReader = new Scanner(input.nextLine());
        while (input.hasNextLine()) {
            colReader = new Scanner(input.nextLine());
            ArrayList<Double> col = new ArrayList<Double>();
            while (colReader.hasNextDouble()) {
                col.add(colReader.nextDouble());
            }
            arrayList.add(col);
            colReader.close();
        }
        input.close();
        Double[][] tmp = new Double[arrayList.size()][arrayList.get(0).size()];
        int[][] data = new int[arrayList.size()][arrayList.get(0).size()];
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).toArray(tmp[i]);

        }
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[i].length; j++) {
                data[i][j] = tmp[i][j].intValue();
            }
        }

        return data;
    }

}
