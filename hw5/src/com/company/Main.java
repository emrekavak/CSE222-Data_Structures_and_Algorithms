package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
         try {

             System.out.print("Please enter path :");
            Scanner path = new Scanner(System.in);
            String input = path.nextLine();
            // like C:/Users//emrek//IdeaProjects//hw5//src//com//company/example.png
            File f = new File(input);
            BufferedImage image= ImageIO.read(f);
            if (image == null) {
                System.out.println("The file"+input+"could not be opened , it is not an image");
                return ;
            }

            int height = image.getHeight();
            int width = image.getWidth();
            PriorityQueue PQLEX = new PriorityQueue(new LexComparator());   // 3 Priority Queue created
            PriorityQueue PQEUC = new PriorityQueue(new EucComparator());
            PriorityQueue PQBMX = new PriorityQueue(new BmxComparator());

            ProducerConsume threads = new ProducerConsume();    // this class contain Producer and consumer methods and will use threads
            Thread thr1 = new Thread() {    // first thread add elements into Queues
                @Override
                public void run() {
                    int counter = 0;
                    synchronized (this){
                        threads.setwXh(height*width);
                        for (int i = 0; i < height; i++) {
                            for (int j = 0; j < width; j++) {
                                Color pixel = new Color(image.getRGB(j, i));
                                try {
                                    threads.produce(PQLEX,PQEUC,PQBMX,pixel,counter);   // produce methos adds pixels into Queues
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                counter++;
                            }
                        }
                        notify();   // When add finished, this method for wake up another threads
                    }
                }
            };

            Thread thr2 = new Thread(){ // for remove PQLEX
                public void run(){
                    synchronized (this){
                        try {
                            threads.consume(PQLEX,"Thread2","PQLEX :");    // CONSUME removes element given Queue
                            notify();
                        }
                        catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            Thread thr3 = new Thread() {    // for remove PQEUC
                @Override
                public void run() {
                    synchronized (this){
                        try {
                            threads.consume(PQEUC,"Thread3","PQEUC :");

                        }
                        catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            Thread thr4 = new Thread() {     // for remove PQBMX
                 @Override
                 public void run() {
                     synchronized (this){
                         try {
                             threads.consume(PQBMX,"Thread4","PQBMX :");

                         }
                         catch(InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                 }
             };

             thr1.start();
             thr2.start();
             thr3.start();
             thr4.start();

            } catch (IOException ioe) {
                ioe.printStackTrace();

            }
        }
    }