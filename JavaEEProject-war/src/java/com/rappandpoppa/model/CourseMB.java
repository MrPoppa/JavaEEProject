package com.rappandpoppa.model;

import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.entities.Course;
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
public class CourseMB {

    private Long id;
    private String courseName;
    private String courseCode;
    private String level;
    private String language;
    private String period;
    private int maxNumberOfStudents;
    private TeacherMB mainTeacher;
    private List<StudentMB> courseStudents = new ArrayList<>();
    private AttendanceListMB attendanceList;
    private List<Date> lectureDates = new ArrayList<>();
    
    @EJB
    CourseFacadeLocal courseFacadeLocal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public TeacherMB getMainTeacher() {
        return mainTeacher;
    }

    public void setMainTeacher(TeacherMB mainTeacher) {
        this.mainTeacher = mainTeacher;
    }

    public List<StudentMB> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<StudentMB> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public AttendanceListMB getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(AttendanceListMB attendanceList) {
        this.attendanceList = attendanceList;
    }

    public void addLectureDate(Date lectureDate) {
        lectureDates.add(lectureDate);
    }

    public void removeLectureDate(Date lectureDate) {
        lectureDates.remove(lectureDate);
    }

    public List<Date> getLectureDates() {
        return lectureDates;
    }

    public void setLectureDates(List<Date> lectureDates) {
        this.lectureDates = lectureDates;
    }
    
    public void addCourse() {
        Course createdCourse = new Course();
        createdCourse.setCourseName(this.courseName);
        createdCourse.setCourseCode(this.courseCode);
        createdCourse.setCourseLanguage(this.language);
        createdCourse.setCoursePeriod(this.period);
        createdCourse.setCourseLevel(this.level);
        createdCourse.setMaxNumberOfStudents(this.maxNumberOfStudents);
        courseFacadeLocal.create(createdCourse);
    }
}
