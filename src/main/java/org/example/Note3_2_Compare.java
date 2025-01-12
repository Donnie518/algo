package org.example;

import com.sun.nio.sctp.PeerAddressChangeNotification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Note3_2_Compare {


    public static void main(String[] args) {
        ArrayList<Person> arrayList = new ArrayList<>();
        arrayList.add(new Person(13, "333"));
        arrayList.add(new Person(13, ""));
        arrayList.add(new Person(12, ""));
        Collections.sort(arrayList);
//        arrayList.sort((p1, p2) -> {
//            if (p1.age - p2.age != 0) return p1.age - p2.age;
//            return p1.name.length() - p2.name.length();
//        });
        arrayList.forEach(System.out::println);
//        for (Person p : arrayList) {
//            System.out.println("p = " + p);
//        }


        Person[] people = {
                new Person(16, "333"),
                new Person(16, ""),
                new Person(14, "")
        };
        Arrays.sort(people);
//        Arrays.sort(people,(p1, p2) -> {
//            if (p1.age - p2.age != 0) return p1.age - p2.age;
//            return p1.name.length() - p2.name.length();
//        });
        Arrays.stream(people).forEach(System.out::println);
//        for (Person p : people) {
//            System.out.println("p = " + p);
//        }
    }
}

class Person implements Comparable<Person> {
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person p) {
        if (this.age - p.age != 0) return this.age - p.age;
        return this.name.length() - p.name.length();
    }
}