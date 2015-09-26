package com.rappandpoppa.model;

import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Teacher;
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

    private Integer id;
    private String courseName;
    private String courseCode;
    private String level;
    private String language;
    private int maxNumberOfStudents;
    private Teacher mainTeacher;
    private List<StudentMB> courseStudents = new ArrayList<>();
    private Attendancelist attendanceList;
    private List<Attendancelist> attendanceLists;
    private List<Date> lectureDates = new ArrayList<>();
    
    private Integer teacherId;

    private List<CourseMB> courses = new ArrayList<>();

    @EJB
    CourseFacadeLocal courseFacade;
    @EJB
    TeacherFacadeLocal teacherFacade;

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

    public List<StudentMB> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<StudentMB> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public Attendancelist getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(Attendancelist attendanceList) {
        this.attendanceList = attendanceList;
    }

    public List<Attendancelist> getAttendanceLists() {
        return attendanceLists;
    }

    public void setAttendanceLists(List<Attendancelist> attendanceLists) {
        this.attendanceLists = attendanceLists;
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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
    
    public void addCourse() {
        Course createdCourse = new Course();
        createdCourse.setCourseName(this.courseName);
        createdCourse.setCourseCode(this.courseCode);
        createdCourse.setCourseLanguage(this.language);
        createdCourse.setCourseLevel(this.level);
        createdCourse.setMaxNumberOfStudents(this.maxNumberOfStudents);
        this.mainTeacher = teacherFacade.find(teacherId);
        createdCourse.setTeacher(this.mainTeacher);
        courseFacade.create(createdCourse);
    }
    
    public void addAttendanceList() {
        this.attendanceLists.add(this.attendanceList);
        Course courseToUpdate = courseFacade.find(id);
        courseToUpdate.setAttendancelistList(attendanceLists);
        courseFacade.edit(courseToUpdate);
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
