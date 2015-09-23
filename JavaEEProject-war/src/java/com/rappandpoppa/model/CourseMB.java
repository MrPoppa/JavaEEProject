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

    private List<CourseMB> courses = new ArrayList<>();

    @EJB
    CourseFacadeLocal courseFacade;

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

    public List<CourseMB> getCourses() {
        return courses;
    }

    public void addCourse() {
        Course createdCourse = new Course();
        createdCourse.setCourseName(this.courseName);
        createdCourse.setCourseCode(this.courseCode);
        createdCourse.setCourseLanguage(this.language);
        createdCourse.setCoursePeriod(this.period);
        createdCourse.setCourseLevel(this.level);
        createdCourse.setMaxNumberOfStudents(this.maxNumberOfStudents);
        courseFacade.create(createdCourse);
    }

    public List<Course> viewAllCourses() {
        return this.courseFacade.findAll();
    }
    
    public List<String> getAllCourseNames() {
        List<Course> coursesFound = courseFacade.findAll();
        List<String> courseNames = new ArrayList<>();
        for (Course course : coursesFound) {
            courseNames.add(course.getCourseName());
        }
        return courseNames;
    }

//    public List<CourseMB> viewAllCourses() {
//        courses.clear();
//        List<Course> foundCourses = courseFacade.findAll();
//        for (Course foundCourse : foundCourses) {
//            CourseMB courseMB = new CourseMB();
//            courseMB.setCourseName(foundCourse.getCourseName());
//            courseMB.setCourseCode(foundCourse.getCourseCode());
//            courseMB.setLevel(foundCourse.getCourseLevel());
//            courseMB.setLanguage(foundCourse.getCourseLanguage());
//            courseMB.setPeriod(foundCourse.getCoursePeriod());
//            courseMB.setMaxNumberOfStudents(foundCourse.getMaxNumberOfStudents());
////            courseMB.setMainTeacher(foundCourse.getTeacher());
//            courses.add(courseMB);
//        }
//        return courses;
//    }
}
