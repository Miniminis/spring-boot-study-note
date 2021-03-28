package kr.co.fastcampus.eatgore.repositories;

import kr.co.fastcampus.eatgore.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
