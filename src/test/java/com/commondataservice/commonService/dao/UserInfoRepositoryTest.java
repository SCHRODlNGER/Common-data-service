package com.commondataservice.commonService.dao;

import com.commondataservice.commonService.entitiy.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void printUserInfo(){

        List<UserInfo> userInfoList = userInfoRepository.findAll();

        System.out.println(userInfoList);
    }

}