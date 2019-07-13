package com.company;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;

public class PriorityQueue implements Comparator<Color>, Queue<Color> {
    protected Color binaryHeapArr[];
    protected int size;
    protected int capacity;
    protected Comparator<Color> comparator ;
    public PriorityQueue(Comparator<Color> comp) {
        size = 0;
        capacity = 100;
        binaryHeapArr = new Color[capacity];
        this.comparator = comp;
    }
    public Color getBinaryHeapArr(int index){
        if(index < size) return binaryHeapArr[index];
        Color temp = new Color(23,32,43);
        return temp;
    }
    public void setBinaryHeapArr(int index, Color pixel){
        if(index < size) binaryHeapArr[index] = pixel;
    }
    public void setSize() {
        this.size++;
    }
    public void setCapacity() {
        this.capacity++;
    }
    public int getSize() {
        return size;
    }
    public int getCapacity() {
        return capacity;
    }

    public PriorityQueue(){
        size = 0;
        capacity = 100;
        binaryHeapArr = new Color[capacity];
    }
    public void swap(Integer parent, Integer childs){
        Color temp = binaryHeapArr[parent];
        binaryHeapArr[parent] = binaryHeapArr[childs];
        binaryHeapArr[childs] = temp;

    }
    @Override
    public boolean add(Color data){
        if(size == capacity){
            Color[] temp = new Color[capacity];
            temp = binaryHeapArr;
            capacity = 2*capacity;
            binaryHeapArr = new Color[capacity];
            for(int i = 0; i< temp.length;i++){
                binaryHeapArr[i] = temp[i];
            }
        }
        //System.out.print("size : " + size);
        binaryHeapArr[size] = data;
        size++;
        int childs = size -1 ;
        int parent = ( childs - 1 ) / 2;   /// parent = size-1/2;

        while(compare(binaryHeapArr[parent], binaryHeapArr[childs]) < 0 && parent >= 0) {  // compare i burada kullanıcam dostum
            swap(parent,childs);
            childs = parent ;
            parent = ( childs -1 ) / 2;
        }
        return true;
    }

    public Color remove(Integer index) {

        int i = 0, k = 1;
        Color pixel = new Color(1,2,3);
        pixel = binaryHeapArr[index];
        if( index == 0 ) {
            i = 1;
            k = 0;
        }
        if(index == size-1 ){
            i = 0;
            k= 2;
        }
        Color[] temp = new Color[size];
        for (; i < size -1; i++) {
            temp[i] = binaryHeapArr[i];
        }
        size--;
        //if(size < 0) size = 0;
        if( size > 0) {
            binaryHeapArr = temp;
        }
        if(size == 0) {
            temp = new Color[capacity];
            binaryHeapArr = temp;
        }
        return pixel;
    }
    @Override
    public Color remove(){ // remove max element and return int, if queue is empty throw NosuchElementException
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // Save the top of the heap.
        Color root = this.getBinaryHeapArr(0);
        // If only one item then remove it.
        if (size == 1) {
            this.remove(0);  // baştan sil
            return root;
        }
        // Remove the last item from the ArrayList and place it into
        //the first position
        Color element = remove(size - 1); // last element remove
        this.setBinaryHeapArr(0, element);
        // The parent starts at the top.
        int parent = 0;
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= size) {
                break; // Out of heap.
            }
            int rightChild = leftChild + 1;
            int maxChild = leftChild; // Assume leftChild is smaller.
            // See whether rightChild is smaller.
            if (rightChild < size
                    && compare(this.getBinaryHeapArr(leftChild),
                    this.getBinaryHeapArr(rightChild)) < 0) {
                maxChild = rightChild;
            }
            // assert: minChild is the index of the smaller child.
            // Move smaller child up heap if necessary.
            if (compare(this.getBinaryHeapArr(parent),
                    this.getBinaryHeapArr(maxChild)) < 0) {
                swap(parent, maxChild);
                parent = maxChild;
            } else { // Heap property is restored.
                break;
            }
        }
        return root;
    }

    @Override
    public Color poll() {
        if (isEmpty()) {
            return null;
        }
        // Save the top of the heap.
        Color root = this.getBinaryHeapArr(0);
        // If only one item then remove it.
        if (size == 1) {
            this.remove(0);  // baştan sil
            return root;
        }
        // Remove the last item from the ArrayList and place it into
        //the first position
        Color element = remove(size - 1); // last element remove
        this.setBinaryHeapArr(0, element);
        // The parent starts at the top.
        int parent = 0;
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= size) {
                break; // Out of heap.
            }
            int rightChild = leftChild + 1;
            int maxChild = leftChild; // Assume leftChild is smaller.
            // See whether rightChild is smaller.
            if (rightChild < size
                    && compare(this.getBinaryHeapArr(leftChild),
                    this.getBinaryHeapArr(rightChild)) < 0) {
                maxChild = rightChild;
            }
            // assert: minChild is the index of the smaller child.
            // Move smaller child up heap if necessary.
            if (compare(this.getBinaryHeapArr(parent),
                    this.getBinaryHeapArr(maxChild)) < 0) {
                swap(parent, maxChild);
                parent = maxChild;
            } else { // Heap property is restored.
                break;
            }
        }
        return root;
    }

    @Override
    public boolean offer(Color data) {
        if(size == capacity){
            /// new capacity
        }
        binaryHeapArr[size] = data;
        size++;
        int childs = size -1 ;
        int parent = ( childs - 1 ) / 2;   /// parent = size-1/2;

        while(compare(binaryHeapArr[parent], binaryHeapArr[childs]) < 0 && parent >= 0) {  // compare i burada kullanıcam dostum
            swap(parent,childs);
            childs = parent ;
            parent = ( childs -1 ) / 2;
        }
        return true;
    }

    public void print(){
        for(int i = 0; i< size; i++){
            System.out.print(binaryHeapArr[i] + "\n");
        }
    }
    @Override
    public int compare(Color left, Color right) {
        if(comparator != null ){
            return comparator.compare(left,right);
        }
        //else return ((Comparable<Integer>) left).compareTo(right);
        return  0;
    }
    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Color> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super Color> predicate) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean isEmpty(){
        return size > 0 ? false : true;
    }

    @Override
    public Color peek(){ // return smallest element without remove it, if queue is empty, return null
        if(isEmpty()) return null;
        Color temp = binaryHeapArr[0];
        return temp;
    }
    @Override
    public Color element(){ // return smallest element without remove it, if queue is empty, throw NosuchElementException
        if(isEmpty()) throw new NoSuchElementException();
        Color temp = binaryHeapArr[0];
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Color> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }
}
