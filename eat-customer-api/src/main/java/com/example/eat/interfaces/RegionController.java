package com.example.eat.interfaces;

import com.example.eat.application.RegionService;
import com.example.eat.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Configuration
@CrossOrigin
@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list(){
        List<Region> regions= regionService.getRegions();

        return regions;
    }

   /* @PostMapping("/regions")
    public ResponseEntity<?> create(
            @RequestBody Region resource
    ) throws URISyntaxException {
        String name= resource.getName ();
        Region region=  regionService.addRegion (name);
        String url="/regions/"+region.getId();
        return ResponseEntity.created (new URI (url)).body ("{}");
    }*/
}
