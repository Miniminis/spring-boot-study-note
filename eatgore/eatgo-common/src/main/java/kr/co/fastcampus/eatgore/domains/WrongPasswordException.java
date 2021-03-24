package kr.co.fastcampus.eatgore.domains;

public class WrongPasswordException extends RuntimeException{

    WrongPasswordException() {
        super("Password is wrong!");
    }

}
