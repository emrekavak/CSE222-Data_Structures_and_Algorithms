package com.company;

/* This class abstract and implements Graph interface */
public abstract class AbstractGraph implements Graph {

    /** The number of vertices */
    protected int numV;
    /** Flag to indicate whether this is a directed graph */
    // Abstract Methods
    protected boolean directed; // for hold graph type
    abstract protected void setDirected(boolean bool);  // set directed
    abstract protected void setEdge(int p1,int p2, boolean bool); // set edges true or false
}
