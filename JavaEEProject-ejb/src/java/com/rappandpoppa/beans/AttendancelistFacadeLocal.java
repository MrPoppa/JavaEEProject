/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Attendancelist;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benjamin
 */
@Local
public interface AttendancelistFacadeLocal {

    void create(Attendancelist attendancelist);

    void edit(Attendancelist attendancelist);

    void remove(Attendancelist attendancelist);

    Attendancelist find(Object id);

    List<Attendancelist> findAll();

    List<Attendancelist> findRange(int[] range);

    int count();
    
}
