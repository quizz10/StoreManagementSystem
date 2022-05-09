package se.iths.storemanagementsystem.exceptions.customexceptions;

public class ShortPasswordException extends RuntimeException {
    public ShortPasswordException(String message) {
        super(message);
    }
}

