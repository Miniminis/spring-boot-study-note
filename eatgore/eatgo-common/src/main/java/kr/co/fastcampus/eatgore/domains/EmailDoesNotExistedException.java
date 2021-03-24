package kr.co.fastcampus.eatgore.domains;

public class EmailDoesNotExistedException extends RuntimeException {

    EmailDoesNotExistedException(String email) {
        super("Email Does Not Existed" + email);
    }
}
