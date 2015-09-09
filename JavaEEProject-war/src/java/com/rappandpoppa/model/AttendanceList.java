package com.rappandpoppa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class AttendanceList {

    private Long id;
    private Map<Date, List> studentsPresentByDate = new HashMap<>();
    private List<Student> studentsPresent = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Date, List> getStudentsPresentByDate() {
        return studentsPresentByDate;
    }

    public void setStudentsPresentByDate(Map<Date, List> studentsPresentByDate) {
        this.studentsPresentByDate = studentsPresentByDate;
    }

    public void addPresentStudent(Student student, Date date) {
        studentsPresent.clear();
        studentsPresent = studentsPresentByDate.get(date);
        if (!studentsPresent.contains(student)) {
            studentsPresent.add(student);
            studentsPresentByDate.put(date, studentsPresent);
        }
    }

    public void removePresentStudent(Student student, Date date) {
        studentsPresent.clear();
        studentsPresent = studentsPresentByDate.get(date);
        if (studentsPresent.contains(student)) {
            studentsPresent.remove(student);
            studentsPresentByDate.put(date, studentsPresent);
        }
    }

    public void addListOfStudentsByDate(List<Student> studensPresent, Date date) {
        studentsPresentByDate.put(date, studentsPresent);
    }

    public void removeListofStudentsByDate(Date date) {
        studentsPresent = studentsPresentByDate.get(date);
        studentsPresent.clear();
        studentsPresentByDate.put(date, studentsPresent);
    }
}
