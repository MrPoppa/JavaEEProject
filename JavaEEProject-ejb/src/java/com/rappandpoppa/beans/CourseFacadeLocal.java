package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Course;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benjamin
 */
@Local
public interface CourseFacadeLocal {

    void create(Course course);

    void edit(Course course);

    void remove(Course course);

    Course find(Object id);

    List<Course> findAll();
    
    Course findByCourseName(Object courseName);

    List<Course> findRange(int[] range);

    int count();
    
}
