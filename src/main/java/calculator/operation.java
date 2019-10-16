package com.company;

import java.lang.Math;
import java.util.Scanner;
import java.util.Stack;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class operation {

    public String equation;

    public operation(String eq){
        equation = eq;
    }
    float operate(){
                return operatePostfix(ToPostfixed(equation));
    }

    private static String depurar(String s) {
        s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
        s = "(" + s + ")";
        String simbols = "+-*/()";
        String str = "";

        //Deja espacios entre operadores
        for (int i = 0; i < s.length(); i++) {
            if (simbols.contains("" + s.charAt(i))) {
                str += " " + s.charAt(i) + " ";
            }else str += s.charAt(i);
        }
        return str.replaceAll("\\s+", " ").trim();
    }

    public String ToPostfixed (String Infix) {
        String Postfixed = "";

       // Infix = reduceOperator(Infix);

        Infix = depurar (Infix);

        String[] arrayInfix = Infix.split(" ");

        Stack<String> E = new Stack<String>();
        Stack<String> P = new Stack<String>();
        Stack<String> S = new Stack<String>();

        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            E.push(arrayInfix[i]);
        }

        while (!E.isEmpty()) {
            switch (precedence(E.peek())) {
                case 1:
                    P.push(E.pop());
                    break;
                case 3:
                case 4:
                    while (precedence(P.peek()) >= precedence(E.peek())) {
                        S.push(P.pop());
                    }
                    P.push(E.pop());
                    break;
                case 2:
                    while (!P.peek().equals("(")) {
                        S.push(P.pop());
                    }
                    P.pop();
                    E.pop();
                    break;
                default:
                    S.push(E.pop());
            }
        }

        Postfixed = S.toString().replaceAll("[\\]\\[,]", "");

        return Postfixed;
    }

    public int precedence (String op){
        int prf = 99;
        if (op.equals("^")) prf = 5;
        if (op.equals("*") || op.equals("/")) prf = 4;
        if (op.equals("+") || op.equals("-")) prf = 3;
        if (op.equals(")")) prf = 2;
        if (op.equals("(")) prf = 1;
        return prf;
    };


    boolean isOperand(char myChar){
        if (myChar>='0' && myChar<='9') return true;
        else if (myChar>='a' && myChar<='z') return true;
        else if (myChar>='A' && myChar<='Z') return true;
        return false;
    }
    boolean isVariable(char myChar){
        if (myChar>='a' && myChar<='z') return true;
        else if (myChar>='A' && myChar<='Z') return true;
        return false;
    }


	public boolean isOperator(char myChar) {
		return myChar == '+' || myChar == '-' || myChar == '*' || myChar == '/' || myChar == '^';
    	}

    	public boolean isParentesis(char myChar){
        	return myChar =='(' || myChar == ')';
    	}
    
	public boolean isLeftParentesis(char myChar){
        	return myChar == '(';
    
	}
    
	public boolean isRightParentesis(char myChar){
        	return myChar == ')';
    	}
    
	public boolean isPlusOrMinus(char myChar){
        	return myChar == '-' || myChar == '+';
    	}
    
	public boolean isPlus(char myChar) {
        	return myChar == '+';
    	}
    
	public boolean isMinus(char myChar) {
        	return myChar == '-';
    	}
    
	public boolean isMult(char myChar){
        	return  myChar == '*';
    	}


    public String reduceOperator(String equation) {
        String myResult = ""; 
        for (int i = 0; i < equation.length(); i++ ) {
            if (isOperand(equation.charAt(i))) {
                myResult = myResult + equation.charAt(i);
            }
            if (isParentesis(equation.charAt(i))) {
                myResult = myResult + equation.charAt(i);
            }
            if (isOperator(equation.charAt(i))) {
                if (isPlusOrMinus(equation.charAt(i))) {
                    if (isPlus(myResult.charAt(myResult.length() - 1)) && isMinus(equation.charAt(i))) {
                        myResult = myResult.substring(0, myResult.length() - 1);
                        myResult = myResult + '-';
                    } else if (isPlus(myResult.charAt(myResult.length() - 1)) && isPlus(equation.charAt(i))) {
                        myResult = myResult.substring(0, myResult.length() - 1);
                        myResult = myResult + '+';
                    } else if (isMinus(myResult.charAt(myResult.length() - 1)) && isMinus(equation.charAt(i))) {
                        myResult = myResult.substring(0, myResult.length() - 1);
                        myResult = myResult + '+';
                    } else if (isMinus(myResult.charAt(myResult.length() - 1)) && isPlus(equation.charAt(i))) {
                        myResult = myResult.substring(0, myResult.length() - 1);
                        myResult = myResult + '-';
                    } else {
                        myResult = myResult + equation.charAt(1);
                    }
                } else {
                    myResult = myResult + equation.charAt(1);
                }
            }

        }
        return myResult;
    }

    float operates(float first,float second, char op){
        switch (op){
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;
            case '^':
                return (float) Math.pow(first,second);
            default:
                return 0;
        }

    }
    float operatePostfix(String equation){
        String [] parts = equation.split(" ");
        String word;
        Stack<Float> myStack = new Stack<Float>();

        for (int i = 0; i < parts.length; i++){
            if(parts[i].length() > 1 && isOperator(parts[i].charAt(0)) && !isOperator(parts[i].charAt(1))){
                float number = Float.parseFloat(parts[i]);
                myStack.push(number);
            }
            else if (!isOperator(parts[i].charAt(0))){
                float number = Float.parseFloat(parts[i]);
                myStack.push(number);
            }else if(isOperator(parts[i].charAt(0))){
                float second = myStack.peek(); myStack.pop();
                float first = myStack.peek(); myStack.pop();
                float result = operates(first, second,parts[i].charAt(0) );
                myStack.push(result);
            }
        }
        return myStack.peek();
    }
}
