// Emre KAVAK
package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* This class extend AbstractGraoh class and overrrides methods*/

public class MatrixGraph extends AbstractGraph {
    protected boolean[][] edges; // The two dimensional array to represen an edge

    public MatrixGraph(String fileName) {  // this constructor send file name to fiiEdges method
        this.fillEdges(fileName);
    }

    @Override
    public void insert(int row, int column) {   // for inser element into edges array and call transitive method
        edges[row][column] = true;
        this.setTransitive();
    }

    @Override
    public boolean isEdge(int source, int dest) {   // return given element have edge or not
        if (isDirected()) {
            return edges[source][dest];
        }
        else {
            return edges[dest][source];
        }
    }
    @Override
    public int getNumV() {  // return number of vertices
        return this.numV;
    }

    @Override
    public void setNumv(int size) { // set size of vertices
        this.numV = size;
    }

    @Override
    public boolean isDirected(){    // return directed or not
        return this.directed;
    }
    @Override
    protected void setDirected(boolean bool){   // set directed data member
        this.directed = bool;
    }

    @Override
    protected void setEdge(int p1, int p2, boolean bool) {  // set vertices according to edges
        this.edges[p1][p2] = bool;
    }
    protected void fillEdges(String fileName){  // this method read given file name and fill edges array
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String [] arrOfStr = line.split(" ");   // split string and convert integer
            int countOfReletion = Integer.valueOf(arrOfStr[1]);
            this.setNumv(Integer.valueOf(arrOfStr[0])+1);
            this.setDirected(true);
            this.setEdgeSize();

            int  count = 1;
            while (count <= countOfReletion) {  // this loop according to countOf relation
                line = reader.readLine();
                if(line == null) {  // check if entered bigger relation size
                    System.out.println("You entered Bigger Relations number than the given number. File reading stopped.");
                    count = countOfReletion+1;
                }else {
                    String[] arrOfReletions = line.split(" ");
                    this.insert(Integer.valueOf(arrOfReletions[0]), Integer.valueOf(arrOfReletions[1]));    // setting vertices
                }
                count++;
            }
            reader.close();
            this.setTransitive();  // Finally, set transitive
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.printEdges();
        this.mostPopulerPeople();   // calculate popular people
    }

    protected void setEdgeSize() {
        edges = new boolean[getNumV()][];

        if (isDirected()) {    // fill directed graph
            for (int i = 0; i != getNumV(); i++) {
                edges[i] = new boolean[getNumV()];
                for (int j = 0; j != getNumV(); j++) {
                    edges[i][j] = false;
                }
            }
        } else {        // fill undirected graph
            for (int i = 0; i != getNumV(); ++i) {
                edges[i] = new boolean[i + 1];
                for (int j = 0; j != i + 1; ++j) {
                    edges[i][j] = false;
                }
            }
        }
    }

    protected void setTransitive(){
        boolean check = false;
        for(int i =0; i< this.getNumV(); i++){
            for(int j =0; j< this.getNumV();j++){
                if(this.isEdge(i,j) == true ){
                    for(int k =0; k< getNumV();k++){
                        if(this.isEdge(j,k) == true && i != k){
                            this.setEdge(i,k,true);
                        }
                    }
                }
            }
        }
    }

    protected void printEdges(){    // if you want to print array, you can call this method
        System.out.print("\t"+"  ");
        for(int i = 1; i<getNumV();i++)
            System.out.print(i+ "\t"+ "\t"+"  ");
        System.out.println("\n");

        for(int i = 1; i<getNumV();i++){
            System.out.print(i + "\t");
            for(int j = 1; j<getNumV();j++)
                System.out.print(edges[i][j] + "\t");
            System.out.println("\n");
        }
    }

    protected void mostPopulerPeople(){
        int max = 0;
        int countOfpeople = 0;
        for(int i = 1; i<this.getNumV();i++){
            for(int j = 1; j < getNumV();j++){
                if(isEdge(j,i) == true) max++; // calculate column true size,
                else if(i != j)max = 0;
                if(max == this.getNumV()-2) {   // according to true size, count of people will increase
                    countOfpeople++;
                    max = 0;
                }
            }

        }
        System.out.println(countOfpeople); // Count Of Most populer People
    }
}
