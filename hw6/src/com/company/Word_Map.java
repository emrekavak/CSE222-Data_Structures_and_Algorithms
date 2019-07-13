// 151044085
package com.company;
import java.util.*;

public class Word_Map implements Map, Iterable {

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];
    protected int position = 0;  // for add position file map occurances field // temporarily change
    protected int size = 0;      // for added element size
    protected Node head;        // for linked structure
    public void setPosition(int pos){
        this.position = pos;
    }
    public Word_Map() {     // no parameter constructor
        this.table = new Node[INITCAP];
        head = null;
    }

    @Override
    public Iterator iterator() {
        return new myIterator(head);    // myIterator class implement iterator interface
    }
    ////////////// INNER CLASS NODE START///////////////
        static class Node {
            // complete this class according to the given structure in homework definition
            protected String key ;
            protected File_Map value;
            protected Node nextWord;    // for linked list structure

            public Node(){
                key = null;
                value = null;
                nextWord = null;
            }
            public Node(Object key, Object value){
                this.value = (File_Map) value;
                nextWord = null;
                this.key = (String) key;
            }

        @Override
        public String toString() {
            return this.key + " " + this.value ;
        }
    }
    ////////////// INNER CLASS NODE END ///////////////
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0 ? true : false;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */ // HATALIII
    public boolean containsKey(Object key) {    // this method contains given key index empty or not
        int index = this.hashCode((String) key)% this.CURRCAP;
        if(this.size >0) {
            if(table[index] != null ){
                    return true;
            }
        }
        return false;
    }

    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) { // USED LINKED LIST STRUCTURE
        Node t = this.head;
        while (t != null){
            for(int i = 0; i< t.value.fnames.size(); i++){
                if(t.value.fnames.get(i).equals((String) value)){
                    return true;
                }
            }
            t = t.nextWord;
        }
        return false;
    }
    @Override
    public Object get(Object key) { // value dondurecek
        int index = this.hashCode((String)key) % this.CURRCAP;
        return table[index].value;
    }
    public void setElement(String key, String fname){   // THIS METHOD USE FOR IF THERE IS WORD IN THE TABLE, IT WILL BE UPDATE WİTH NEW POSİTİON OR NEW FILE INFORMATİON
        Node temp = this.head;
        int flag = 0;
        while (flag == 0 && temp != null){
            if(temp.key.equals(key)){                   // CHECKING GIVEN WORD, BECAUSE OF CAN BE A COLLİSİON AND I MUST UPDATE RIGHT WORD
                if( temp.value.get(fname) != null) {    //
                    temp.value.set((List) temp.value.get(fname), position);
                }else{
                    List t = new ArrayList();    // IF WORD THERE İS AND ISNT CONTAİN THİS FNAME, IT WILL BE ADDED INTO WORD
                    t.add(this.position);
                    temp.value.put(fname,t);
                }
                flag = 1;
            }
            temp = temp.nextWord;
        }
        if(flag == 0){      // IF THERE WAS COLLİSİON AND GIVEN WORD DOESNT EXİSTS, İT WİLL BE ADDED
            File_Map fobject = new File_Map();
            fobject.fnames.add(fname);
            List t = new ArrayList();
            t.add(this.position);
            fobject.occurances.add(t);
            this.put(key,fobject);
        }
        size++;
    }
    @Override
    /*
    Use linear probing in case of collision
    key is word name, value is file_map object
    * */
    public Object put(Object key, Object value) {   // file mapsları linked list ile baglıcam
        double checkLoadFact = (1.0*size) / (double)this.CURRCAP ;  // with hashCode method, index calculate
        if(checkLoadFact > LOADFACT) {      // firstly checking load factor
            this.reHash();                  // calling rehash method
        }
        Node temp = new Node(key,value);  // new word element create
        temp.nextWord = null;
        int index = this.hashCode((String) key) % this.CURRCAP;
        if(table[index] == null ) {     // IF THE INDEX EMPTYİ IT WILL BE ADD
            table[index] = temp;
        }
        else {              // ıf index is not empty, linear probing will apply
            int flag = 0;
            while (flag == 0){
                if(index + 1 < this.CURRCAP) index++;
                else index = 0;
                if(table[index] == null){
                    table[index] = temp;
                    flag = 1;
                }
            }
        }
        size++;
        if(this.head == null){  // linked list head
            head = temp;
        }
        else{
            Node t = this.head;
            while (t.nextWord !=null ){
                t = t.nextWord;
            }
            t.nextWord = temp;
        }
        return null;
    }
    public void reHash(){       // this method update capacity doubled
        this.CURRCAP = this.CURRCAP*2;
        Node[] temp = new Node[this.CURRCAP];
        Node t = this.head;
        while (t !=null) {
            int index = this.hashCode((String) t.key) % this.CURRCAP; // new index created
            temp[index] = t; // adding elements into temp array
            t = t.nextWord;
        }
        this.table = new Node[this.CURRCAP];
        this.table = temp;
    }
    @Override
    /*You do not need to implement remove function
     * */
    public Object remove(Object key) {
        return null;
    }
    @Override
    public void putAll(Map m) {
        this.table = new Node[m.size()];
        for(int  i = 0; i< m.size();i++){
            table[i].value = (File_Map) m.get(i);   // copy
        }
    }
    @Override
    public void clear() {
        this.table = new Node[CURRCAP];
    }
    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
        Node t = head;
        Set<String> set = null;
        while (t!=null){
            set.add(t.key);
            t = t.nextWord;
        }
        return set;
    }
    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {  // values ler ne ?
        Node t = head;
        ArrayList<ArrayList<String>> arr = null;
        while (t!=null){
            arr.add(t.value.fnames);
            t = t.nextWord;
        }
        return arr;
    }
    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }
    public static int hashCode(String key){     // this method calculate hashCode
        int n = key.length();
        int hashCode =0;
        for(int i = 0; i< key.length() ;i++){
            n--;
            int power31 =1;
            for(int k =n ; k>0;k--){
                power31 *= 31;          // power calculate
            }
            hashCode += key.charAt(i)*power31; // multiple and sum
        }
        return Math.abs(hashCode);
    }
    @Override
    public String toString() {
        return table.toString();
    }
}