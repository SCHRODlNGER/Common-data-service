package com.commondataservice.commonService.services;

import com.commondataservice.commonService.dto.*;
import com.commondataservice.commonService.entitiy.*;
import com.commondataservice.commonService.models.AddressInfoModel;
import com.commondataservice.commonService.models.MainScreenResponse;
import com.commondataservice.commonService.models.OrderInfoModel;
import org.apache.catalina.User;

import java.util.HashMap;
import java.util.List;

public interface Common_Service {


    OrderInfoDTO findOrderByUsername(String username);

    OrderInfoDTO makeOrder(OrderInfoModel orderInfoModel, String username);

    AddressInfoDTO findAddressInfoByUsername(String username);

    AddressInfoDTO saveAddressInfoByUsername(AddressInfoModel addressDetails, String username);

    UserInfo findByUserName(String username);

    UserInfo findUserByID(Integer user_id);

    RecipesDTO findAllRecipe();

    RecipesDTO findRecipeByQuery(String queryParams);

    MainScreenResponse getHomeScreenData(String apiName);

    HashMap<Integer, Recipe> findRecipeByIds(String queryParams);


}
