package com.commondataservice.commonService.dao;

import com.commondataservice.commonService.entitiy.VegCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface VegCategoryRepository extends JpaRepository<VegCategory, Integer> {

    Optional<VegCategory> findByType(String type);

    @Query(value = "select id from veg_category", nativeQuery = true)
    List<Integer> findAllIds();

    @Query(value = "SELECT id FROM veg_category where type in ?1", nativeQuery = true)
    List<Integer> findVegCategoryIdByType(Set<String> type);
}
