package com.commondataservice.commonService.dao;

import com.commondataservice.commonService.entitiy.AddressInfo;
import com.commondataservice.commonService.entitiy.UserInfo;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressInfoRepositoryTest {

    @Autowired
    private AddressInfoRepository addressInfoRepository;

    @Autowired
    UserInfoRepository userInfoRepository ;

    @Test
    public void save_AddressInfo(){


        Optional<UserInfo> userInfo = userInfoRepository.getUserInfoByFirstname("asas");

        System.out.println(userInfo);

        AddressInfo addressInfo = AddressInfo.builder()
                .Address("address2")
                .phone("phone2")
                .zipcode("zipcode2")
                .userInfo(userInfo.get())
                .build();

        addressInfoRepository.save(addressInfo);


    }

    @Test
    public void find_AllAddressUsingUserID(){
        Optional<UserInfo> userInfo = userInfoRepository.getUserInfoByFirstname("asas");

        Optional<List<AddressInfo> > addressInfoList = addressInfoRepository.findAllByUserInfo(userInfo.get());

        addressInfoList.ifPresent(System.out::println);
    }


}