package com.company;

import java.awt.*;
import java.util.Comparator;

public class BmxComparator implements Comparator<Color> {
    private boolean[] binaryArr;
    private String convertBinary(Color pixel){   // for calculate binary

        String temp = "0";
        String red = Integer.toBinaryString(pixel.getRed());
        if(red.length()<8){
            for(int i = 1; i < 8 - red.length();i++){
                temp = temp + "0";
            }
            red = temp + red;
        }
        //System.out.println( " red " + red);
        String green = Integer.toBinaryString(pixel.getGreen());
        if(green.length()<8){
            for(int i = 1; i < 8 - green.length();i++){
                temp = temp + "0";
            }
            green = temp + green;
        }
        //System.out.println( "green " + green);
        String blue = Integer.toBinaryString(pixel.getBlue());
        if(blue.length()<8){
            for(int i = 1; i < 8 - blue.length();i++){  // if this integer lower than 8 bits, it will added 0s to the top
                temp = temp + "0";
            }
            blue = temp + blue;
        }
        //System.out.println( "blue " + blue);
        String res = new String();
        for(int i = 0; i< 8; i++){
            res = res + red.charAt(i);
            res = res + green.charAt(i);
            res = res + blue.charAt(i);
        }
        return res;
    }
    @Override
    public int compare(Color left, Color right) {
        String leftRes = this.convertBinary(left);
        String RightRes = this.convertBinary(right);
        if( leftRes.compareTo(RightRes) < 0 ) return -1;
        if( leftRes.compareTo(RightRes) > 0 ) return 1;
        return 0;
    }
}
