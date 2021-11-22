package com.commondataservice.commonService.dao;

import com.commondataservice.commonService.entitiy.OrderInfo;
import com.commondataservice.commonService.entitiy.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {

    Optional<List<OrderInfo> > findAllByUserInfo(UserInfo userInfo);


}
