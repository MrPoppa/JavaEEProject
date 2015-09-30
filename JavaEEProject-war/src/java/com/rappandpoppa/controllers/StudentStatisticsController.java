package com.rappandpoppa.controllers;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Anders
 */
@ManagedBean
@ViewScoped
public class StudentStatisticsController extends StatisticsController {

    private Map<Date, Boolean> attendedDates = new HashMap<>();
    private List<Date> datesAttendedByStudent = new ArrayList<>();
    private List<Date> courseDates = new ArrayList<>();

    public Map<Date, Boolean> getAttendedDates() {
        return attendedDates;
    }

    public void setAttendedDates(Map<Date, Boolean> attendedDates) {
        this.attendedDates = attendedDates;
    }

    public List<Date> getDatesAttendedByStudent() {
        return datesAttendedByStudent;
    }

    public void setDatesAttendedByStudent(List<Date> datesAttendedByStudent) {
        this.datesAttendedByStudent = datesAttendedByStudent;
    }

    public List<Date> getCourseDates() {
        return courseDates;
    }

    public void setCourseDates(List<Date> courseDates) {
        this.courseDates = courseDates;
    }

    public void onCourseChange() {
        courseMB.setChosenCourse(courseFacade.find(courseMB.getId()));
        courseMB.setCourseStudents(courseMB.getChosenCourse().getStudentList());
    }

    public void setAttendedDatesForStudent() {
        for (Attendancelist a : courseMB.getChosenCourse().getAttendancelistList()) {
            courseDates.add(a.getAttendanceDate());
        }
 
        for (Date d : courseDates) {
            if (datesAttendedByStudent.contains(d)) {
                attendedDates.put(d, true);
            } else {
                attendedDates.put(d, false);
            }
        }
    }

}