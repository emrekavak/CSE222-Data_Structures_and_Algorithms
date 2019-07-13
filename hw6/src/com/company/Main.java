// 151044085 EMRE KAVAK
package com.company;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        NLP obj = new NLP("C://Users//emrek//IdeaProjects//hw6//dataset");  // first file read
        // for print word_map
        // obj.printWordMap();
        // !!!!!!!!!!!!!!!!!!!!!! YOU SHOULD ADD FULL PATH !!!!!!!!!!!!!!!!!!!!!!!
        obj.readInput("C:\\Users\\emrek\\IdeaProjects\\hw6\\src\\com\\company\\input.txt"); // secondly input file read
    }
}
