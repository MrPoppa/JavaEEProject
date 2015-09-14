package com.rappandpoppa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class SchoolCalendarMB {

    private static List<Date> lectureFreeDays = new ArrayList<>();

    public static List<Date> getLectureFreeDays() {
        return lectureFreeDays;
    }

    public static void setLectureFreeDays(List<Date> lectureFreeDays) {
        lectureFreeDays = lectureFreeDays;
    }

    public static void addLectureFreeDate(Date date) {
        lectureFreeDays.add(date);
    }

    public static void removeLectureFreeDate(Date date) {
        lectureFreeDays.remove(date);
    }
}
