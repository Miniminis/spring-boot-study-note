package kr.co.fastcampus.eatgore.domains.exceptions;

public class EmailExistedException extends RuntimeException{

    public EmailExistedException(String email) {
        super("Email is already registered :" +  email);
    }
}
