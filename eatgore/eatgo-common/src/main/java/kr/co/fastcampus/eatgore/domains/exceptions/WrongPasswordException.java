package kr.co.fastcampus.eatgore.domains.exceptions;

public class WrongPasswordException extends RuntimeException{

    WrongPasswordException() {
        super("Password is wrong!");
    }

}
