package com.room.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Alexey on 19.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toString());
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }

}
