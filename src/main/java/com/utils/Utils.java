package com.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

<<<<<<< HEAD
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;

=======
>>>>>>> main
public class Utils {
    public static void hardWait(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (Exception e) {
            // pass
        }
    }

    public static void hardWait() {
        hardWait(3000);
    }

    public static String addDaysToToday(int daysToAdd) {
        LocalDate result = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return result.format(fmt);
    }

    public static String extractDate(String date, String type) {
        String extractedDate = "";
        switch (type) {
            case "day":
                extractedDate = date.substring(0, 2).replaceFirst("^0", "");
                break;
            case "month":
                extractedDate = date.substring(3, 5).replaceFirst("^0", "");
                break;
            case "year":
                extractedDate = date.substring(date.length() - 4);
                break;
            default:
                break;
        }
        return extractedDate;
    }

    public static String addDays(String startDate, int daysToAdd) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(startDate, fmt);
            return date.plusDays(daysToAdd).format(fmt);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatDate(String dayStr, String monthStr, String yearStr) {
        try {
            int day = Integer.parseInt(dayStr.replaceAll("\\D+", ""));
            int month = Integer.parseInt(monthStr.replaceAll("\\D+", ""));
            int year = Integer.parseInt(yearStr.replaceAll("\\D+", ""));
            return String.format("%02d-%02d-%04d", day, month, year);
        } catch (NumberFormatException e) {
            // fallback: join raw values if parsing fails
            return dayStr + "-" + monthStr + "-" + yearStr;
        }
    }
<<<<<<< HEAD

    public static void takeScrenshot(WebDriver driver, Scenario scenario, String fileName) {

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", fileName);
    }

    public static String getStartDate(String text) {
        String numberAddToday = text.substring(text.indexOf("+ ") + 2);
        String dateFromToday = addDaysToToday(Integer.parseInt(numberAddToday));
        return dateFromToday;
    }

    public static String getEndDateFromStartDate(String text, String startDate) {
        String numberAddToday = text.substring(text.indexOf("+ ") + 2);
        String endDate = addDays(startDate, Integer.parseInt(numberAddToday));
        return endDate;
    }
=======
>>>>>>> main
}
