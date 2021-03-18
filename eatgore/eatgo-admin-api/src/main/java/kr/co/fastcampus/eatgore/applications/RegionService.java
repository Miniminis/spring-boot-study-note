package kr.co.fastcampus.eatgore.applications;

import kr.co.fastcampus.eatgore.domains.Region;
import kr.co.fastcampus.eatgore.domains.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getList() {
        return regionRepository.findAll();
    }

    public Region addRegion(Region region) {
        return regionRepository.save(region);
    }
}
