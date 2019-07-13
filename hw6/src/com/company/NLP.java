package com.company;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NLP {
    public Word_Map wmap;
    public int totalNumDocuments = 0;
    public ArrayList<String> fileNames;     // HOLDS FILE NAMES , FOR CALCULATE IDF
    public ArrayList<Integer> fileWordSize; // HOLDS COUNT OF WORD IN A FILE, FOR CALCULATE IDF
    public NLP(String dirName) throws FileNotFoundException {
        wmap = new Word_Map();
        fileNames = new ArrayList<String>();
        fileWordSize = new ArrayList<Integer>();
        readDataset(dirName);
    }
    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */
    public void readDataset(String dir) throws FileNotFoundException {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();  // ALL DIRECTORY FILES INSERTED
        for(int i = 0 ; i< listOfFiles.length ;i++){ // ALL FILES READ IN THERE
            File f = listOfFiles[i];
            fileNames.add(f.getName());
            totalNumDocuments++;        // FOR CALCULATE IDF
            FileReader fr = new FileReader(f);
            BufferedReader buffer = new BufferedReader(fr);
            String line = "";
            int counter = 1;
            while(true){    // LOOP FOR READ A FILE
                try {
                    if (!((line = buffer.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] splitStr = line.trim().split("\\s+");                          // TRIM STRING WORDS INTO ARRAY
                for(int k = 0; k< splitStr.length;k++) {                                  // READ WORD BY WORD
                    splitStr[k].replaceAll("\\s+","");                              // TRIM WORD INTO SPACE, PUNT.
                    if(line.trim().length() != 0) {  // line bos degılse
                        splitStr[k] = splitStr[k].trim().replaceAll("\\p{Punct}", "");
                        wmap.setPosition(counter);                                          // FOR ADD POSİTİON İNTO FİLE_MAP OBJECT LİST
                        if(wmap.containsKey(splitStr[k]) == true){                          // IF THE WORD ADDED BEFORE, I WILL ADD JUST FILE NAME OR NEW POSİTİON FOR SAME FILE
                            wmap.setElement(splitStr[k],f.getName());                       // THİS METHOD SET EXİSTİNG WORD
                        }else{
                            File_Map fobject = new File_Map();
                            List temp = new ArrayList();
                            temp.add(counter);
                            fobject.put(f.getName(),temp);  // USING FILE_MAP PUT METHOD
                            wmap.put(splitStr[k],fobject);  // USING WMAP PUT METHOD
                        }
                        counter++;                          // COUNTER FOR ADD POSITION A WORD
                    }
                }
            }
            fileWordSize.add(counter);  // FOR CALCULATE IDF
        }
    }
    public void readInput(String inputFile) throws IOException {
        File f = new File(inputFile);
        FileReader fr = new FileReader(f);
        BufferedReader buffer = new BufferedReader(fr);
        String line = "";
        while((line = buffer.readLine()) != null){
            //System.out.println(line);
            String[] splitLine = line.trim().split("\\s+");
            for(int i = 0; i< splitLine.length; i++){
                if(splitLine[i].equals("bigram") == true){
                    this.bigrams(splitLine[i+1]);
                }
                else if(splitLine[i].equals("tfidf") == true){
                    this.tfIDF(splitLine[i+1], splitLine[i+2]);
                }
            }
        }
    }
    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word){
        Word_Map.Node t = wmap.head;
        Word_Map.Node t2 = null;
        int flag = 0;
        while ( t != null && flag == 0){
            if(t.key.equals(word) == true){     // GETTING WORD NODE OBJECT
                t2 = t;
                flag = 1;
            }
            t = t.nextWord;
        }
        ArrayList<String> save= new ArrayList<>(); // FOR SAVE WORDS, YOU SHOULD ADD WORD PARAMETER WHEN YOU PRINT IT
        for(int i = 0; i< t2.value.fnames.size();i++){  // THİS METHOD FIND GIVEN WORD OBJECT FNAMES,POSITIONS AND FIND +1 POSITION
            String fname = t2.value.fnames.get(i);  // filenames
            List occurances = t2.value.occurances.get(i); // occurances
            t = wmap.head;  // FOR TRAVERS WORDS
            while(t != null){
                if(t.key.equals(word) == false){
                    for(int k = 0; k < t.value.fnames.size();k++ ){
                        if(t.value.fnames.get(k).equals(fname)){        // CHECK FILE NAME
                            List occurances2 = t.value.occurances.get(k);
                            for(int j = 0; j<occurances.size();j++){
                                for( int m = 0; m < t.value.occurances.get(k).size();m++){      // CHECH POSİTONS
                                    int position1 = (int)occurances.get(j);
                                    int position2 = (int)occurances2.get(m);
                                    if( position1+1 == position2) {                             // +1 POSITION CHECK
                                        int flag2 = 0;
                                        for(int n = 0; n < save.size(); n++ ){
                                            if(save.get(n).equals(t.key) ==true ) flag2 =1;
                                        }
                                        if(flag2 == 0) {
                                            save.add(word);
                                            save.add(t.key);    // ADD INTO SAVE LİST WİTH GİVEN WORD
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                t= t.nextWord;
            }
        }
        System.out.print("[");
        for(int i = 0; i< save.size();i++){     // PRINT BIGRAM OBJECTS
            System.out.print(save.get(i) +" ");
            if(i+1<save.size()) {
                System.out.print(save.get(i+1));
                i++;
            }
            if(i+1<save.size()) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("");
        return save;
    }
    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName) {
        Word_Map.Node t = wmap.head;    // FOR  TRAVERSE ARRAY
        int flag = 0;
        float size = 0, totalSizeWitht = 0; // FOR CALCULATİON
        while (t != null && flag == 0){
            if(t.key.equals(word) == true){
                List list = (List) t.value.get(fileName);  // Number of times term t appears in a document
                if(list != null)size = (float) list.size();
                totalSizeWitht = (float) t.value.fnames.size(); // Number of documents with term t in it)
                flag =1;
            }
            t= t.nextWord;
        }
        float totalNum = 0;
        for(int i = 0; i< this.fileNames.size();i++){
            if(this.fileNames.get(i).equals(fileName) == true){
                totalNum = (float)this.fileWordSize.get(i);     //Total number of terms in the document
                i = this.fileNames.size();
            }
        }
        float ıdf = (float) Math.log(totalNumDocuments / totalSizeWitht);
        float tf = size/totalNum;
        float res= tf*ıdf;
        String str = String.format("%.07f", res);
        System.out.println(str + "\n");    // RESULT
        return res;
    }
    /*Print the WordMap by using its iterator*/
    public  void printWordMap() {  // you should call this method into main class
        Iterator it = wmap.iterator();
        while (it.hasNext()){
            System.out.println(it.next().toString());
        }
    }
}