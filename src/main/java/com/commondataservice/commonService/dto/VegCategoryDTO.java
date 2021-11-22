package com.commondataservice.commonService.dto;

import com.commondataservice.commonService.entitiy.Restaurant;
import com.commondataservice.commonService.entitiy.VegCategory;

import java.io.Serializable;
import java.util.List;

public class VegCategoryDTO implements Serializable {

    private List<VegCategory> vegCategories;
    private Integer totalCount;

    public VegCategoryDTO(List<VegCategory> vegCategories){
        this.vegCategories = vegCategories;
        this.totalCount = vegCategories.size();
    }


}