package com.address.book.addressbookapi.exception;

import com.address.book.addressbookapi.exception.customexception.ContactIdNotPresentException;
import com.address.book.addressbookapi.exception.customexception.ContactNotFoundInDatabaseException;
import com.address.book.addressbookapi.exception.customexception.EmptyDatabaseException;
import com.address.book.addressbookapi.exception.customexception.JsonProcessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<String> handleInvalidArgument(ConstraintViolationException ex) {
        log.error("Error occurred due to passing the invalid arguments");
        ArrayList<String> errorList = new ArrayList<>();
        ex.getConstraintViolations()
                .forEach(err ->
                        errorList.add(err.getMessage()));
        return errorList;
    }

    @ExceptionHandler(JsonProcessException.class)
    public String responseReadException() {
        log.error("Not able to process the data");
        return "Unable to process Data";
    }


    @ExceptionHandler(ResourceAccessException.class)
    public String handleConnectionTimeout() {
        log.error("Unable to make the connection to external api");
        return "Connection Is Not Established | Please try again later";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NullPointerException.class)
    public String handleFirstNameNull() {
        log.error("error occured due to invalid data");
        return "Doesn't Exist ! Please enter valid data";
    }

    @ExceptionHandler(EmptyDatabaseException.class)
    public String emptyDatabase() {
        log.error("Error occured because of Database is empty please make some entries in database.");
        return "|| Database is Empty ||";
    }

    @ExceptionHandler(ContactNotFoundInDatabaseException.class)
    public String firstNameNotFound() {
        log.error("Enter a valid name");
        return "|| This name is not in the Database Please try with other Name ||";
    }

    @ExceptionHandler(ContactIdNotPresentException.class)
    public String idNotFoundInDatabase() {
        log.error("this Contact Id is not in database | please enter a valid id");
        return "Contact Id Not present in database | try with other Id";
    }
}
