package com.tensquare.article.controller;


public class aa {
    //第一题
    public static void main(String[] args) {
        for (int i=1;i<=9;i++) {
            for (int j = i; j <= 9; j++) {
                System.out.print(i + "*" + j + "=" + i * j + "\t");
            }
            System.out.println();

        }
        System.out.println(max(10,15,11));
        Main();
    }
    //第二题
    public static int max(int a,int b,int c){
        int sum[]={a,b,c};
        int max=0;
        for (int i=0;i<sum.length;i++){
            if (sum[i]>max){
                max=sum[i];
            }
        }
        return max;
    }
    //第三题
    public static void Main(){
        int a=5 , b=a+3 , c=0;
        String x="Hello!";
        if(a<b){
            c=a;
        }else {
            c=b;
        }
        System.out.println(a+","+b+","+","+c+","+x);
    }


}
