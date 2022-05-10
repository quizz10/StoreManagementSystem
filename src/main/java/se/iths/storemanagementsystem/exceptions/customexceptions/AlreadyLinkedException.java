package se.iths.storemanagementsystem.exceptions.customexceptions;

public class AlreadyLinkedException extends RuntimeException{
    public AlreadyLinkedException(String message){
        super(message);
    }
}
