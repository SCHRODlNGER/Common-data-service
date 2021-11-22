package com.commondataservice.commonService.dao;

import com.commondataservice.commonService.entitiy.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Optional<Restaurant> getRestaurantByName(String name);

    List<Restaurant> findAll();

    @Query(value = "SELECT id FROM restaurant where name in (?1)", nativeQuery = true)
    List<Integer> findRestaurantIdByName(Set<String> names);

    @Query(value = "select id from restaurant", nativeQuery = true)
    List<Integer> findAllIds();


}
