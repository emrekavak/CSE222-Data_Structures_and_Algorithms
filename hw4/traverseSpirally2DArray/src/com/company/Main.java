package com.company;
import java.util.Iterator;
// EMRE KAVAK 151044085 HW4
public class Main {

    public static void main(String[] args) {
    int[][] arr =   {{1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,16}};
        Spirally2DArray obj = new Spirally2DArray(arr); // this class for create ıterator objects.
        Iterator it = obj.iterator();                   // I create an object of traverseSpirally2DArray object into Spirally2DArray class
        System.out.print("Order : ");                   // and it calculate path recursively, finally it save this path into an array.
        while (it.hasNext()){                           // iterator return next to next element on this array.
            System.out.print(it.next()+ " ");
        }
    }

}
