package com.rappandpoppa.model;

import com.rappandpoppa.model.origin.Person;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anders
 */
@ManagedBean
public class Student extends Person {

    private List<Course> courses = new ArrayList<>();
    private List<String> imageResources = new ArrayList<>();
    private List<String> fileResources = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
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

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
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

}
