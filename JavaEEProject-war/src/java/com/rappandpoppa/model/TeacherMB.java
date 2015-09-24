package com.rappandpoppa.model;

import com.rappandpoppa.beans.AttendancelistFacadeLocal;
import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.model.origin.Employee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class TeacherMB extends Employee {

    private List<Course> courses;
    private String chosenCourseName;
    private Date chosenDate;
    private Date periodStartDate;
    private Date periodEndDate;
    private List<Date> courseDates;
    private List<Student> attendingStudentsByCourseDate;

    @EJB
    TeacherFacadeLocal teacherFacade;

    @EJB
    AttendancelistFacadeLocal attendancelistFacade;

    
    public List<Course> getCourses() {
        return teacherFacade.find(1).getCourseList();
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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

    public List<Date> getCourseDates() {
        return courseDates;
    }

    public void setCourseDates(List<Date> courseDates) {
        this.courseDates = courseDates;
    }

    public List<Student> getAttendingStudentsByCourseDate() {
        return attendingStudentsByCourseDate;
    }

    public void setAttendingStudentsByCourseDate(List<Student> attendingStudentsByCourseDate) {
        this.attendingStudentsByCourseDate = attendingStudentsByCourseDate;
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
            courseDates = attendancelistFacade.findAllDatesByCourse(1);
        } else {
            courseDates = new ArrayList<>();
        }
    }

    public void getStudentsByCourseDate() {
        attendingStudentsByCourseDate.clear();
        attendingStudentsByCourseDate = attendancelistFacade.findAllStudentsByCourseDate(chosenDate, 1);
    }

    public void viewAttendingStudentsByCourseDate() {

    }
}
