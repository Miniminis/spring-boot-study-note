package kr.co.fastcampus.eatgore.interfaces;

import kr.co.fastcampus.eatgore.applications.RegionService;
import kr.co.fastcampus.eatgore.domains.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list() {
        return regionService.getList();
    }

    @PostMapping("/regions")
    public ResponseEntity<?> create(@RequestBody Region region)
            throws URISyntaxException {

        regionService.addRegion(region);

        URI location = new URI("/regions");
        return ResponseEntity.created(location).body("{}");
        }

}
