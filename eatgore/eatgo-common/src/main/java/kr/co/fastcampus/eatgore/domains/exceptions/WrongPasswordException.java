package kr.co.fastcampus.eatgore.domains.exceptions;

public class WrongPasswordException extends RuntimeException{

    public WrongPasswordException() {
        super("Password is wrong!");
    }

}
