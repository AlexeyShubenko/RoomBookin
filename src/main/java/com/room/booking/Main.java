package com.room.booking;

import com.room.booking.domain.Employee;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 19.07.2017.
 */
public class Main {

    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime.toString());
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate);
//        LocalDateTime d1 = LocalDateTime.of(2017, Month.valueOf("JANUARY"),0,8,05);
//        System.out.println(d1.getMonth().toString().equals("JANUARY"));
//        System.out.println(d1.toLocalTime().toString());
        LocalTime time = LocalTime.parse("18:10");
        System.out.println(time.toString());
//        LocalDateTime d2 = LocalDateTime.of(2017,1,31,12,30);
//        System.out.println(d1.isAfter(d2));
//        LocalTime t = LocalTime.of(14,54);
//        System.out.println(t);


    }

}
