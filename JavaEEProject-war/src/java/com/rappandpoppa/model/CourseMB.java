package com.rappandpoppa.model;

import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.entities.Teacher;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Anders
 */
@ManagedBean
@RequestScoped
public class CourseMB {

    private Integer id;
    private String courseName;
    private String courseCode;
    private String level;
    private String language;
    private int maxNumberOfStudents;
    private Teacher mainTeacher;
    private Integer studentToBeAddedId;
    private List<Student> courseStudents = new ArrayList<>();
    private List<Attendancelist> attendanceLists;
    private List<Date> lectureDates = new ArrayList<>();
    private Date startDate;
    private Integer numberOfWeeks;
    private List<String> daysOfTheWeek;

    private Integer teacherId;

    @EJB
    CourseFacadeLocal courseFacade;
    @EJB
    TeacherFacadeLocal teacherFacade;
    @EJB
    StudentFacadeLocal studentFacade;

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

    public Integer getStudentToBeAddedId() {
        return studentToBeAddedId;
    }

    public void setStudentToBeAddedId(Integer studentToBeAddedId) {
        this.studentToBeAddedId = studentToBeAddedId;
    }

    public List<Student> getCourseStudents() {
        if (id != null) {
            return courseStudents = courseFacade.find(id).getStudentList();
        } else {
            return null;
        }
    }

    public void setCourseStudents(List<Student> courseStudents) {
        this.courseStudents = courseStudents;
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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public void setNumberOfWeeks(Integer numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    public List<String> getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(List<String> daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }

    public void addCourse() {
        Course createdCourse = new Course();
        int numberOfAttendanceLists = daysOfTheWeek.size() * numberOfWeeks;
        createdCourse.setCourseName(this.courseName);
        createdCourse.setCourseCode(this.courseCode);
        createdCourse.setCourseLanguage(this.language);
        createdCourse.setCourseLevel(this.level);
        createdCourse.setMaxNumberOfStudents(this.maxNumberOfStudents);
        this.mainTeacher = teacherFacade.find(teacherId);
        createdCourse.setTeacher(this.mainTeacher);
        for (int i = 0; i < numberOfAttendanceLists; ++i) {
            Attendancelist attendanceList = new Attendancelist();
            if (i == 0) {
                attendanceList.setAttendanceDate(startDate);
            } else {
                for (String day : daysOfTheWeek) {
                    switch (day) {
                        case "Mon":
                            attendanceList.setAttendanceDate(startDate);

                    }
                }
            }

        }
        courseFacade.create(createdCourse);
    }

//    public void addAttendanceList() {
//        this.attendanceLists.add(this.attendanceList);
//        Course courseToUpdate = courseFacade.find(id);
//        courseToUpdate.setAttendancelistList(attendanceLists);
//        courseFacade.edit(courseToUpdate);
//    }
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

    public void addStudents() {
        Course courseToBeEdited = courseFacade.find(id);
        this.maxNumberOfStudents = courseToBeEdited.getMaxNumberOfStudents();
        if (courseStudents.size() < maxNumberOfStudents) {
            courseStudents.add(studentFacade.find(studentToBeAddedId));
            courseToBeEdited.setStudentList(courseStudents);
            courseFacade.edit(courseToBeEdited);
        }
    }
}
