package com.company;

import java.io.*;

public class infixCalculator {
    private myStack<String> stack;
    public infixCalculator(String fileName) throws IOException {
        this.stack = new myStack<String>();
        readFile(fileName);
    }
    private void readFile(String fileName) throws IOException {   // this func Read file and fill stack
        File openFile = new File(fileName);
        BufferedReader b = new BufferedReader(new FileReader(openFile));
        String readLine = "", variablesArr[] = new String[100],backupArr[];
        int flag =0, i = 0;
        while ((readLine = b.readLine()) != null) {
            if(!(readLine.isEmpty()) && flag == 0) {
                if(i<50){
                    variablesArr[i] = readLine;         // variables will hold in there
                    i++;
                }
                System.out.println(variablesArr[i-1]);
            }
            if(readLine.isEmpty()){ // after space, I will fill backupArr array for expression
                flag = 1;
            }
            if(flag == 1 && !(readLine.isEmpty())) {
                backupArr = readLine.split(" ");    // all experitiıon split into backupArr
                System.out.println(readLine);
                calculate(backupArr,variablesArr);
            }
        }
    }
    private void calculate(String backupArr[], String variablesArr[]){    // calculate all operations
        int flag =0, i = 0, k = 0 , opCode = 0 ;
        float result = 0; // will hold result
        String  var1 ="0", var2 = "0", getVar[];

        while(k<backupArr.length) {
            this.stack.push(backupArr[k]);
            if(backupArr[k].equals(")")){
               if(!(stack.empty())) {
                   while(!stack.empty()){       // this loop do, assing var1(int or variable), opcode( + - * / ), var2( (int or variable) and calculate experession
                       if(backupArr[k].equals(")")) stack.pop();
                       var1 = (stack.pop());
                       if(!(this.stack.empty())){
                           opCode = operatorCheck((stack.pop()).charAt(0));
                           if(!(this.stack.empty())) var2 = stack.pop();
                           int j = 0;
                           while(variablesArr[j] != null){  // for find variable name and value
                               getVar = variablesArr[j].split("=");
                               if( getVar[0].equals(var1)) {
                                   var1 = getVar[1];
                               }
                               if( getVar[0].equals(var2)){
                                   var2 = getVar[1];
                               }
                               j++;
                           }
                           if(!var1.equals("(") && !var1.equals("(")) { // after find, I will find operator and will calculate result
                               if (opCode == 1) {
                                   result = Float.parseFloat(var2) + Float.parseFloat(var1);
                               }
                               if (opCode == 2) {
                                   result = Float.parseFloat(var2) - Float.parseFloat(var1);
                               }
                               if (opCode == 3) {
                                   result = Float.parseFloat(var2) * Float.parseFloat(var1);
                               }
                               if (opCode == 4) {
                                   result = Float.parseFloat(var2) / Float.parseFloat(var1);
                               }
                           }
                           stack.push(String.valueOf(result));  // result going to stack
                       }
                   }
                   stack.push(String.valueOf(result));
               }
            }
            k++;
        }
        while(!stack.empty())
        System.out.println("Result is = " + stack.pop());   // last result
    }
    protected int operatorCheck(char element) { // choice operation
        switch (element) {
            case '+':
                return 1;
            case '-':
                return 2;
            case '*':
                return 3;
            case '/':
                return 4;
        }
        return 0;
    }
}
