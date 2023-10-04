package ua.edu.dnu.project.exception;
import java.time.LocalDateTime;

public class ServiceException extends Exception{
    ServiceException(String message, Throwable e) {
        super(message, e);
    }
    ServiceException(String message) {
        super(message);
    }
    void toLog() {
        String message = "Date: " + LocalDateTime.now() +
                " Class and method: " + getStackTrace()[getStackTrace().length - 1] +
                " Type: " + getClass() +
                " Text: " + getMessage();
        System.out.println(message);
    }
}
