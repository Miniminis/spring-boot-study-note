package kr.co.fastcampus.eatgore.domains;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewTest {

    @Test
    public void creteReview() {
        Review review = Review.builder()
                .id(134L)
                .restaurantId(1004L)
                .name("test writer")
                .score(2)
                .description("핵 맛없어...").build();

        assertThat(review.getId()).isEqualTo(134L);
        assertThat(review.getRestaurantId()).isEqualTo(1004L);
        assertThat(review.getName()).isEqualTo("test writer");
        assertThat(review.getScore()).isEqualTo(2);
        assertThat(review.getDescription()).isEqualTo("핵 맛없어...");
    }

}