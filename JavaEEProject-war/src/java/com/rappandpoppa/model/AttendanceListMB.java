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
public class AttendanceListMB {

    private Long id;
    private Date date;
    private List<StudentMB> studentsPresent = new ArrayList<>();

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
    
    public List<StudentMB> getStudentsPresent() {
        return studentsPresent;
    }

    public void setStudentsPresent(List<StudentMB> studentsPresent) {
        this.studentsPresent = studentsPresent;
    }

    public void addPresentStudent(StudentMB student, Date date) {
        studentsPresent.clear();
        studentsPresent = AttendanceListArchive.getAttendanceLists().get(date).getStudentsPresent();
        if (!studentsPresent.contains(student)) {
            studentsPresent.add(student);
            AttendanceListArchive.addAttendanceList(this);
        }
    }

    public void removePresentStudent(StudentMB student, Date date) {
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
