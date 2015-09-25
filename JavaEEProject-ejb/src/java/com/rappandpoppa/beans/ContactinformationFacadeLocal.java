package com.rappandpoppa.beans;

import com.rappandpoppa.entities.ContactInformation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Anders
 */
@Local
public interface ContactinformationFacadeLocal {

    void create(ContactInformation contactinformation);

    void edit(ContactInformation contactinformation);

    void remove(ContactInformation contactinformation);

    ContactInformation find(Object id);

    List<ContactInformation> findAll();

    List<ContactInformation> findRange(int[] range);

    int count();
    
}
