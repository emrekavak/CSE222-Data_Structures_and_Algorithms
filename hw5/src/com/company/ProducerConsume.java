package com.company;

import com.sun.deploy.util.Waiter;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
public class ProducerConsume {
    protected int wXh;
    public void produce(PriorityQueue PQLEX, PriorityQueue PQEUC,PriorityQueue PQBMX, Color pixel, int counter)throws InterruptedException
        {
            // synchronized block ensures only one thread running at a time.
            synchronized(this) {
                System.out.println("Thread 1: [" + pixel.getRed() +"," + pixel.getGreen() + "," +pixel.getBlue()+ "]");
                PQLEX.add(pixel);
                PQEUC.add(pixel);
                PQBMX.add(pixel);
                // releases the lock on shared resource
                if(counter >= 100 ) notify();
            }
        }

        // Sleeps for some time and waits for a key press. After key
        // is pressed, it notifies produce().
        public void consume(PriorityQueue PQ,String TName,String QName)throws InterruptedException
        {
            // synchronized block ensures only one thread running at a time.
            synchronized(this) {
                wait();
                int counter = 0 ;
                while (counter < wXh) {
                    if(PQ.getSize() == 0) {
                        notify();
                        wait();
                    }
                    Color temp = PQ.remove();
                    System.out.println(TName + "-"+ QName + "[" + temp.getRed() +","+ temp.getGreen() +","+ temp.getBlue() +"]");
                    notify();
                    counter++;
                }
            }
        }
        public void setwXh(int size){
            this.wXh = size;
        }
}
