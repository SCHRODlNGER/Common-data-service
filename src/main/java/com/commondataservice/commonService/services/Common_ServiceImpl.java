package com.commondataservice.commonService.services;

import com.commondataservice.commonService.dao.*;
import com.commondataservice.commonService.dto.*;
import com.commondataservice.commonService.entitiy.*;
import com.commondataservice.commonService.models.AddressInfoModel;
import com.commondataservice.commonService.models.MainScreenResponse;
import com.commondataservice.commonService.models.OrderInfoModel;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class Common_ServiceImpl implements Common_Service{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private AddressInfoRepository addressInfoRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VegCategoryRepository vegCategoryRepository;

    private HashMap<String, List<String>> getConditionMaps(String queryParams){

        queryParams = queryParams.concat("::");
        String[] seperatedConditions = queryParams.split("::");

        if(seperatedConditions.length>0){
            HashMap<String, List<String>> conditionMap = new HashMap<>();
            for(String condition: seperatedConditions){
                String conditionParam = condition.split("=")[0];
                String[] conditions = condition.split("=")[1].split(",");
                if(conditions.length>0) {
                    List<String> temp = new ArrayList<>(List.of(conditions));
                    conditionMap.put(conditionParam, temp);
                }

            }
            return conditionMap;

        }
        return null;
    }


    @Transactional
    @Override
    public AddressInfoDTO saveAddressInfoByUsername(AddressInfoModel addressDetails, String username){
        try {
            Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
            if (userInfo.isPresent()) {
                AddressInfo addressInfo = AddressInfo.builder()
                        .userInfo(userInfo.get())
                        .Address(addressDetails.getAddress())
                        .zipcode(addressDetails.getZipcode())
                        .phone(addressDetails.getPhone())
                        .build();
                addressInfoRepository.save(addressInfo);

                return new AddressInfoDTO(List.of(addressInfo));
            }
            return null;


        }catch (Exception e){
            return null;
        }
    }

    @Transactional
    @Override
    public OrderInfoDTO makeOrder(OrderInfoModel orderInfoModel, String username){
        try{
            System.out.println(orderInfoModel.getRecipeIds());
            Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
            Optional<AddressInfo> addressInfo = addressInfoRepository.findById(orderInfoModel.getAddress_id());
            Optional<List<Recipe>> recipes = recipeRepository.findAllByIds(orderInfoModel.getRecipeIds());

            if(userInfo.isPresent() && addressInfo.isPresent() && recipes.isPresent()){
                OrderInfo orderInfo = OrderInfo.builder()
                        .addressInfo(addressInfo.get())
                        .userInfo(userInfo.get())
                        .recipeList(recipes.get()).build();

                orderInfoRepository.save(orderInfo);
                return new OrderInfoDTO(List.of(orderInfo));
            }
            return null;

        }
        catch (Exception e){
            return null;
        }
    }

    @Transactional
    @Override
    public OrderInfoDTO findOrderByUsername(String username) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
        if(userInfo.isPresent()) {
            Optional<List<OrderInfo>> result = orderInfoRepository.findAllByUserInfo(userInfo.get());

            return result.map(OrderInfoDTO::new).orElse(null);
        }
        return null;
    }

    @Transactional
    @Override
    public AddressInfoDTO findAddressInfoByUsername(String username) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
        if(userInfo.isPresent()) {
            Optional<List<AddressInfo>> result = addressInfoRepository.findAllByUserInfo(userInfo.get());

            return result.map(AddressInfoDTO::new).orElse(null);
        }
        return null;
    }


    @Transactional
    @Override
    public UserInfo findByUserName(String username) {
        Optional<UserInfo> result = userInfoRepository.findByUsername(username);

        UserInfo userInfo = null;

        if(result.isPresent())
            userInfo = result.get();
        return  userInfo;


    }

    @Transactional
    @Override
    public UserInfo findUserByID(Integer user_id) {
        Optional<UserInfo> result = userInfoRepository.findUserInfoById(user_id);

        UserInfo userInfo = null;

        if(result.isPresent())
            userInfo = result.get();
        return  userInfo;


    }

    @Transactional
    @Override
    public RecipesDTO findAllRecipe() {
        return new RecipesDTO(recipeRepository.findAll());
    }

    @Transactional
    @Override
    public RecipesDTO findRecipeByQuery(String queryParams){

        HashMap<String, List<String>> conditionMap = getConditionMaps(queryParams);

        if(conditionMap == null){
            return findAllRecipe();
        }
        List<Integer> restaurantIds = restaurantRepository.findAllIds();
        List<Integer> vegCategoryIds = vegCategoryRepository.findAllIds();

        for(Map.Entry<String, List<String>> entry: conditionMap.entrySet()){
            String key = entry.getKey();
            Set<String> set_ids = new HashSet<String>(entry.getValue());
            switch (key){

                case "restaurant":
                    restaurantIds = restaurantRepository.findRestaurantIdByName(set_ids);
                    break;
                case "veg-category":
                    vegCategoryIds = vegCategoryRepository.findVegCategoryIdByType(set_ids);

            }

        }

        Optional<List<Recipe>> recipes = recipeRepository.findAllByRestaurantIdAndVegCategoryId(restaurantIds, vegCategoryIds );

        return recipes.map(RecipesDTO::new).orElse(null);

    }

    @Override
    public HashMap<Integer, Recipe> findRecipeByIds(String queryParams){
        String[] productIds = queryParams.split(",");
        HashMap<Integer, Recipe> resultMap = null;

        if(productIds.length>0){
            Optional<List<Recipe>> result = recipeRepository.findAllByIds(List.of(productIds));
            if(result.isPresent()){
                resultMap = new HashMap<>();
                for(Recipe recipe: result.get())
                    resultMap.put(recipe.getId(), recipe);
            }

        }
        return resultMap;
    }

    @Transactional
    @Override
    @Cacheable(key = "#apiName", value = "mainScreenResponse")
    public MainScreenResponse getHomeScreenData(String apiName){

        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<VegCategory> vegCategories = vegCategoryRepository.findAll();

        return new MainScreenResponse(restaurants, vegCategories);

    }
}
