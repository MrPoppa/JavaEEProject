package com.rappandpoppa.model;

import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.model.origin.Employee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author Anders
 */
@ManagedBean
public class TeacherMB extends Employee {

    private List<Course> courses = new ArrayList<>();
    private String chosenCourseName;
    private Date chosenDate;
    private Date periodStartDate;
    private Date periodEndDate;
    private List<Date> courseDates;
    private List<Student> attendingStudentsByCourseDate;
    @Inject
    CourseMB courseMB;
    @Inject
    AttendanceListMB attendanceListMB;
   
    @EJB
    TeacherFacadeLocal teacherFacade;

    public List<Course> getCourses() {
        return courses;
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

    public void viewAllCourses() {
        courses.clear();
        courses = teacherFacade.findTeacherCourses();
    }

    public void onCourseChange() {
        if (chosenCourseName != null && !chosenCourseName.equals("")) {
//            Integer courseId = courseFacade.findIdByCourseName(chosenCourseName);
            courseDates = attendanceListMB.attendanceFacade.findAllDatesByCourse(courseMB.getId());
        } else {
            courseDates = new ArrayList<>();
        }
    }

    public void getStudentsByCourseDate() {
        attendingStudentsByCourseDate.clear();
        attendingStudentsByCourseDate = attendanceListMB.attendanceFacade.findAllStudentsByCourseDate(chosenDate, courseMB.getId());
    }

    public void viewAttendingStudentsByCourseDate() {

    }
}
