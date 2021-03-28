package kr.co.fastcampus.eatgore.repositories;

import kr.co.fastcampus.eatgore.domains.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
