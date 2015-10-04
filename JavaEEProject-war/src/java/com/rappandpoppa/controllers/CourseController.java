package com.rappandpoppa.controllers;

import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.model.CourseMB;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Benjamin
 */
@ManagedBean
@RequestScoped
public class CourseController {

    private Integer studentToBeAddedId;
    private Integer teacherId;
    private List<Student> courseStudents = new ArrayList<>();
    private List<Attendancelist> newAttendanceLists = new ArrayList<>();
    private List<Attendancelist> completeAttendanceLists = new ArrayList<>();
    private Date startDate;
    private Integer numberOfWeeks;
    private List<String> daysOfTheWeek;
    private String searchTextLabel;
    private final List<Course> courses = new ArrayList<>();
    private Course courseToBeEdited;

    CourseMB courseMB = new CourseMB();

    @EJB
    CourseFacadeLocal courseFacade;
    @EJB
    StudentFacadeLocal studentFacade;
    @EJB
    TeacherFacadeLocal teacherFacade;

    public Integer getStudentToBeAddedId() {
        return studentToBeAddedId;
    }

    public void setStudentToBeAddedId(Integer studentToBeAddedId) {
        this.studentToBeAddedId = studentToBeAddedId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public List<Attendancelist> getNewAttendanceLists() {
        return newAttendanceLists;
    }

    public void setNewAttendanceLists(List<Attendancelist> newAttendanceLists) {
        this.newAttendanceLists = newAttendanceLists;
    }

    public List<Attendancelist> getCompleteAttendanceLists() {
        return completeAttendanceLists;
    }

    public void setCompleteAttendanceLists(List<Attendancelist> completeAttendanceLists) {
        this.completeAttendanceLists = completeAttendanceLists;
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

    public List<Course> getCourses() {
        return courses;
    }

    public String getSearchTextLabel() {
        return searchTextLabel;
    }

    public void setSearchTextLabel(String searchTextLabel) {
        this.searchTextLabel = searchTextLabel;
    }

    public Course getCourseToBeEdited() {
        return courseToBeEdited;
    }

    public void setCourseToBeEdited(Course courseToBeEdited) {
        this.courseToBeEdited = courseToBeEdited;
    }

    public CourseMB getCourseMB() {
        return courseMB;
    }

    public void setCourseMB(CourseMB courseMB) {
        this.courseMB = courseMB;
    }

    public List<Student> getCourseStudents() {
        Integer courseId = courseMB.getId();
        if (courseId != null) {
            return courseStudents = courseFacade.find(courseId).getStudentList();
        } else {
            return null;
        }
    }

    public void addCourse() {
        Course createdCourse = new Course();
        String courseCode = generateCourseCode();
        int numberOfAttendanceLists = daysOfTheWeek.size() * numberOfWeeks;
        createdCourse.setCourseName(courseMB.getCourseName());
        createdCourse.setCourseCode(courseCode);
        createdCourse.setCourseLanguage(courseMB.getLanguage());
        createdCourse.setCourseLevel(courseMB.getLevel());
        createdCourse.setMaxNumberOfStudents(courseMB.getMaxNumberOfStudents());
        createdCourse.setTeacher(teacherFacade.find(teacherId));
        for (int i = 0; i < numberOfAttendanceLists; ++i) {
            Attendancelist attendanceList = new Attendancelist();
            newAttendanceLists.add(attendanceList);
        }
        for (String day : daysOfTheWeek) {
            Calendar cal = Calendar.getInstance();
            boolean firstAttendanceList = true;
            Date attendanceDate = startDate;
            for (int i = 0; i < numberOfWeeks; ++i) {
                Attendancelist attendanceList = newAttendanceLists.get(0);
                switch (day) {
                    case "Mon":
                        if (firstAttendanceList) {
                            attendanceList.setAttendanceDate(startDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                            firstAttendanceList = false;
                        } else {
                            cal.setTime(attendanceDate);
                            cal.add(Calendar.DATE, 7);
                            attendanceDate = cal.getTime();
                            attendanceList.setAttendanceDate(attendanceDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                        }
                        break;
                    case "Tue":
                        if (firstAttendanceList) {
                            cal.setTime(startDate);
                            cal.add(Calendar.DATE, 1);
                            attendanceDate = cal.getTime();
                            attendanceList.setAttendanceDate(attendanceDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                            firstAttendanceList = false;
                        } else {
                            cal.setTime(attendanceDate);
                            cal.add(Calendar.DATE, 7);
                            attendanceDate = cal.getTime();
                            attendanceList.setAttendanceDate(attendanceDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                        }
                        break;
                    case "Wed":
                        if (firstAttendanceList) {
                            cal.setTime(startDate);
                            cal.add(Calendar.DATE, 2);
                            attendanceDate = cal.getTime();
                            attendanceList.setAttendanceDate(attendanceDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                            firstAttendanceList = false;
                        } else {
                            cal.setTime(attendanceDate);
                            cal.add(Calendar.DATE, 7);
                            attendanceDate = cal.getTime();
                            attendanceList.setAttendanceDate(attendanceDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                        }
                        break;
                    case "Thu":
                        if (firstAttendanceList) {
                            cal.setTime(startDate);
                            cal.add(Calendar.DATE, 3);
                            attendanceDate = cal.getTime();
                            attendanceList.setAttendanceDate(attendanceDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                            firstAttendanceList = false;
                        } else {
                            cal.setTime(attendanceDate);
                            cal.add(Calendar.DATE, 7);
                            attendanceDate = cal.getTime();
                            attendanceList.setAttendanceDate(attendanceDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                        }
                        break;
                    case "Fri":
                        if (firstAttendanceList) {
                            cal.setTime(startDate);
                            cal.add(Calendar.DATE, 4);
                            attendanceDate = cal.getTime();
                            attendanceList.setAttendanceDate(attendanceDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                            firstAttendanceList = false;
                        } else {
                            cal.setTime(attendanceDate);
                            cal.add(Calendar.DATE, 7);
                            attendanceDate = cal.getTime();
                            attendanceList.setAttendanceDate(attendanceDate);
                            attendanceList.setCourse(createdCourse);
                            completeAttendanceLists.add(attendanceList);
                            newAttendanceLists.remove(attendanceList);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        createdCourse.setAttendancelistList(completeAttendanceLists);
        courseFacade.create(createdCourse);
    }

    public String generateCourseCode() {
        char[] letters = courseMB.getCourseName().toCharArray();
        StringBuilder sb1 = new StringBuilder();
        for (char letter : letters) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(letter);
            if (sb2.toString().matches("^[A-Z0-9]+$")) {
                sb1.append(sb2.toString());
            }
        }
        sb1.append("-15");
        return sb1.toString();
    }

    public void addStudents() {
        Course courseToBeEdited = courseFacade.find(courseMB.getId());
        courseMB.setMaxNumberOfStudents(courseToBeEdited.getMaxNumberOfStudents());
        if (courseStudents.size() < courseMB.getMaxNumberOfStudents()) {
            courseStudents.add(studentFacade.find(studentToBeAddedId));
            courseToBeEdited.setStudentList(courseStudents);
            courseFacade.edit(courseToBeEdited);
        }
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

    public List<Course> completeCourse(String query) {
        List<Course> allCourses = courseFacade.findAll();
        List<Course> filteredCourses = new ArrayList<>();

        for (Course course : allCourses) {
            if (course.getCourseName().toLowerCase().startsWith(query.toLowerCase())) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }

    public void viewStudent() {
        courses.clear();
        Course foundCourse = courseFacade.find(courseMB.getId());
        if (foundCourse != null) {
            courses.add(foundCourse);
            searchTextLabel = foundCourse.getCourseName();
        }
    }

    public void viewAllStudents() {
        courses.clear();
        List<Course> foundCourses = courseFacade.findAll();
        for (Course foundCourse : foundCourses) {
            courses.add(foundCourse);
        }
    }

    public void selectStudent() {
        if (courseMB.getId() != null) {
            courseToBeEdited = courseFacade.find(courseMB.getId());
            courseMB.setCourseName(courseToBeEdited.getCourseName());
            courseMB.setCourseCode(courseToBeEdited.getCourseCode());
            courseMB.setId(courseToBeEdited.getId());
            courseMB.setLanguage(courseToBeEdited.getCourseLanguage());
            courseMB.setLevel(courseToBeEdited.getCourseLevel());
            courseMB.setMaxNumberOfStudents(courseToBeEdited.getMaxNumberOfStudents());
            courseMB.setStudentList(courseToBeEdited.getStudentList());
            courseMB.setAttendancelistList(courseToBeEdited.getAttendancelistList());

        }
    }

    public void updateCourse() {
        if (courseMB.getId() != null) {
            courseToBeEdited.setCourseName(courseMB.getCourseName());
            courseToBeEdited.setCourseCode(courseMB.getCourseCode());
            courseToBeEdited.setId(courseMB.getId());
            courseToBeEdited.setCourseLanguage(courseMB.getLanguage());
            courseToBeEdited.setCourseLevel(courseMB.getLevel());
            courseToBeEdited.setMaxNumberOfStudents(courseMB.getMaxNumberOfStudents());
            courseToBeEdited.setStudentList(courseMB.getStudentList());
            courseToBeEdited.setAttendancelistList(courseMB.getAttendancelistList());

            courseFacade.edit(courseToBeEdited);
        }
    }

    public void deleteStudent() {
        if (courseMB.getId() != null) {
            courseToBeEdited.setCourseName(courseMB.getCourseName());
            courseToBeEdited.setCourseCode(courseMB.getCourseCode());
            courseToBeEdited.setId(courseMB.getId());
            courseToBeEdited.setCourseLanguage(courseMB.getLanguage());
            courseToBeEdited.setCourseLevel(courseMB.getLevel());
            courseToBeEdited.setMaxNumberOfStudents(courseMB.getMaxNumberOfStudents());
            courseToBeEdited.setStudentList(courseMB.getStudentList());
            courseToBeEdited.setAttendancelistList(courseMB.getAttendancelistList());

            courseFacade.edit(courseToBeEdited);
        }
    }

}
