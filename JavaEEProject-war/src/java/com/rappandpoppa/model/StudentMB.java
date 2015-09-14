package com.rappandpoppa.model;

import com.rappandpoppa.beans.StudentFacadeLocal;
import com.rappandpoppa.entities.Student;
import com.rappandpoppa.model.origin.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class StudentMB extends Person {

    private List<CourseMB> courses = new ArrayList<>();
    private List<String> imageResources = new ArrayList<>();
    private List<String> fileResources = new ArrayList<>();
    private Student student = new Student();
    
    @EJB
    StudentFacadeLocal studentFacade;

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
    
    public void addStudent() {
        student.setFirstName(this.getFirstName());
        student.setAge(this.getAge());
        student.setLastName(this.getLastName());
        student.setGender(this.getGender());
        this.studentFacade.create(student);
    }

}
