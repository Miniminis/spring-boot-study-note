package kr.co.fastcampus.eatgore.domains.exceptions;

public class NoAuthTokenException extends RuntimeException {

    public NoAuthTokenException() {
        super("No AccessToken Found or the token is not valid!");
    }
}
