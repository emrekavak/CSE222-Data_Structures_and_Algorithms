// 151044085 EMRE KAVAK
package com.company;
import java.util.Scanner;
import java.io.IOException;


// You should enter full path name like bottom
// C:/Users/emrek/IdeaProjects/hw3/src/com/company/input.txt
// C:/Users/emrek/IdeaProjects/hw3/src/com/company/test_file_part2.txt
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Please enter Full path for PART1 : ");
        Scanner fileadress = new Scanner(System.in);
        String path ;
        path = fileadress.next();
        MatrixWhiteComponents white = new MatrixWhiteComponents(path);

        System.out.println("Please enter Full path for PART2 : ");
        path = fileadress.next();
        infixCalculator infix = new infixCalculator(path);
    }
}
    // EXAMPLE FOR PATH
 /*     Please enter Full path for PART1 :
        C:/Users/emrek/IdeaProjects/hw3/src/com/company/input.txt
        Number Of Groups  = 4
        Please enter Full path for PART2 :
        C:/Users/emrek/IdeaProjects/hw3/src/com/company/test_file_part2.txt
        w=5
        x=6
        ( 4 - x ) * ( w + 10 )
        Result is = 15.0
*/