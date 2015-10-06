package com.rappandpoppa.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Benjamin
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object toValidate) throws ValidatorException {
        boolean valid = false;
        String value = null;
        if (context == null || component == null) {
            throw new NullPointerException();
        }
        if (!(component instanceof UIOutput)) {
            return;
        }

        String email = (String) toValidate;
        if (!email.matches("^[a-öA-Ö0-9._%+-]+@[a-öA-Ö0-9.-]+\\.[a-öA-Ö]{2,6}$")) {
            FacesMessage message = new FacesMessage("Wrong email format");
            throw new ValidatorException(message);
        }
    }

}
