package com.rappandpoppa.controllers;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Anders
 */
@ManagedBean
@ViewScoped
public class DateStatisticsController extends StatisticsController implements Serializable {

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
        courseMB.setStudentList(course.getStudentList());
    }

    public void addStudentToAttendancelist() {

        Attendancelist currentAttendancelist = attendancelistFacade.find(attendancelistMB.getId());
        List<Student> attendedStudents = currentAttendancelist.getStudentList();
        Student student = studentFacade.find(studentMB.getId());
        if (courseMB.getStudentList().contains(student)) {
            if (!attendedStudents.contains(student)) {
                attendedStudents.add(student);
                currentAttendancelist.setStudentList(attendedStudents);
                attendancelistFacade.edit(currentAttendancelist);
            }
        }
    }

    public void removeStudentFromAttendancelist() {
        Attendancelist currentAttendancelist = attendancelistFacade.find(attendancelistMB.getId());
        List<Student> attendedStudents = currentAttendancelist.getStudentList();
        Student student = studentFacade.find(studentMB.getId());
        if (attendedStudents.contains(student)) {
            attendedStudents.remove(student);
            currentAttendancelist.setStudentList(attendedStudents);
            attendancelistFacade.edit(currentAttendancelist);
        }
    }

    public List<Student> completeStudent(String query) {
        List<Student> filteredStudents = new ArrayList<>();
        if (courseMB.getId() != null) {
            List<Student> allStudents = courseMB.getStudentList();

            for (Student student : allStudents) {
                if (student.getFirstName().toLowerCase().startsWith(query.toLowerCase())) {
                    filteredStudents.add(student);
                }
            }
        }
        return filteredStudents;
    }

    public LocalDate convertDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
