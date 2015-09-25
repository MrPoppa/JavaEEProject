package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Contactinformation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Anders
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
