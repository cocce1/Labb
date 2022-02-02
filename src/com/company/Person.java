package com.company;


 class Person {
     private String name;
     private String gender;
     private double salary;

     public Person(String name, String gender, double salary) {
         this.name = name;
         this.gender = gender;
         this.salary = salary;
     }

     public String getName() {
         return name;
     }

     public String getGender() {
         return gender;
     }

     public double getSalary() {
         return salary;
     }

     @Override
     public String toString() {
         return "Person{" +
                 "name='" + name + '\'' +
                 ", gender='" + gender + '\'' +
                 ", salary=" + salary +
                 '}';
     }
 }
