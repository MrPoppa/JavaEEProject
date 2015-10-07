package com.rappandpoppa.model;

import com.rappandpoppa.beans.AttendancelistFacadeLocal;
import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Anders
 */
@ManagedBean
@ViewScoped
public class AttendanceListMB implements Serializable {

    private int id;
    private LocalDate date;
    private CourseMB courseMB = new CourseMB();
    List<Attendancelist> attendanceLists = new ArrayList<>();
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

    public List<Student> getStudentsPresent() {
        return studentsPresent;
    }

    public void setStudentsPresent(List<Student> studentsPresent) {
        this.studentsPresent = studentsPresent;
    }

    public CourseMB getCourseMB() {
        return courseMB;
    }

    public void setCourseMB(CourseMB courseMB) {
        this.courseMB = courseMB;
    }

    public void createAttendanceList() {
        Attendancelist attendanceList = new Attendancelist();
        attendanceList.setAttendanceDate(convertFromLocalDateToDate(this.date));
        Course courseFound = courseFacade.find(courseMB.getId());
        attendanceList.setCourse(courseFound);
        attendanceFacade.create(attendanceList);
    }

    public List<Attendancelist> getAttendanceLists() {
        return attendanceLists;
    }
    
    public void setCourseAttendanceLists() {
        attendanceLists.clear();
        if (courseMB.getId() != null) {
            Course course = courseFacade.find(courseMB.getId());
            attendanceLists = course.getAttendancelistList();
        }
    }

    public void removeAttendanceList() {
        Attendancelist attendanceList = attendanceFacade.find(id);
        attendanceLists.remove(attendanceList);
        attendanceFacade.remove(attendanceList);
    }

    public Date convertFromLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
