package com.company;

/* This class interface for graph class*/
public interface Graph {
    int getNumV(); //Return the number of vertices.
    void setNumv(int size);
    boolean isDirected();// Determine whether this is a directed graph.
    void insert(int p1,int p2);//Insert a new edge into the graph.
    boolean isEdge(int source, int dest); //Determine whether an edge exists.
}
