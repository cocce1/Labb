package com.company;



public class Carfactory {
 public Car createCar(Fueltype fueltype){
    return switch (fueltype){
         case PETROL -> new PetrolCar();
         case ELECTRIC -> new ElectricCar();
     };
 }
}
