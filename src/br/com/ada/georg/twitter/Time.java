package br.com.ada.georg.twitter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Time {
    public static boolean isValidDate(String date) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(date,formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String convertDateFormat(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return date.format(outputFormatter);
    }

    public static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return now.format(formatter);
    }

    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm");
        return now.format(formatter);
    }

    public static int getAge(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        LocalDate birth = LocalDate.parse(birthDate, formatter);
        LocalDate now = LocalDate.parse(getCurrentDate(), formatter);
        Period age = Period.between(birth, now);
        return age.getYears();
    }
}
