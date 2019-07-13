package com.company;

import java.awt.*;
import java.util.Comparator;

public class EucComparator implements Comparator<Color> {
    public double calculateEuc(Color parent){
        double result = Math.sqrt(Math.pow(parent.getRed(),2) +  Math.pow(parent.getBlue(),2) + Math.pow(parent.getBlue(),2));
        return result;
    }
    @Override
    public int compare(Color parent, Color child) {
        double res1 = calculateEuc(parent);
        double res2 = calculateEuc(child);
        if(res1 < res2) return -1;
        if(res2 < res1 ) return 1;
        return 0 ;
    }
}
