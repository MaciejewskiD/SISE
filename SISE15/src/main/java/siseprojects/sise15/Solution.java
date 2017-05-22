/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siseprojects.sise15;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        {13, 14, 15, 0},};

    public static char[] directions = new char[]{'U', 'D', 'L', 'R'};
    private List<Node> visited;
    private boolean result;
    private int depth;
    private int calculated = 0;
    private int visitedNodes = 0;
    private String solutionPath;
    public List<String> outputList = new ArrayList<>();
    private String output;

    public void setOutput(String output) {
        this.output = output;
    }

    public void Astar(Node source, String heur) {
        Queue<Node> open = new LinkedList<>();
        List<Node> close = new ArrayList<>();
        Node tmp = null;
        int price = 222222;
        Node theBest = null;
        open.add(source);
        while (!open.isEmpty()) {
            for (int i = 0; i < open.size(); i++) {
                tmp = open.remove();
            for (int j = 0; j < tmp.getBoard().length; j++) {
                System.err.println(" ");
                for (int k = 0; k < tmp.getBoard().length; k++) {

                    System.err.println(tmp.getBoard()[j][k]);
                }
            }
//                System.err.println("PRICE "+ tmp.getCost());
                if (tmp.getCost() <= price) {
                    price = tmp.getCost();
                    close.add(theBest);
                    theBest = tmp;

                } else {
                    close.add(tmp);
                }
            }
//            for (int j = 0; j < theBest.getBoard().length; j++) {
//                System.err.println(" ");
//                for (int k = 0; k < theBest.getBoard().length; k++) {
//
//                    System.err.println(theBest.getBoard()[j][k]);
//                }
//            }
            Node actual = theBest;
//            if (!open.contains(actual)) {
//                close.add(actual);
//                visitedNodes++;
//            }
            if (Arrays.deepEquals(actual.getBoard(), resultArray)) {
                getPath(actual);
                result = true;
                break;
            } else {
                List<Node> children = new ArrayList<>();
                children = actual.generateChildren();

                open.clear();
                for (Node child : children) {
//                    for (int j = 0; j < child.getBoard().length; j++) {
//                        System.err.println(" ");
//                        for (int k = 0; k < child.getBoard().length; k++) {
//
//                            System.err.println(child.getBoard()[j][k]);
//                        }
//                    }
                    child.setCost(Manhattan.calculateDistance(child.getBoard()));
                    open.add(child);
                }

            }

        }

    }

    public void BFS(Node source, char[] order) {
        order = reverseTable(order);
        depth = 0;
        List<Node> children = new ArrayList<>();
        visited = new ArrayList<>();
        Queue<Node> nextToVisit = new LinkedList<>();
        nextToVisit.add(source);
        calculated = 1;
        visitedNodes = 0;
        result = false;
        do {
            Node actual = nextToVisit.remove();
            depth = getMaxDepth(depth, actual);
            if (!visited.contains(actual)) {
                visited.add(actual);
                visitedNodes++;
            }
            if (Arrays.deepEquals(actual.getBoard(), resultArray)) {
                result = true;
                getPath(actual);
                break;
            } else {
                children = actual.generateChildren();
                for (int i = 0; i < order.length; i++) {
                    for (Node child : children) {
                        if (order[i] == child.getDirection()) {
                            if (!visited.contains(child)) {
                                nextToVisit.add(child);
                                calculated++;
                            }
                        }
                    }
                }
            }

        } while (!nextToVisit.isEmpty());

        outputList.add("Glebokosc: " + depth + "\n");
        System.out.println("BFS glebokos przeszukiwania :  " + depth + "\n");
        if (!result) {
            System.out.println("Brak rozwiazania");
            outputList.add("-1");
        }
    }

    public void DFS(Node actual, char[] order) {
        order = reverseTable(order);
        int recursiveNum = 0;
        visited = new ArrayList<>();
        DFS(actual, order, recursiveNum);
        calculated = 1;
        visitedNodes = 0;
        if (!result) {

            outputList.add("-1");
        } else {
            outputList.add("Glebokosc: " + depth + "\n");
        }

    }

    public void DFS(Node actual, char[] order, int recursiveNum) {

        char[] nodeOrder = order;
        List<Node> children = new ArrayList<>();
        depth = getMaxDepth(depth, actual);
        if (!visited.contains(actual)) {
            visited.add(actual);
            visitedNodes++;
        }
        if (Arrays.deepEquals(actual.getBoard(), resultArray)) {
            result = true;
            getPath(actual);
        } else if (!result) {
            recursiveNum++;
            children = actual.generateChildren();
        }
        for (int i = nodeOrder.length - 1; i >= 0; i--) {
            if (!children.isEmpty()) {
                for (Node child : children) {
                    if (child.getDirection() == nodeOrder[i]) {
                        if (!visited.contains(child)) {
                            calculated++;
                            if (recursiveNum < 9) {//TO JEST MINIMALNA LICZBA REKURENCJI
                                DFS(child, order, recursiveNum);
                            }
                        }
                    }
                }
            }

        }
    }

    public void getPath(Node actual) {

        while (actual.haveParent()) {
            solutionPath += actual.getDirection();
            for (Node n : visited) {
                if (actual.getParentID() == n.getId()) {
                    actual = n;
                    break;
                }
            }

        }

        try {
            Data.saveFile(convertResultPath(solutionPath), output, convertResultPath(solutionPath).length());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Solution.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        outputList.add("Dlugosc rozwiazania: " + convertResultPath(solutionPath).length() + "\n");
        System.out.println("Droga jaka pokonalem aby zwyciezyc: " + resultPath(solutionPath));
        outputList.add("Odwiedzone: " + visitedNodes + "\n");
        System.out.println("Ilosc odwiedzonych wezlow: " + visitedNodes);
        outputList.add("Przetworzone: " + calculated + "\n");
        System.out.println("Ilosc przetworzonych wezlow: " + calculated);
    }

    public String convertResultPath(String resultPath) {
        String newResultPath = null;
        if (resultPath != null) {
            for (int i = resultPath.length() - 1; i >= 0; i--) {
                if (resultPath.charAt(i) == 'l') {
                    break;
                } else if (newResultPath == null) {
                    newResultPath = Character.toString(resultPath.charAt(i));
                } else {
                    newResultPath += resultPath.charAt(i);
                }
            }
            return newResultPath;
        } else {
            return "nie wygenerowalem zadnej drogi";
        }
    }

    public String resultPath(String resultPath) { ////?????????????????????????????????????
        String newResultPath = null;
        if (resultPath != null) {
            for (int i = resultPath.length() - 1; i >= 0; i--) {
                if (resultPath.charAt(i) == 'l') {
                    break;
                } else if (newResultPath == null) {
                    newResultPath = Character.toString(resultPath.charAt(i));
                } else {
                    newResultPath += resultPath.charAt(i);
                }
            }
            return newResultPath;
        } else {
            return "nie wygenerowalem zadnej drogi";
        }
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
