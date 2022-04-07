package com.address.book.addressbookapi.exception;

import com.address.book.addressbookapi.exception.customexception.ContactIdNotPresentException;
import com.address.book.addressbookapi.exception.customexception.ContactNotFoundInDatabaseException;
import com.address.book.addressbookapi.exception.customexception.EmptyDatabaseException;
import com.address.book.addressbookapi.exception.customexception.JsonProcessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<String> handleInvalidArgument(ConstraintViolationException ex) {
        ArrayList<String> errorList = new ArrayList<>();
        ex.getConstraintViolations()
                .forEach(err ->
                        errorList.add(err.getMessage()));
        return errorList;
    }

    @ExceptionHandler(JsonProcessException.class)
    public String responseReadException() {
        return "Unable to process Data";
    }


    @ExceptionHandler(ResourceAccessException.class)
    public String handleConnectionTimeout() {
        return "Connection Is Not Established | Please try again later";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NullPointerException.class)
    public String handleFirstNameNull() {
        return "Doesn't Exist ! Please enter valid data";
    }

    @ExceptionHandler(EmptyDatabaseException.class)
    public String emptyDatabase() {
        return "|| Database is Empty ||";
    }

    @ExceptionHandler(ContactNotFoundInDatabaseException.class)
    public String firstNameNotFound() {
        return "|| This name is not in the Database Please try with other Name ||";
    }

    @ExceptionHandler(ContactIdNotPresentException.class)
    public String idNotFoundInDatabase() {
        return "Contact Id Not present in database | try with other Id";
    }
}
