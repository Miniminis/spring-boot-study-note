package kr.co.fastcampus.eatgore.domains.exceptions;

public class EmailDoesNotExistedException extends RuntimeException {

    public EmailDoesNotExistedException(String email) {
        super("Email Does Not Existed" + email);
    }
}
