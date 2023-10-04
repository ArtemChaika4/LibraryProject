package ua.edu.dnu.project.exception;
import java.time.LocalDateTime;

public class ServiceException extends Exception{
    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
    public ServiceException(String message) {
        super(message);
    }
    public void toLog() {
        String message = "Date: " + LocalDateTime.now() +
                " Class and method: " + getStackTrace()[getStackTrace().length - 1] +
                " Type: " + getClass() +
                " Text: " + getMessage();
        System.out.println(message);
    }
}
