package com.commondataservice.commonService.dao;

import com.commondataservice.commonService.entitiy.AddressInfo;
import com.commondataservice.commonService.entitiy.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressInfoRepository extends JpaRepository<AddressInfo, Integer> {


   Optional<List<AddressInfo> > findAllByUserInfo(UserInfo userInfo);

}
