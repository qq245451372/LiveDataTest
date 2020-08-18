package com.example.test;

public class MyClass {
    public static void main(String[] args) {

        Person person = new Son();
        person.show();


        System.out.println(test());

    }

    public static int test()
    {
        try {
            System.out.println("11111111111");
            return 1;
        }catch (Exception e)
        {
            System.out.println("Exception");
        }finally {
            System.out.println("finally----");
        }
        return 0;
    }
}