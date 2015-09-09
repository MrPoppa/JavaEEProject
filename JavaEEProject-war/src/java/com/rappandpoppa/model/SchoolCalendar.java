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
public class SchoolCalendar {

    private List<Date> lectureFreeDays = new ArrayList<>();

    public List<Date> getLectureFreeDays() {
        return lectureFreeDays;
    }

    public void setLectureFreeDays(List<Date> lectureFreeDays) {
        this.lectureFreeDays = lectureFreeDays;
    }

    public void addLectureFreeDate(Date date) {
        lectureFreeDays.add(date);
    }

    public void removeLectureFreeDate(Date date) {
        lectureFreeDays.remove(date);
    }
}
