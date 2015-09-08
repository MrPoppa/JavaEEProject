package com.rappandpoppa.model;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class Course {

    private Long id;
    private String name;
    private String courseCode;
    private String level;
    private String language;
    private String period;
    private int maxNumberOfStudents;
    private Teacher mainTeacher;
    private List<Student> courseStudents;
    private AttendanceList attendanceList;
    private List<Date> lectureDates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public Teacher getMainTeacher() {
        return mainTeacher;
    }

    public void setMainTeacher(Teacher mainTeacher) {
        this.mainTeacher = mainTeacher;
    }

    public List<Student> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<Student> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public AttendanceList getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(AttendanceList attendanceList) {
        this.attendanceList = attendanceList;
    }

    public void addLectureDate(Date lectureDate) {
        lectureDates.add(lectureDate);
    }

    public void removeLectureDate(Date lectureDate) {
        lectureDates.remove(lectureDate);
    }
}
