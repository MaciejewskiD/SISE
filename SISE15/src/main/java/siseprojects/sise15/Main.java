/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siseprojects.sise15;

import java.io.FileNotFoundException;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
       int[][] table = new int[4][4];
       table = Data.loadFile("file");
        for (int i = 0; i < table.length; i++) {
            System.err.println();
            for (int j = 0; j < 4; j++) {
                System.err.print(table[i][j] + " ");
            }
            
        }
    }
    
}
