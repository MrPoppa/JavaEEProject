package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Teacher;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Anders
 */
@Local
public interface TeacherFacadeLocal {

    void create(Teacher teacher);

    void edit(Teacher teacher);

    void remove(Teacher teacher);

    Teacher find(Object id);

    List<Teacher> findAll();

    List<Teacher> findRange(int[] range);

    int count();
    
}
