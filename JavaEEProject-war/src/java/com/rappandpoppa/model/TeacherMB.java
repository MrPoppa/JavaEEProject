package com.rappandpoppa.model;

import com.rappandpoppa.beans.AttendancelistFacadeLocal;
import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.model.origin.Employee;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class TeacherMB extends Employee {

    private List<Course> courses;
    private Course chosenCourse;
    private String chosenCourseName;
    private Date chosenDate;
    private Date periodStartDate;
    private Date periodEndDate;
    private List<LocalDate> courseDates = new ArrayList<>();
    private List<Student> attendingStudentsByCourse;
    private List<Student> attendingStudentsByCourseDate;
    private List<Attendancelist> attendancelistList = new ArrayList<>();
    int chosenCourseId;

    @EJB
    TeacherFacadeLocal teacherFacade;

    @EJB
    AttendancelistFacadeLocal attendancelistFacade;

    @EJB
    CourseFacadeLocal courseFacade;

    public List<Course> getCourses() {
//        courses = teacherFacade.find(this.getId()).getCourseList();
        courses = teacherFacade.find(1).getCourseList();

        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course getChosenCourse() {
        return chosenCourse;
    }

    public void setChosenCourse(Course chosenCourse) {
        this.chosenCourse = chosenCourse;
    }

    public List<Student> getAttendingStudentsByCourseDate() {
        return attendingStudentsByCourseDate;
    }

    public void setAttendingStudentsByCourseDate(List<Student> attendingStudentsByCourseDate) {
        this.attendingStudentsByCourseDate = attendingStudentsByCourseDate;
    }

    public String getChosenCourseName() {
        return chosenCourseName;
    }

    public void setChosenCourseName(String chosenCourseName) {
        this.chosenCourseName = chosenCourseName;
    }

    public Date getChosenDate() {
        return chosenDate;
    }

    public void setChosenDate(Date chosenDate) {
        this.chosenDate = chosenDate;
    }

    public List<LocalDate> getCourseDates() {
        return courseDates;
    }

    public void setCourseDates(List<LocalDate> courseDates) {
        this.courseDates = courseDates;
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(Date periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public Date getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(Date periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    public void onCourseChange() {
        if (chosenCourseName != null && !chosenCourseName.equals("")) {
            chosenCourseId = chosenCourse.getId();
            courseDates.clear();
            attendancelistList.clear();
            attendancelistList = courseFacade.find(chosenCourseId).getAttendancelistList();
            attendingStudentsByCourse = chosenCourse.getStudentList();
//            attendingStudentsByCourseDate = attendingStudentsByCourse.get

            for (Attendancelist a : attendancelistList) {
                LocalDate date1 = a.getAttendanceDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                courseDates.add(date1);
            }
        } else {
            courseDates = new ArrayList<>();
        }
    }

    public void onDateChange() {
        attendingStudentsByCourseDate.clear();
        if (chosenCourse != null) {
            for (Attendancelist a : attendancelistList) {
                if (a.getAttendanceDate() == chosenDate) {
                    attendingStudentsByCourseDate = a.getStudentList();
                }
            }
        }
//        attendingStudentsByCourseDate = attendancelistFacade.findAllStudentsByCourseDate(chosenDate, chosenCourseId);
    }

}
