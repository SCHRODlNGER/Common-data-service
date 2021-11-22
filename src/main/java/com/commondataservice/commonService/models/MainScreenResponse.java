package com.commondataservice.commonService.models;

import com.commondataservice.commonService.entitiy.Restaurant;
import com.commondataservice.commonService.entitiy.VegCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MainScreenResponse implements Serializable {

    private List<Restaurant> restaurants;
    private List<VegCategory> vegCategories;

    public MainScreenResponse(List<Restaurant> restaurants, List<VegCategory> vegCategories) {
        this.restaurants = restaurants;
        this.vegCategories = vegCategories;
    }
}
