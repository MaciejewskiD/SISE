/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siseprojects.sise15;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author wojta
 */
public class Node {
    
    private int[][] board;
    private int id;
     private int parentID;
    private int boardLengthY;
    private int boardLengthX;
    
    private int zeroX;
    private int zeroY;
    private char direction; 
    private int depth;
    private boolean haveParent;
    
    public Node(int[][] board){
        this.board = board;
        this.boardLengthX = board[0].length;
        this.boardLengthY = board.length;
        this.haveParent = false; 
        this.depth = 0;
        this.id = hashCode();
        getZero();
    }
    
    public Node(int[][] board, char direction , int parentID, int depth ){
        
        this.depth = depth + 1;
        this.board = board; 
        this.direction = direction;
        this.depth = depth;
        this.parentID = parentID;
        this.boardLengthX = board[0].length;
        this.boardLengthY = board.length;
        this.haveParent = true; 
        this.id = hashCode();
        getZero();
        
    }
    
    public void getZero(){
        for(int i=0 ; i< boardLengthY ; i ++){
            for (int j = 0; j < boardLengthX; j++) {
                if(board[i][j] == 0){
                    zeroX =j;
                    zeroY = i;
                    break;
                }
                
            }
        }       
    }
    public boolean canMoveUp(){
        if(zeroY != 0) return true;
        else return false;     
    }
    
    public boolean canMoveDown(){
        if(zeroY != boardLengthY -1 ) return true;
        else return false;    
    }
    
    public boolean canMoveLeft(){
        if(zeroX != 0 ) return true;
        else return false;
    }
    
    public boolean canMoveRight(){
        if(zeroX !=  boardLengthX -1) return true;
        else return false;        
    }
    
    public int[][] moveElement(char direction) {
    	int[][] childsArray = new int[boardLengthY][boardLengthX];

        for (int i = 0; i < boardLengthX; i++) {
            System.arraycopy(board[i], 0, childsArray[i], 0, boardLengthY);
        }

        int temp = childsArray[zeroY][zeroX];
        switch (direction) {
            case 'G':
                childsArray[zeroY][zeroX] = childsArray[zeroY - 1][zeroX];
                childsArray[zeroY - 1][zeroX] = temp;
                break;
            case 'D':
                childsArray[zeroY][zeroX] = childsArray[zeroY + 1][zeroX];
                childsArray[zeroY + 1][zeroX] = temp;
                break;
            case 'P':
                childsArray[zeroY][zeroX] = childsArray[zeroY][zeroX + 1];
                childsArray[zeroY][zeroX + 1] = temp;
                break;
            case 'L':
                childsArray[zeroY][zeroX] = childsArray[zeroY][zeroX - 1];
                childsArray[zeroY][zeroX - 1] = temp;
                break;
            default:
                break;
        }
        return childsArray;
    }
    
    public ArrayList<Node> generateChildren() {
        
        ArrayList<Node> childerNodes = new ArrayList<Node>();
        
        if (canMoveUp()) {
            childerNodes.add(new Node(moveElement('G'), 'G', id , depth));
        }
        if (canMoveDown()) {
            childerNodes.add(new Node(moveElement('D'), 'D', id, depth));
        }
        if (canMoveLeft()) {
            childerNodes.add(new Node(moveElement('L'), 'L', id, depth));
        }
        if (canMoveRight()) {
            childerNodes.add(new Node(moveElement('P'), 'P', id, depth));
        }
        return childerNodes;
    }

    
     @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(board);
        return result;
    }
    
     public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Node other = (Node) obj;
                 return Arrays.deepEquals(board, other.board);

    }
     
     
    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getBoardLengthY() {
        return boardLengthY;
    }

    public void setBoardLengthY(int boardLengthY) {
        this.boardLengthY = boardLengthY;
    }

    public int getBoardLengthX() {
        return boardLengthX;
    }

    public void setBoardLengthX(int boardLengthX) {
        this.boardLengthX = boardLengthX;
    }

    public int getZeroX() {
        return zeroX;
    }

    public void setZeroX(int zeroX) {
        this.zeroX = zeroX;
    }

    public int getZeroY() {
        return zeroY;
    }

    public void setZeroY(int zeroY) {
        this.zeroY = zeroY;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean haveParent() {
        return haveParent;
    }

    public void setHaveParent(boolean haveParent) {
        this.haveParent = haveParent;
    }
     
}

//wwwwwwwwwwwwwwwww