package com.room.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * Created by Alexey on 19.07.2017.
 */
public class Main {

    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime.toString());
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate);
        LocalDateTime d1 = LocalDateTime.of(2017, Month.valueOf("January"),9,8,05);
        System.out.println(d1.getMonth().toString().equals("JANUARY"));
//        System.out.println(localDateTime.toString());
//        LocalDateTime d2 = LocalDateTime.of(2017,1,31,12,30);
//        System.out.println(d1.isAfter(d2));
//        LocalTime t = LocalTime.of(14,54);
//        System.out.println(t);
    }

}
