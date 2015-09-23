package com.rappandpoppa.beans;

import com.rappandpoppa.entities.Principal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Anders
 */
@Local
public interface PrincipalFacadeLocal {

    void create(Principal principal);

    void edit(Principal principal);

    void remove(Principal principal);

    Principal find(Object id);

    List<Principal> findAll();

    List<Principal> findRange(int[] range);

    int count();
    
}
