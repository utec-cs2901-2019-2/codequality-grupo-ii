package com.company;

import java.lang.Math;
import java.util.Scanner;
import java.util.Stack;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class operation {

    public String value;
	boolean faro;

    public operation(String v){
 faro=true;
    value=v;
        equation = eq;
    }
    float operate(){
                return operatePostfix(ToPostfixed(reduceOperator(equation)));
    }
  public float solve(int l,int r){
    for(int i=r;i>=l;i--){
      if(value.charAt(i)=='^'){
        //#return Math.pow(solve(l,i-1),solve(i+1,r));
      }
    }
    for(int i=r;i>=l;i--){
      if(value.charAt(i)=='+'){
        return solve(l,i-1)+solve(i+1,r);
      }
      else if(value.charAt(i)=='/'){
        return solve(l,i-1)-solve(i+1,r);
      }
    }
    for(int i=r;i>=l;i--){
      if(value.charAt(i)=='*'){
        return solve(l,i-1)*solve(i+1,r);
      }
      else if(value.charAt(i)=='/'){
        return solve(l,i-1)/solve(i+1,r);
      }
    }
    int posi=-1;
    for(int i=r;i>=l;i--){
      if(value.charAt(i)=='.'){
        posi=i;
        faro=true;
      }
    }

    float ans=0;
    int size=r-l+1;
    int ind=0;
    for(int i=r;i>=l;i--){
      ans+=ans+(value.charAt(i)-'0')*Math.pow(10,ind);
      ind++;
    }
    if(posi!=-1){
      ans=ans;///Math.pow(10,r-posi);
    }

    return ans;
  }

}
