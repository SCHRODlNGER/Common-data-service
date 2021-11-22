package com.commondataservice.commonService.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderInfoRepositoryTest {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private AddressInfoRepository addressInfoRepository;



}