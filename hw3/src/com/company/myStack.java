package com.company;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Stack;


// I used String array for hold element like (10,12)
// I hold size and countElement for added element
public class myStack<T>{
    private T arr[];
    private int size = 10 ;
    private int countElement = 0;
    /////////  METHODS /////////
    public myStack() {
        arr = (T[]) new Object[size];
    }
    public T push(T element){

        if( size == countElement) {
            T temp2[] =(T[]) new Object[this.size];
            int i = 0;
            while (arr.length != i) {
                temp2[i] = this.arr[i];
                i++;
            }
            i = 0;
            this.size += this.size; //  new size will be doubled
            arr = (T[]) new Object[this.size];
            while (temp2.length != i) {
                this.arr[i] = temp2[i];
                i++;
            }
        }
        this.arr[countElement] = element;
        countElement++;
        return arr[countElement-1];
    }
    protected T pop(){    // last element will be "\0" and will be returned this element with temp
        int i = 0;
        T temp = null;
        if(countElement != 0) {
            temp = arr[this.countElement - 1];
            arr[this.countElement - 1] = (T) "\0";
            countElement--;
        }
        return temp;
    }
    protected T peek(){    // last element return
        T temp = arr[size-2];
        return temp ;
    }
    protected boolean empty(){  // according to countElement count, will return true or false
        if(countElement == 0) return true;
        return false;
    }
}
