package com.rappandpoppa.model;

import com.rappandpoppa.beans.AttendancelistFacadeLocal;
import com.rappandpoppa.beans.ContactinformationFacadeLocal;
import com.rappandpoppa.beans.CourseFacadeLocal;
import com.rappandpoppa.beans.TeacherFacadeLocal;
import com.rappandpoppa.entities.Attendancelist;
import com.rappandpoppa.entities.Course;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.entities.Teacher;
import com.rappandpoppa.model.origin.Employee;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Anders
 */
@ManagedBean
@SessionScoped
public class TeacherMB extends Employee {

    private Teacher teacher;
    private List<Course> teacherCourses;
    private Map<Course, List<Attendancelist>> attendanceListsByCourses;
    private Course chosenCourse;
    private String chosenCourseName;
    private List<Attendancelist> attendanceListsByCourse;
    private List<LocalDate> courseDates = new ArrayList<>();
    private LocalDate chosenDate;
    private Attendancelist chosenAttendancelistByDate;
    private List<Student> studentsByCourse;
    private List<Student> attendingStudentsByCourseDate = new ArrayList<>();
    private List<String> courseNames;
    int chosenCourseId;

    @EJB
    TeacherFacadeLocal teacherFacade;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Course> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(List<Course> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public Map<Course, List<Attendancelist>> getAttendanceListsByCourses() {
        return attendanceListsByCourses;
    }

    public void setAttendanceListsByCourses(Map<Course, List<Attendancelist>> attendanceListsByCourses) {
        this.attendanceListsByCourses = attendanceListsByCourses;
    }

    public Course getChosenCourse() {
        return chosenCourse;
    }

    public void setChosenCourse(Course chosenCourse) {
        this.chosenCourse = chosenCourse;
    }

    public String getChosenCourseName() {
        return chosenCourseName;
    }

    public void setChosenCourseName(String chosenCourseName) {
        this.chosenCourseName = chosenCourseName;
    }

    public List<Attendancelist> getAttendanceListsByCourse() {
        return attendanceListsByCourse;
    }

    public void setAttendanceListsByCourse(List<Attendancelist> attendanceListsByCourse) {
        this.attendanceListsByCourse = attendanceListsByCourse;
    }

    public List<LocalDate> getCourseDates() {
        return courseDates;
    }

    public void setCourseDates(List<LocalDate> courseDates) {
        this.courseDates = courseDates;
    }

    public LocalDate getChosenDate() {
        return chosenDate;
    }

    public void setChosenDate(LocalDate chosenDate) {
        this.chosenDate = chosenDate;
    }

    public Attendancelist getChosenAttendancelistByDate() {
        return chosenAttendancelistByDate;
    }

    public void setChosenAttendancelistByDate(Attendancelist chosenAttendancelistByDate) {
        this.chosenAttendancelistByDate = chosenAttendancelistByDate;
    }

    public List<Student> getStudentsByCourse() {
        return studentsByCourse;
    }

    public void setStudentsByCourse(List<Student> studentsByCourse) {
        this.studentsByCourse = studentsByCourse;
    }

    public List<Student> getAttendingStudentsByCourseDate() {
        return attendingStudentsByCourseDate;
    }

    public void setAttendingStudentsByCourseDate(List<Student> attendingStudentsByCourseDate) {
        this.attendingStudentsByCourseDate = attendingStudentsByCourseDate;
    }

    public List<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }

    public int getChosenCourseId() {
        return chosenCourseId;
    }

    public void setChosenCourseId(int chosenCourseId) {
        this.chosenCourseId = chosenCourseId;
    }

    public TeacherFacadeLocal getTeacherFacade() {
        return teacherFacade;
    }

    public void setTeacherFacade(TeacherFacadeLocal teacherFacade) {
        this.teacherFacade = teacherFacade;
    }

    public List<Student> viewAttendingStudentsByCourseDate() {
        attendingStudentsByCourseDate.clear();
        if (chosenCourse != null) {
            for (Attendancelist a : attendanceListsByCourse) {
                if (convertDateToLocalDate(a.getAttendanceDate()) == chosenDate) {
                    attendingStudentsByCourseDate = a.getStudentList();
                    break;
                }
            }
        }
        return attendingStudentsByCourseDate;
    }

    public void onDateChange() {
        attendingStudentsByCourseDate.clear();
        Teacher teacher = teacherFacade.find(1);
        List<Course> teacherCourse = teacher.getCourseList();
        System.out.println(chosenDate);
        if (chosenCourse != null) {
            for (Attendancelist a : attendanceListsByCourse) {
                if (convertDateToLocalDate(a.getAttendanceDate()) == chosenDate) {
                    attendingStudentsByCourseDate = a.getStudentList();
                    for (Student s : attendingStudentsByCourseDate) {
                        System.out.println(s.getFirstName());
                    }
                    break;
                }
            }
        }
    }

    private void fetchTeacher() {
        teacher = teacherFacade.find(1);
    }

    private void fetchTeacherCourses() {
        fetchTeacher();
        teacherCourses = teacherFacade.findTeacherCourses(1);
    }

    private void fetchChosenCourse() {
        chosenCourse = teacherFacade.findCourseByCourseName(1, chosenCourseName);
    }

    private void fetchAttendanceListsByCourse() {
        attendanceListsByCourse = teacherFacade.findAttendanceListsByCourse(chosenCourseName, 1);
    }

    private void fetchChosenAttendanceListByDate() {
        chosenAttendancelistByDate
                = teacherFacade.findAttendanceListByDate(1, chosenCourseName, chosenDate);
    }

//    private void fetchStudentsByCourse() {
//        if (chosenCourse != null) {
//            studentsByCourse = chosenCourse.getStudentList();
//        }
//    }
    private void fetchAllStudensByCourseAndDate() {
        fetchChosenAttendanceListByDate();
        attendingStudentsByCourseDate
                = chosenAttendancelistByDate.getStudentList();
    }

    public List<Student> getStudents() {
        fetchTeacher();
        fetchTeacherCourses();
        fetchChosenCourse();
        fetchAttendanceListsByCourse();
        fetchAllStudensByCourseAndDate();

        return attendingStudentsByCourseDate;
    }

    public List<String> getAllCourseNames() {
        fetchTeacher();
        fetchTeacherCourses();
        courseNames = new ArrayList<>();
        for (Course course : teacherCourses) {
            String courseName = course.getCourseName();
            courseNames.add(courseName);
        }
        return courseNames;
    }

    public void onCourseChange() {
        if (chosenCourseName != null && !chosenCourseName.equals("")) {
            courseDates.clear();
            attendanceListsByCourse.clear();
            defineChosenCourse();
            defineAttendanceListDates();
        } else {
            courseDates = new ArrayList<>();
        }
    }

    private void defineChosenCourse() {
        System.out.println(chosenCourseName);
        for (Course course : teacherCourses) {
            if (chosenCourseName.equals(course.getCourseName())) {
                chosenCourse = course;
                break;
            }
        }
    }

    private void defineAttendanceListDates() {
        attendanceListsByCourse = chosenCourse.getAttendancelistList();
        for (Attendancelist a : attendanceListsByCourse) {
            LocalDate date1 = a.getAttendanceDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            courseDates.add(date1);
        }
    }

    private LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
