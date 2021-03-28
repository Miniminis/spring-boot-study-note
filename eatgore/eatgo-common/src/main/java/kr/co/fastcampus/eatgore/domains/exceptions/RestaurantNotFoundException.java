package kr.co.fastcampus.eatgore.domains.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(long id) {
        super("Could Not Found Restaurant " + id);
    }
}
