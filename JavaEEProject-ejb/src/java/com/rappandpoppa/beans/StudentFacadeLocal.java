package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Student;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benjamin
 */
@Local
public interface StudentFacadeLocal {

    void create(Student student);

    void edit(Student student);

    void remove(Student student);

    Student find(Object id);

    List<Student> findAll();
    
    Student findOneByFirstName(String firstName);

    List<Student> findRange(int[] range);

    int count();

}
