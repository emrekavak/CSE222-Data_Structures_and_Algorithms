package com.company;

import java.awt.*;
import java.util.Comparator;

public class LexComparator implements Comparator<Color>{
    @Override
    public int compare(Color pixLeft, Color pixRight ) {
        if( pixLeft.getRed() < pixRight.getRed() ||
            pixLeft.getGreen() < pixRight.getGreen() ||
            pixLeft.getBlue() < pixRight.getBlue())
            return -1;

        if( pixLeft.getRed() > pixRight.getRed() ||
            pixLeft.getGreen() > pixRight.getGreen() ||
            pixLeft.getBlue() > pixRight.getBlue())
            return 1;

        return 0 ;
    }
}
