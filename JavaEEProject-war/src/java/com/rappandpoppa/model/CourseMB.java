package com.rappandpoppa.model;

import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Student;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class CourseMB {

    private Integer id;
    private String courseName;
    private String level;
    private String language;
    private int maxNumberOfStudents;
    private List<Student> studentList;
    private List<Attendancelist> attendancelistList;
    private String courseCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Attendancelist> getAttendancelistList() {
        return attendancelistList;
    }

    public void setAttendancelistList(List<Attendancelist> attendancelistList) {
        this.attendancelistList = attendancelistList;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
