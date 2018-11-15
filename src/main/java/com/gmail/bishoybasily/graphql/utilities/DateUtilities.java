package com.gmail.bishoybasily.graphql.utilities;

import java.util.Calendar;
import java.util.Date;

public class DateUtilities {

    public static Integer hour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1;
    }

    private static Integer hour(Date date) {
        return calendar(date).get(Calendar.HOUR_OF_DAY) + 1;
    }

    public static Integer day() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 0;
    }

    private static Integer day(Date date) {
        return calendar(date).get(Calendar.DAY_OF_WEEK) + 0;
    }

    public static Integer week() {
        return Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) + 0;
    }

    private static Integer week(Date date) {
        return calendar(date).get(Calendar.WEEK_OF_MONTH) + 0;
    }

    public static Integer month() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    private static Integer month(Date date) {
        return calendar(date).get(Calendar.MONTH) + 1;
    }

    public static Integer year() {
        return Calendar.getInstance().get(Calendar.YEAR) + 0;
    }

    private static Integer year(Date date) {
        return calendar(date).get(Calendar.YEAR) + 0;
    }

    //**

    public static Date now() {
        return new Date();
    }

    public static Date now(int constant, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(constant, count);
        return calendar.getTime();
    }

    public static Integer[] levels(Date date) {
        return new Integer[]{year(date), month(date), week(date), day(date), hour(date)};
    }

    private static Calendar calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

}
