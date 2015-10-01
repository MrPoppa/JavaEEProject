package com.rappandpoppa.controllers;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.model.AttendanceListMB;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Anders
 */
@ManagedBean
@SessionScoped
public class DateStatisticsController extends StatisticsController {

    private List<Student> attendingStudentsByCourseDate = new ArrayList<>();

    public List<Student> getAttendingStudentsByCourseDate() {
        if (attendancelistMB.getId() != 0) {
            return attendancelistFacade.find(attendancelistMB.getId()).getStudentList();
        }
        return new ArrayList<>();
    }

    public void setAttendingStudentsByCourseDate(List<Student> attendingStudentsByCourseDate) {
        this.attendingStudentsByCourseDate = attendingStudentsByCourseDate;
    }

    public void onCourseChange() {
        loadCourseMB(courseFacade.find(courseMB.getId()));
    }

    private void loadCourseMB(Course course) {
        courseMB.setCourseName(course.getCourseName());
        courseMB.setCourseCode(course.getCourseCode());
        courseMB.setLevel(course.getCourseLevel());
        courseMB.setLanguage(course.getCourseLanguage());
        courseMB.setMaxNumberOfStudents(course.getMaxNumberOfStudents());
        courseMB.setAttendancelistList(course.getAttendancelistList());
    }
}
