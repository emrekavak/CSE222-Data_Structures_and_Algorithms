package com.company;
import java.util.Iterator;
// EMRE KAVAK 151044085 HW4
// this class actually do every travers operations and add elements into array.
// also have, next and hasNext override methods

public class traverseSpirally2DArray implements Iterator{
    private  int listElements[];
    private int size = 0;
    private int index = 0;
    public traverseSpirally2DArray(int[][] arr){    // constructor
        listElements = new int[arr.length*arr[0].length];   // memory allocation with 2d size
        this.traverseArray(arr,0,0);            // all actions done in this function with helper functions
    }
    public traverseSpirally2DArray(){                       // no parameter constructor
        /// null
    }
    private void addTopright(int arr[][],int row, int column){  // add element in listElements array top rigt to left
        if(arr[row][column] != -1){                             // if this element is -1, this means this were visited
            listElements[size] = arr[row][column];
            arr[row][column] = -1;                              // after add element into array, I  added -1 because of visited.
            size++;
        }
        if(column+1 < arr[0].length) addTopright(arr,row,column+1); // recursive
    }
    private void addTopDown(int arr[][],int row, int column){   // this function add elements top to down with recursive
        if(row < arr.length && column<arr[0].length) {          // checking coordinate for not to exceed size
            if(size < arr.length*arr[0].length )
                if(arr[row][column] != -1){                     // same other functions
                    listElements[size] = arr[row][column];
                    arr[row][column] = -1;
                    size++;
                }
        }
        if(row+1 < arr.length) addTopDown(arr,row+1,column); // recursive
    }
    private void addRightToLeft(int arr[][],int row, int column){   //add elements <- right to left with recursively
        if(row < arr.length && column>= 0) {
            if(size < arr.length*arr[0].length ){
                if(arr[row][column] != -1){
                    listElements[size] = arr[row][column];          // same with other functions
                    arr[row][column] = -1;
                    size++;
                }
            }
        }
        if(column-1 >= 0) addRightToLeft(arr,row,column-1); // recursive
    }
    private void addDownToUp(int arr[][],int row, int column){  // ^ add element down to top with recursively
        if(row > 0 && column<arr[0].length) {
            if(size < arr.length*arr[0].length ) {
                if(arr[row][column] != -1){
                    listElements[size] = arr[row][column];
                    arr[row][column] = -1;
                    size++;
                }
            }
        }
        if(row-1 >0) addDownToUp(arr,row-1,column);     // recursive
    }
    @Override
    public boolean hasNext() {
        if(index<size) return true;     // size is number of added elements, index is for iteration
        return false;
    }
    @Override
    public Integer next() {
        int temp = listElements[index];
        index++;
        return temp;    // next element return
    }
    private int traverseArray(int arr[][],int row,int column){
        addTopright(arr,row,column);    // -> add elements left to right on top row
        addTopDown(arr,row+1,arr[0].length-column-1);   // add elements top to down recursively
        addRightToLeft(arr,arr.length-row-1,arr[0].length-2-column);    // add last row into listArray recursively
        addDownToUp(arr,arr.length-2-row,column);   // add elements down to top into listArray recursively
        if(size < arr.length*arr[0].length && row+1 < arr.length && column+1 <arr[0].length) traverseArray(arr,row+1,column+1);
        return 0;
    }
}
