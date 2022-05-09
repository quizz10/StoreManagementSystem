package se.iths.storemanagementsystem.exceptions.customexceptions;

public class WrongEmailFormatException extends RuntimeException {
    public WrongEmailFormatException(String message) {
        super(message);
    }
}
