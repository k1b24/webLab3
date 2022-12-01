package com.kib.weblab3.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("tagInputValidator")
public class TagInputValidator implements Validator {

    private static final FacesMessage errorMessage = new FacesMessage("Тэг должен состоять из латинских букв");

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String input = (String) o;
        String[] splittedTags = input.split(",");
        if (splittedTags.length == 1 && splittedTags[0].equals("")) {
            return;
        }
        for (String elem:
             splittedTags) {
            if (!elem.matches("^[a-zA-Z ]+$")) {
                errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(errorMessage);
            }
        }
    }
}
