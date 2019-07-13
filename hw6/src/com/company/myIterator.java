package com.company;

import java.util.Iterator;

public class myIterator implements Iterator {
    Word_Map.Node current;
    public myIterator(){
        current = null;
    }
    public myIterator( Word_Map.Node obj ) {
        this.current = obj;
    }
    @Override
    public boolean hasNext() { // Override for itetator
        if(current!=null ) return true;
        else return false;
    }
    @Override
    public Word_Map.Node next() {
        Word_Map.Node temp = new Word_Map.Node();
        temp = current;
        current = current.nextWord;
        return temp;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
