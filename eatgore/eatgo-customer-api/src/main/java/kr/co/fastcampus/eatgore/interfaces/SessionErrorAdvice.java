package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.domains.EmailDoesNotExistedException;
import kr.co.fastcampus.eatgore.domains.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SessionErrorAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongPasswordException.class)
    public String handleBadRequestWithPassword() {
        return "Password is wrong";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailDoesNotExistedException.class)
    public String handleBadRequestWithEmail() {
        return "Password is wrong";
    }

}
