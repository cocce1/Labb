package com.company;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        //1
        List<Person> personList = List.of(
                new Person("Alma", "Female", 30000),
                new Person("Astrid", "Female", 38000),
                new Person("Elsa", "Female", 34000),
                new Person("Julia", "Female", 39000),
                new Person("Athena", "Female", 31000),
                new Person("Nike", "Female", 33000),
                new Person("Gösta", "Male", 31000),
                new Person("Bengt", "Male", 27000),
                new Person("Sören", "Male", 28000),
                new Person("Artemis", "Male", 35000)
        );
        //1.1
        System.out.println("Female avarge: " +
                personList
                        .stream()
                        .filter(pers -> pers.getGender().equals("Female"))
                        .map(Person::getSalary).collect(Collectors.averagingDouble(Double::doubleValue))

        );
        System.out.println("Male avarge " +
                personList
                        .stream()
                        .filter(pers -> pers.getGender().equals("Male"))
                        .map(Person::getSalary).collect(Collectors.averagingDouble(Double::doubleValue))
        );
        //1.2
        System.out.println(
                personList
                        .stream()
                        .sorted(Comparator.comparing(Person::getSalary).reversed())
                        .limit(1)
                        .toList()
        );
        //1.3
        System.out.println(
                personList
                        .stream()
                        .sorted(Comparator.comparing(Person::getSalary))
                        .limit(1)
                        .toList()
        );
//2 Skapa en bilfabrik, med hjälp av factory pattern
        Carfactory carfactory = new Carfactory();
        Car petrolCar1 = carfactory.createCar(Fueltype.PETROL);
        petrolCar1.makesNoise();
        Car electricCar1 = carfactory.createCar(Fueltype.ELECTRIC);
        electricCar1.makesNoise();
        //3 Skapa en lista av ord. Använd reguljära uttryck för plocka ut endast
        // de ord som innehåller 2 eller fler engelska vokaler (a, e, i, o, u, y)
        Pattern pattern = Pattern.compile("a, e, i, o, u, y");
        List<String> programingLanguages = List.of("Go", "C", "Ruby", "Fortran",
                "Java", "BASIC", "Assembly", "Python", "Swift", "JS", "MatLab");
            pattern.matcher(programingLanguages.toString()).results()
                    .map(MatchResult::group)
                    .forEach(System.out::println);

      //  [a, e, i, o, u, y] {,2}gmi

        //4. Räkna ut antalet primtal inom intervallet 0 till 500'000.
        // Dela upp intervallet på 2 eller flera trådar, som var för sig
        // räknar på antalet primtal inom sin tilldelade del parallellt.
        // Du kan t.ex. Låta en tråd ta en första del (typ 0 till 350'000)
        // och en annan tråd resten 350'001 till 500'000. Det är dock givetvis
        // tillåtet med något eget mer avancerat och effektivt upplägg också.
        List<Integer> primeNum1 = new ArrayList<>();
        Runnable upTO350000 = () -> {
            for (int i= 0; i <= 350000; i++) {
                if(i == 0 || i == 1){continue;}
                boolean primeNumerals = true;
                for(int j = 2 ; j < i ;j++)
                if (i % j == 0) {
                  primeNumerals = false;
                  break;
                }
                if (primeNumerals){
                    primeNum1.add(i);
                }
            }
            System.out.println("Antalet primtal från 0-350 000 är "+ primeNum1.size());
        };
       List<Integer> primeNum2 = new ArrayList<>();
        Runnable from350001 = () -> {
            for (int k = 350001; k <= 500000; k++) {
                boolean primeNumbers2 = true;
                for (int l = 2; l < k ; l++ )
                if (k % l == 0) {
                    primeNumbers2 = false;
                    break;
                }if(primeNumbers2) {
                   primeNum2.add(k);
                }
            }
            System.out.println("Antalet primtal från 350 001 - 500 000 är " + primeNum2.size());
        };
        Thread thread1 = new Thread(upTO350000);
        Thread thread2 = new Thread(from350001);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

    }
}
