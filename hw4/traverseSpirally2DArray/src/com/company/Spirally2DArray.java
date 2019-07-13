package com.company;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
// EMRE KAVAK 151044085 HW4
// this class for create ıterator objects.
// firstly, you should create an object this class and you should enter 2d array into one parameter construction
public class Spirally2DArray implements Iterable {
    private int itArr[][];  // just for hold 2d array and use it when create an traverseSpirally2DArray  object for traverse and save element.
    public Spirally2DArray(int arr[][]){
        itArr = arr;
    }
    @Override
    public Iterator iterator() {
        return new traverseSpirally2DArray(itArr);  // traverseSpirally2DArray for travers array and save elements into an array
    }

    @Override
    public void forEach(Consumer consumer) {
    // empty because of I didn't use it
    }

    @Override
    public Spliterator spliterator() {
    // empty because of I didn't use it
        return null;
    }
}
