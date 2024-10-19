package com.example.movete.utils;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Duration;

public class Utils {
    

    // crear funcion para comparar 2 fechas
    public static boolean compareHoursDates(Date date1, Date date2, int hours) {
        LocalDateTime date = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime date3 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        long hoursDifference = Duration.between(date, date3).toHours();

        return hoursDifference < hours;

    }

}
