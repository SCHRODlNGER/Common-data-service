package com.commondataservice.commonService.dao;

import com.commondataservice.commonService.entitiy.VegCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VegCategoryRepositoryTest {
    @Autowired
    private VegCategoryRepository vegCategoryRepository;

    @Test
    public void saveVegCategory(){
        VegCategory vegCategory = VegCategory.builder()
                .type("Veg").build();

        vegCategoryRepository.save(vegCategory);
    }

    @Test
    public void findByType(){
        Optional<VegCategory> vegCategory = vegCategoryRepository.findByType("Veg");
        System.out.println(vegCategory);
    }

}