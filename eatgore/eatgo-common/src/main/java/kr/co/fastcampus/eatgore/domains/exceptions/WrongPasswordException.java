package kr.co.fastcampus.eatgore.domains.exceptions;

import org.springframework.http.ResponseEntity;

public class WrongPasswordException extends RuntimeException{

    public WrongPasswordException() {
        super("Password is wrong!");
    }

}
