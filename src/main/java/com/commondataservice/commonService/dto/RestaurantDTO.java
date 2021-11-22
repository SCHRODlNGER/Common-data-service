package com.commondataservice.commonService.dto;

import com.commondataservice.commonService.entitiy.OrderInfo;
import com.commondataservice.commonService.entitiy.Restaurant;

import java.io.Serializable;
import java.util.List;

public class RestaurantDTO implements Serializable {

    private List<Restaurant> restaurantList;
    private Integer totalCount;

    public RestaurantDTO(List<Restaurant> restaurantList){
        this.restaurantList = restaurantList;
        this.totalCount = restaurantList.size();
    }


}