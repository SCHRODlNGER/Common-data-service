package com.commondataservice.commonService.dao;

import com.commondataservice.commonService.entitiy.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> getUserInfoByFirstname(String firstName);

    Optional<UserInfo> findByUsername(String username);

    Optional<UserInfo> getUserInfoByFirstnameAndLastname(String firstname, String lastname);

    Optional<UserInfo> findUserInfoById(Integer id);

}
