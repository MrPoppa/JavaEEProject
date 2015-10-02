package com.rappandpoppa.controllers;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
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

    private Map<LocalDate, String> attendedDates = new LinkedHashMap<>();
    private List<LocalDate> datesAttendedByStudent = new ArrayList<>();
    private List<LocalDate> courseDates = new ArrayList<>();

    public Map<LocalDate, String> getAttendedDates() {
        return attendedDates;
    }

    public void setAttendedDates(Map<LocalDate, String> attendedDates) {
        this.attendedDates = attendedDates;
    }

    public List<LocalDate> getDatesAttendedByStudent() {
        return datesAttendedByStudent;
    }

    public void setDatesAttendedByStudent(List<LocalDate> datesAttendedByStudent) {
        this.datesAttendedByStudent = datesAttendedByStudent;
    }

    public List<LocalDate> getCourseDates() {
        return courseDates;
    }

    public void setCourseDates(List<LocalDate> courseDates) {
        this.courseDates = courseDates;
    }

    public void onCourseChange() {
        loadCourseMB(courseFacade.find(courseMB.getId()));
        defineCourseDates();
    }

    public void onStudentChange() {
        if (!attendedDates.isEmpty()) {
            attendedDates.clear();
        }
        if (!datesAttendedByStudent.isEmpty()) {
            datesAttendedByStudent.clear();
        }

        Student student = studentFacade.find(studentMB.getId());
        for (Attendancelist a : courseMB.getAttendancelistList()) {
            if (a.getStudentList().contains(student)) {
                datesAttendedByStudent.add(convertDateToLocalDate(a.getAttendanceDate()));
            }
        }
        setAttendedDatesForStudent();
    }

    public void setAttendedDatesForStudent() {
        Collections.sort(courseDates);
        for (LocalDate localDate : courseDates) {
            if (datesAttendedByStudent.contains(localDate)) {
                attendedDates.put(localDate, " X ");
            } else {
                attendedDates.put(localDate, "");
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
            courseDates.add(convertDateToLocalDate(a.getAttendanceDate()));
        }
        Collections.sort(courseDates);
    }
//
//    public void attDateToStudentAttendance() {
//        Student student = studentFacade.find(studentMB.getId());
//        List<Attendancelist> attLists = courseMB.getAttendancelistList();
//        List<Student> currentStudents = courseMB.getStudentList();
//        for (Attendancelist attendancelist : attLists) {
//            
//        }
//    }
}
