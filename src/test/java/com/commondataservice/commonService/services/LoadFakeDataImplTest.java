package com.commondataservice.commonService.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoadFakeDataImplTest {

    @Autowired
    private LoadFakeData loadFakeData;


    @Test
    void loadTestData() {
        loadFakeData.loadTestData();
    }
}