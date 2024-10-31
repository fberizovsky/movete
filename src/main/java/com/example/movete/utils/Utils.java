package com.example.movete.utils;

import java.time.LocalDateTime;
import java.time.Duration;

public class Utils {

    // crear funcion para comparar 2 fechas
    public static boolean compareHoursDates(LocalDateTime date1, LocalDateTime date2, int hours) {
        long hoursDifference = Duration.between(date1, date2).toHours();
        return hoursDifference < hours;
    }

}
