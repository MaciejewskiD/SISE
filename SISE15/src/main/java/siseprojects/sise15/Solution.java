/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siseprojects.sise15;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wojta
 */
public class Solution {
    
    public static int[][] resultArray = new int[][]{
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 0},
    };
    
    public static char[] directions = new char[]{'G', 'D', 'L', 'P'};
    private List<Node> visited;
    private boolean result;
    private int depth;
    private int calculated = 0;
    private int visitedNodes=0;
    private String solutionPath;
    public List<String> outputList = new ArrayList<String>();
    
    public void DFS(Node actual , char[] order){
        order = reverseTable(order);
        int recursiveNum=0;
        visited = new ArrayList<>();
        DFS(actual, order, recursiveNum);
        
    }
    
    public void DFS(Node actual, char[] order, int recursiveNum){
        
        char[] nodeOrder = order;
        List<Node> children = new ArrayList<>();
        depth = getMaxDepth(depth, actual);
        if(!visited.contains(actual)){
            visited.add(actual);
            visitedNodes ++;
        }
        if(Arrays.deepEquals(actual.getBoard(), resultArray)){
            result = true;
            getPath(actual);
        }
        else if(!result){
           recursiveNum++;
           children = actual.generateChildren();           
        }
        for(int i=nodeOrder.length -1 ; i>=0 ; i--){
            if(!children.isEmpty()){
                for(Node child : children){
                    if(child.getDirection() == nodeOrder[i]){
                       if(!visited.contains(child)){
                           calculated++;
                           if(recursiveNum < 21){//TO JEST MINIMALNA LICZBA REKURENCJI
                              DFS(child, order,recursiveNum);
                       } 
                    }
                }
            }
        }
             
     }
    }    
    public void getPath(Node actual){
        
        while(actual.haveParent()){
            solutionPath += actual.getDirection();
            for(Node n : visited){
                if(actual.getParentID() == n.getId()){
                    actual = n;
                    break;
                }
            } 
            
        }
        
        try {
            Data.saveFile(solutionPath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Droga jaka pokonalem aby zwyciezyc: " + resultPath(solutionPath));
        outputList.add("Odwiedzone: " + visitedNodes + "\n");
        System.out.println("Ilosc odwiedzonych wezlow: " + visitedNodes);
        outputList.add("Przetworzone: " + calculated + "\n");
        System.out.println("Ilosc przetworzonych wezlow: " + visitedNodes);
    }
    
    public String resultPath(String resultPath) { ////?????????????????????????????????????
        String newResultPath = null;
        if (resultPath != null) {
            for (int i = resultPath.length() - 1; i >= 0; i--) {
                if (resultPath.charAt(i) == 'l') {
                    break;
                } else if (newResultPath == null) {
                    newResultPath = Character.toString(resultPath.charAt(i));
                } else
                    newResultPath += resultPath.charAt(i);
            }
            return newResultPath;
        } else
            return "nie wygenerowalem zadnej drogi";
    }
    
    public int getMaxDepth(int depth, Node node) {
        if (depth < node.getDepth()) {
            depth = node.getDepth();
        }
        return depth;
    }
     public char[] reverseTable(char[] table) {
        char[] tmp = new char[table.length];
        for (int i = 0; i < table.length; i++) {
            tmp[table.length - 1 - i] = table[i];
        }
        return tmp;
    }
}
