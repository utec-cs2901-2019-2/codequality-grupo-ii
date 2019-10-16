package com.company;

public class Main {

    public static void main(String[] args) {
	String operation;
    Scanner obj=new Scanner(System.in);
    operation=obj.nextLine();
    int arr[]=new int[operation.length()];
    Op o=new Op(operation);
    float v=o.solve(0,operation.length()-1);
    System.out.println(v);
    }
}
