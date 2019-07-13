// 151044085 EMRE KAVAK
package com.company;
import java.util.*;

public class File_Map implements Map {
    /*
    For this hashmap, you will use arraylists which will provide easy but costly implementation.
    Your should provide and explain the complexities for each method in your report.
    * */
    ArrayList<String> fnames;   //  bu 2 arraylist resizeable oldugu icin capacityle ugrasmıyorum.
    ArrayList<List<Integer>> occurances;

    public File_Map(){
        fnames = new ArrayList<String>();
        occurances = new ArrayList<List<Integer>>();
    }
    @Override
    public int size() {
        return fnames.size();
    }

    @Override
    public boolean isEmpty() {
        return fnames.size() == 0 ? true : false ;
    }

    @Override
    public boolean containsKey(Object key) {    // checking file name there is or not
        for(int i = 0; i< fnames.size(); i++){
            if(fnames.equals((String)key)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean containsValue(Object value) { // checking list
        List list = (List) value;
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
                if(list.size() == this.occurances.get(i).size()){
                        for(int k = 0; k< list.size();k++){
                            if(list.get(k) == this.occurances.get(i).get(k)){
                                counter++;
                            }
                        }
                        if(counter == list.size()){
                            return true;
                        }
                }
        }
        return false;
    }

    @Override
    public Object get(Object key) {     // RETURN OCCURANCES VALUE LİST
        for(int i = 0; i< this.occurances.size(); i++){
           if(fnames.get(i).equals((String)key) == true){
               return occurances.get(i); // return a list
            }
        }
        return null;
    }
    public void set(List oc,int position){  // UPDATE A LİST
        oc.add(position);
    }
    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
        fnames.add((String)key);
        occurances.add((List)value);
        return null;    // bu ne return edicek ?
    }

    @Override
    public Object remove(Object key) {  // word mapta class doesnt ımpelement it, so didnt implement it
        return null;
    }

    @Override
    public void putAll(Map m) {
        File_Map fmap= new File_Map();
        File_Map t = (File_Map)m;
        for(int  i = 0; i< m.size();i++){
             fmap .fnames= t.fnames;   // copy
            fmap.occurances = t.occurances;
        }
    }

    @Override
    public void clear() {
        this.fnames = new ArrayList<String>();
        this.occurances = new ArrayList<List<Integer>>();
    }

    @Override
    public Set keySet() {
        File_Map t = null;
        Set<String> set = null;
        for( int i = 0; i< this.fnames.size();i++){
            set.add(this.fnames.get(i));
        }
        return set;
    }

    @Override
    public Collection values() {
        return this.occurances;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    @Override
    public String toString() {
        //return super.toString();
        return fnames + " "+occurances;
    }
    public void print(){
        for(int i = 0; i < this.fnames.size();i++){
            System.out.println(fnames.get(i) + " " + occurances.get(i));
        }
    }
}