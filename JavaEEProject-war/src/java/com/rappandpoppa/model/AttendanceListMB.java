package com.rappandpoppa.model;

import com.rappandpoppa.archive.AttendanceListArchive;
import com.rappandpoppa.beans.AttendancelistFacadeLocal;
import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Student;
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

    private Long id;
    private Date date;
    private List<Student> studentsPresent = new ArrayList<>();

    @EJB
    AttendancelistFacadeLocal attendanceFacade;
    @EJB
    StudentFacadeLocal studentFacade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Student> getStudentsPresent() {
        return studentsPresent;
    }

    public void setStudentsPresent(List<Student> studentsPresent) {
        this.studentsPresent = studentsPresent;
    }

    public void addPresentStudent(Student student) {
        Attendancelist attendanceList = new Attendancelist();
        attendanceList.setAttendanceDate(this.date);
        studentsPresent.add(student);
        attendanceList.setStudentList(studentsPresent);
        attendanceFacade.create(attendanceList);
    }

    public void removeAttendanceList() {
        AttendanceListArchive.removeAttendanceList(this);
    }

}
