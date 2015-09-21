package com.rappandpoppa.model;

import com.rappandpoppa.beans.ContactinformationFacadeLocal;
import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.entities.Contactinformation;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.model.origin.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Anders
 */
@ManagedBean
@ViewScoped
public class StudentMB extends Person {

    private List<CourseMB> courses = new ArrayList<>();
    private List<String> imageResources = new ArrayList<>();
    private List<String> fileResources = new ArrayList<>();
    private final List<StudentMB> students = new ArrayList<>();

    @EJB
    StudentFacadeLocal studentFacade;
    @EJB
    ContactinformationFacadeLocal contactFacade;

    public List<CourseMB> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseMB> courses) {
        this.courses = courses;
    }

    public List<String> getImageResources() {
        return imageResources;
    }

    public void setImageResources(List<String> imageResources) {
        this.imageResources = imageResources;
    }

    public List<String> getFileResources() {
        return fileResources;
    }

    public void setFileResources(List<String> fileResources) {
        this.fileResources = fileResources;
    }

    public void addCourse(CourseMB course) {
        courses.add(course);
    }

    public void removeCourse(CourseMB course) {
        courses.remove(course);
    }

    public void addImage(String imageResource) {
        imageResources.add(imageResource);
    }

    public void removeImage(String imageResource) {
        imageResources.remove(imageResource);
    }

    public void addFile(String fileResource) {
        fileResources.add(fileResource);
    }

    public void removeFile(String fileResource) {
        fileResources.remove(fileResource);
    }

    public List<StudentMB> getStudents() {
        return students;
    }

    public void addStudent() {
        Student student = new Student();
        student.setFirstName(this.getFirstName());
        student.setAge(this.getAge());
        student.setLastName(this.getLastName());
        student.setGender(this.getGender());
        Contactinformation contact = new Contactinformation();
        contact.setCity(this.getContactInformation().getCity());
        contact.setEmailAddress(this.getContactInformation().getEmailAddress());
        contact.setPhoneNumber(this.getContactInformation().getPhoneNumber());
        contact.setStreetName(this.getContactInformation().getStreetName());
        contact.setZipCode(this.getContactInformation().getZipCode());
        student.setContactinformation(contact);
        studentFacade.create(student);
    }

    public void viewStudent() {
        students.clear();
        Student foundStudent = studentFacade.findByFirstName(this.getFirstName());
        if (foundStudent != null) {
            this.setFirstName(foundStudent.getFirstName());
            this.setLastName(foundStudent.getLastName());
            this.setAge(foundStudent.getAge());
            this.setGender(foundStudent.getGender());
            this.getContactInformation().setCity(foundStudent.getContactinformation().getCity());
            this.getContactInformation().setEmailAddress(foundStudent.getContactinformation().getEmailAddress());
            this.getContactInformation().setPhoneNumber(foundStudent.getContactinformation().getPhoneNumber());
            this.getContactInformation().setStreetName(foundStudent.getContactinformation().getStreetName());
            this.getContactInformation().setZipCode(foundStudent.getContactinformation().getZipCode());
            students.add(this);
        }
    }

    public void viewAllStudents() {
        students.clear();
        List <Student> foundStudents = studentFacade.findAll();
        for (Student foundStudent: foundStudents) {
            StudentMB studentMB = new StudentMB();
            studentMB.setFirstName(foundStudent.getFirstName());
            studentMB.setLastName(foundStudent.getLastName());
            studentMB.setAge(foundStudent.getAge());
            studentMB.setGender(foundStudent.getGender());
            ContactInformationMB contactMB = new ContactInformationMB();
            contactMB.setCity(foundStudent.getContactinformation().getCity());
            contactMB.setEmailAddress(foundStudent.getContactinformation().getEmailAddress());
            contactMB.setPhoneNumber(foundStudent.getContactinformation().getPhoneNumber());
            contactMB.setStreetName(foundStudent.getContactinformation().getStreetName());
            contactMB.setZipCode(foundStudent.getContactinformation().getZipCode());
            studentMB.setContactInformation(contactMB);
            students.add(studentMB);
        }
    }
}
