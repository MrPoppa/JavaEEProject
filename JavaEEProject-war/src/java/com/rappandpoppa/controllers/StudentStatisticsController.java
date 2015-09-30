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
    private Date date;
    Boolean listIsEmpty;
    Student student;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    public void onCourseChange() {
        loadCourseMB(courseFacade.find(courseMB.getId()));
        defineCourseDates();
    }

    public void onStudentChange() {
        student = studentFacade.find(studentMB.getId());
        listIsEmpty = courseMB.getAttendancelistList().isEmpty();
        for (Attendancelist a : courseMB.getAttendancelistList()) {
            if (a.getStudentList().contains(student)) {
                datesAttendedByStudent.add(a.getAttendanceDate());
            }
        }
        setAttendedDatesForStudent();
    }

    public void setAttendedDatesForStudent() {
        for (Date d : courseDates) {
            if (datesAttendedByStudent.contains(d)) {
                attendedDates.put(d, true);
            } else {
                attendedDates.put(d, false);
            }
        }
    }

    public void loadCourseMB(Course course) {
        courseMB.setId(course.getId());
        courseMB.setCourseName(course.getCourseName());
        courseMB.setLanguage(course.getCourseLanguage());
        courseMB.setMaxNumberOfStudents(course.getMaxNumberOfStudents());
        courseMB.setStudentList(course.getStudentList());
        courseMB.setAttendancelistList(course.getAttendancelistList());
    }

    public void defineCourseDates() {
        if (!courseDates.isEmpty()) {
            courseDates.clear();
        }

        for (Attendancelist a : courseMB.getAttendancelistList()) {
            courseDates.add(a.getAttendanceDate());
        }
    }

    public boolean didAttend() {
        return datesAttendedByStudent.contains(date);
    }
}
