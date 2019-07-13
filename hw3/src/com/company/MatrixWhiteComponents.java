// 151044085 EMRE KAVAK
package com.company;
import java.beans.IntrospectionException;
import java.io.*;
import java.util.Arrays;

// Firstly I read file, I don't save file, I just save if coordinate is 1 ( like (1,12) ) and I push this coordinate into myStack object.
// after that, I save this stack into an array and I calculate coordinate according to checkLeft, checkRight, checkUp and checkBottom functions results.
// I save again into stack (stack empty )  when I catch 1s coordinate according to functions
// Finally I again pop the stack and calculate Number Of Groups and print it

public class MatrixWhiteComponents {
    private myStack<String> stack;
    private String backupArr[];

    public MatrixWhiteComponents(String fileName) throws IOException {
        stack = new myStack();  // memory allocation;
        readFile(fileName);     // this function read file and calculate group
    }
    protected void readFile(String fileName) throws IOException {
        Reader openFile = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), "US-ASCII"));    // file opening
        int elementNumbers = 0,element,flag =0,flag2 = 0, counterMatrix = 0, index1 = 0, row = 0, column = 0, divide = 0;
        String   x, y, coordinate;

        while((element = openFile.read()) != -1 ){  // I read file char to char
            if(element != 32 && element != 13 && element != 10 && element != 48) {   // 32 = space, 10 = newline ,13 = I think it means end of line
                x = Integer.toString(column);
                y = Integer.toString(row);
                y = y.concat(",");  // I add "," because of seperate X an Y coordinate
                coordinate = y.concat(x);
                stack.push(coordinate);  // pushed element in the stack
                elementNumbers++;        // added coordinate numbers
                flag2++; // refers added element side by side
                flag++;  // refers element added
            }
            if(element != 32 && element != 13 && element != 10) column++;
            if(element == 10) {    // calculate column size. 10 means new line
                row++;
                column = 0;
            }
            if(element != 32 && element != 13 && element != 10) counterMatrix++;
        }
        openFile.close();
        int i = 0;
        backupArr = new String[elementNumbers]; // for hold 1 elements coordinate
        while(!(stack.empty())){    // stack backup to backupArr
            backupArr[i] = stack.pop(); // start end of stack, so, first element is last element of stack
            i++;
        }
        calculateGroups(backupArr,elementNumbers);
    }
    private void checkRigtSide(String arr[],int index, int groupNum){    // this function check the coordinate in order to there is 1 right Side
        int i = index, flag = 0,Rx,Ry,Nx,Ny;
        String right = "1,0", now = "1,0", backup,splitArr1[], splitArr2[];
        if(index-1>=0) {
            right = arr[i-1];
            now = arr[i];
        }
        while(i>=0 && flag == 0){
            if( i-1 >= 0 && (splitArr1 = right.split(",")).length>1 && (splitArr2 = now.split(",")).length>1){ // for split string and catch x and y coordinate
                Rx = Integer.parseInt(splitArr1[0]);
                Ry = Integer.parseInt(splitArr1[1]);
                Nx = Integer.parseInt(splitArr2[0]);
                Ny = Integer.parseInt(splitArr2[1]);
                if(Rx - Nx == 0 && Ry - Ny == 1 ){
                        now =right;
                        backup = right;
                        if (i-2>=0) right = arr[i-2];
                        checkUp(arr,(i-1),groupNum);
                        arr[i-1] =String.valueOf(groupNum);
                        checkBottom(arr,backup,groupNum);
                } else flag = 1;
            }else flag = 1;
            i--;
        }
    }
    private void checkUp(String arr[], int index, int groupNum){ // check there is 1 my up
        int i = index, flag = 0,up_x = 0,up_y =0 ,me_x = 0 ,me_y = 0;
        String splitArr1[], splitArr2[];

        splitArr2 = arr[index].split(",");
        me_x = Integer.parseInt(splitArr2[0]);
        me_y = Integer.parseInt(splitArr2[1]);
        while(i < arr.length && flag == 0 && i>0){
            splitArr1 = arr[i].split(","); // ben
            up_x = Integer.parseInt(splitArr1[0]);
            up_y = Integer.parseInt(splitArr1[0]);
            if( ( me_x - up_x == 1 ) && ( me_y - up_y == 0)){   // if there is, different of x coordinate should be 1 and y 0
                arr[i] = String.valueOf(groupNum);
                flag = 1;
            }
            i++;
        }
    }
    private void checkLeftSide(String arr[], int index, int groupNum){  // this function check the coordinate in order to there is 1 Left Side
        int i = index, flag = 0, flag2 = 0,Rx,Ry,Nx,Ny;
        String left = "1", now = "1", backup;
        String splitArr1[], splitArr2[];
        if(index+1 < arr.length) {
            left = arr[index+1];
            now = arr[i];
        }
        while(i < arr.length && flag == 0){
            if((splitArr1 = left.split(",")).length>1 && (splitArr2 = now.split(",")).length>1) {
                Rx = Integer.parseInt(splitArr1[0]);
                Ry = Integer.parseInt(splitArr1[1]);
                Nx = Integer.parseInt(splitArr2[0]);
                Ny = Integer.parseInt(splitArr2[1]);
                if( i+1 < arr.length){
                    if (Rx - Nx == 0 && Ny - Ry == 1) {
                        now =left;
                        backup = left;
                        if (i+2<=arr.length) left = arr[i+2];
                        arr[i+1] =String.valueOf(groupNum);
                        checkBottom(arr,backup,groupNum);
                    } else flag = 1;
                }else flag = 1;
            }
            i++;
        }   // while end
    }
    private  void checkBottom(String arr[], String coordinate,int groupNum){    // this function check the coordinate in order to there is 1 bottom Side
        int i =arr.length-1,Rx,Ry,Nx,Ny;
        String splitArr1[], splitArr2[];
        while(i>=0){
            if((splitArr1 = coordinate.split(",")).length>1 && (splitArr2 = arr[i].split(",")).length>1) {  // if this coordinate doesnt minus (minus refers to there was 1 in there)
                Rx = Integer.parseInt(splitArr1[0]);
                Ry = Integer.parseInt(splitArr1[1]);
                Nx = Integer.parseInt(splitArr2[0]);
                Ny = Integer.parseInt(splitArr2[1]);
                if ((Nx - Rx == 1) && (Ny - Ry == 0)) {
                    coordinate = arr[i];
                    checkRigtSide(arr, i, groupNum);
                    checkLeftSide(arr, i, groupNum);
                    arr[i] = String.valueOf(groupNum);
                    i = arr.length;
                }
            }
            i--;
        }
    }
    private void calculateGroups(String arr[],int numbersElement){
        int i=numbersElement -1 ; // sondan basa dogru gidiyoruz cunku ilk eklenen 1 in coordinati sonda
        String test[] = backupArr[i].split(",");
        int groupNum = -1;

        while(i>=0){
            if(i >= 0) {
                checkRigtSide(backupArr,i, groupNum);  // check if there is 1 after my coordinate
                checkBottom(backupArr,backupArr[i],groupNum);
                this.stack.push(backupArr[i]);  // I added first element coordinate for count group
                groupNum--;
            }
            test= backupArr[i].split(",");
            i--;
        }
        int result = 0;
        while(!stack.empty()){
            if( (test= stack.pop().split(",")).length > 1) {
                result ++; // Finally I pop stack because of count coordinate except minus values
            }
        }
            System.out.println("Number Of Groups  = " + result);
        }
    }