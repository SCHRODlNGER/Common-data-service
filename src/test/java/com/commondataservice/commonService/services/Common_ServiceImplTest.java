package com.commondataservice.commonService.services;

import com.commondataservice.commonService.dao.*;
import com.commondataservice.commonService.entitiy.Recipe;
import com.commondataservice.commonService.entitiy.Restaurant;
import com.commondataservice.commonService.entitiy.VegCategory;
import com.commondataservice.commonService.models.MainScreenResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Common_ServiceImplTest {

    @Autowired
    private Common_Service commonService;


    @Test
    void findVegCategoryByType() {


    }

    @Test
    void findRestaurantByName() {

    }

    @Test
    void findRecipeByName() {

    }

    @Test
    void findOrderByUserFirstNameAndLastName() {
    }

    @Test
    void findOrderByUserId() {
    }

    @Test
    void findAddressInfoByUserId() {
    }

    @Test
    void findAddressInfoByUserFirstNameAndLastName() {
    }

    @Test
    void findRecipeByRestaurantName() {
    }

    @Test
    void findRecipeByVegCategory() {
    }

    @Test
    void findRecipeByVegCategoryAndRestaurantName() {
    }

    @Test
    void findRecipeByQuery() {


    }

    @Test
    void getHomeScreenData(){

    }


    @Test
    void findByUserName() {
    }

    @Test
    void findAllRestaurants() {
    }

    @Test
    void findAllVegCategory() {
    }

    @Test
    void findAllRecipe() {
    }
}