package kr.co.fastcampus.eatgore.domains;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(long id) {
        super("Could Not Found Restaurant " + id);
    }
}
