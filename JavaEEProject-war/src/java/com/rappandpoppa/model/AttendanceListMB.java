package com.rappandpoppa.model;

import com.rappandpoppa.beans.AttendancelistFacadeLocal;
import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
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
public class AttendanceListMB {

    private int id;
    private LocalDate date;
    private String courseName;
    private List<Student> studentsPresent = new ArrayList<>();

    @EJB
    AttendancelistFacadeLocal attendanceFacade;
    @EJB
    StudentFacadeLocal studentFacade;
    @EJB
    CourseFacadeLocal courseFacade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getStudentsPresent() {
        return studentsPresent;
    }

    public void setStudentsPresent(List<Student> studentsPresent) {
        this.studentsPresent = studentsPresent;
    }

    public void createAttendanceList() {
        Attendancelist attendanceList = new Attendancelist();
        attendanceList.setAttendanceDate(convertFromLocalDateToDate(this.date));
        Course courseFound = courseFacade.findByCourseName(courseName);
        attendanceList.setCourse(courseFound);
        attendanceFacade.create(attendanceList);
    }

    public Date convertFromLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
