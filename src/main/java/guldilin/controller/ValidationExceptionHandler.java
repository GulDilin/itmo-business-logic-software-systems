package guldilin.controller;

import guldilin.config.ErrorCodes;
import guldilin.dto.ErrorDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.exceptions.ObjectAlreadyExists;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface ValidationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    default Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    default ErrorDTO handleDatabaseError(Exception ex) {
        return ErrorDTO.builder()
                .error(ErrorCodes.DATABASE_ERROR)
                .message(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NoSuchObject.class, InvalidDataAccessApiUsageException.class})
    default ErrorDTO handleIllegalArgument(Exception ex) {
        return ErrorDTO.builder()
                .error(ErrorCodes.ILLEGAL_ARGUMENT)
                .message(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NoSuchObject.class})
    default ErrorDTO handleNoSuchObject(NoSuchObject ex) {
        return ErrorDTO.builder()
                .error(ErrorCodes.NO_SUCH_OBJECT)
                .message(ex.getMessage())
                .model(ex.getModel())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ObjectAlreadyExists.class})
    default ErrorDTO handleObjectAlreadyExists(ObjectAlreadyExists ex) {
        return ErrorDTO.builder()
                .error(ErrorCodes.OBJECT_ALREADY_EXISTS)
                .message(ex.getMessage())
                .model(ex.getModel())
                .build();
    }
}
