package com.example.eat.application;

import com.example.eat.domain.Region;
import com.example.eat.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {

        this.regionRepository=regionRepository;
    }

    public List<Region> getRegions() {
        List<Region> regions = regionRepository.findAll();
        //regions.add (Region.builder ().name ("Seoul").build ());
        return regions;
    }


    /*public Region addRegion(String name) {
       Region region= Region.builder().name(name).build();
       regionRepository.save (region);
       return  region;
    }*/
}