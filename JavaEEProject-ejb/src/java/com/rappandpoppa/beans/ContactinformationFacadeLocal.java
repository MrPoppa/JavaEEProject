/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Contactinformation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benjamin
 */
@Local
public interface ContactinformationFacadeLocal {

    void create(Contactinformation contactinformation);

    void edit(Contactinformation contactinformation);

    void remove(Contactinformation contactinformation);

    Contactinformation find(Object id);

    List<Contactinformation> findAll();

    List<Contactinformation> findRange(int[] range);

    int count();
    
}
