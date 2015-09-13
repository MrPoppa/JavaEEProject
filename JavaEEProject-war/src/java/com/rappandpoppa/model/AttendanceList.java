package com.rappandpoppa.model;

import com.rappandpoppa.archive.AttendanceListArchive;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class AttendanceList {

    private Long id;
    private Date date;
    private List<Student> studentsPresent = new ArrayList<>();

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

    public void addPresentStudent(Student student, Date date) {
        studentsPresent.clear();
        studentsPresent = AttendanceListArchive.getAttendanceLists().get(date).getStudentsPresent();
        if (!studentsPresent.contains(student)) {
            studentsPresent.add(student);
            AttendanceListArchive.addAttendanceList(this);
        }
    }

    public void removePresentStudent(Student student, Date date) {
        studentsPresent.clear();
        studentsPresent = AttendanceListArchive.getAttendanceLists().get(date).getStudentsPresent();
        if (studentsPresent.contains(student)) {
            studentsPresent.remove(student);
            AttendanceListArchive.addAttendanceList(this);
        }
    }
    
    public void removeAttendanceList() {
        AttendanceListArchive.removeAttendanceList(this);
    }
}
