/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siseprojects.sise15;

import java.io.FileNotFoundException;
import java.util.Date;

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
      System.out.println("\n");
      System.out.println("Podaj odpowiednie argumenty: \n" +
                         "1. Strategia poszukiwania rozwiazania  \n" +
                         "2. Porzadek przeszukiwania (np. RLUD) lub heurystyka (manh lub hamm) \n" +
                         "3. Plik wejsciowy z pomieszana ukladanka \n" +
                         "4. Plik wyjsciowy z rozwiazaniem \n" +
                         "5. Plik wyjsciowy z dodatkowymi informacjami \n");
        
       String source;
       String solutionOutput;
       String algorithm;
       String statsOutput;
       char[] order = null;
       Solution solver=  new Solution();
       
       if(args.length ==5){
           algorithm = args[0];
           if(args[1].equals("hamm")||args[1].equals("manh")){
               //odpal heurystyke
           }
           else{
               order = args[1].toCharArray();
           }
           source = args[2];
           solutionOutput = args[3];
           solver.setOutput(solutionOutput);
           statsOutput = args[4];
           
           Date time1 = new Date();
           switch(algorithm){
               
               case "dfs":
                solver.DFS(new Node(Data.loadFile(source)), order);
                 break;
               
               case "bfs":
                   solver.BFS(new Node(Data.loadFile(source)), order);
                   break;
                      
           }
           Date time2 = new Date();
           float difference = time2.getTime() - time1.getTime();
           solver.outputList.add("Czas przetwarzania: " + difference + " ms");
           Data.saveOutputFile(solver.outputList, statsOutput, algorithm);           
           
       }
       else System.out.println("Nie podano odpowiednich argumentow wywolania!");
    }
    
}
