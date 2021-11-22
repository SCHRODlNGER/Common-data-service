package com.commondataservice.commonService.controllers;


import com.commondataservice.commonService.dto.AddressInfoDTO;
import com.commondataservice.commonService.dto.OrderInfoDTO;
import com.commondataservice.commonService.dto.RecipesDTO;
import com.commondataservice.commonService.entitiy.AddressInfo;
import com.commondataservice.commonService.entitiy.Recipe;
import com.commondataservice.commonService.entitiy.UserInfo;
import com.commondataservice.commonService.models.AddressInfoModel;
import com.commondataservice.commonService.models.MainScreenResponse;
import com.commondataservice.commonService.models.OrderInfoModel;
import com.commondataservice.commonService.services.Common_Service;
import com.commondataservice.commonService.services.LoadFakeData;
import com.commondataservice.commonService.utils.JwtUtil;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
public class CommonDataController {

    @Autowired
    Environment environment;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    Common_Service commonService;

    @Autowired
    LoadFakeData loadFakeData;

    public void seedDB(){
        if(Objects.equals(environment.getProperty("ACTIVE_PROFILE"), "dev")){
            loadFakeData.loadTestData();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("success");
    }

    @GetMapping(value = "/recipes", params = "q")
    public ResponseEntity<?> getRecipesByCategories(@RequestParam("q") String queryParams){
        System.out.println(queryParams);
        RecipesDTO recipesDTO = commonService.findRecipeByQuery(queryParams);
        if(recipesDTO == null){
            return ResponseEntity.badRequest().body("Query has not followed the required format");

        }
        return  ResponseEntity.ok(recipesDTO);
    }

    @GetMapping(value = "/recipes", params = "product_id")
    public ResponseEntity<?> getRecipesById(@RequestParam("product_id") String queryParams){
        HashMap<Integer, Recipe> resultMap = commonService.findRecipeByIds(queryParams);
        if(resultMap == null)
            return ResponseEntity.badRequest().body("Query has not followed the required format");
        return ResponseEntity.ok(resultMap);
    }

    @GetMapping(value = "/recipes")
    public ResponseEntity<?> getAllRecipes(){
        RecipesDTO recipesDTO = commonService.findAllRecipe();
        if(recipesDTO == null){
            return ResponseEntity.badRequest().body("Query has not followed the required format");
        }
        return  ResponseEntity.ok(recipesDTO);
    }


    @GetMapping(value = "/home")
    public ResponseEntity<?> getHomeScreenData(){
        MainScreenResponse mainScreenResponse = commonService.getHomeScreenData("HomeAPI");
        if(mainScreenResponse == null)
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(mainScreenResponse);
    }

    @GetMapping(value = "/orders")
    public ResponseEntity<?> getOrderDetailsByUserId(@RequestHeader("Authorization") String headerData){

        String jwt_token = headerData.split(" ")[1];

        String username = jwtUtil.getUserName(jwt_token);

        OrderInfoDTO orderInfoDTO = commonService.findOrderByUsername(username);
        if(orderInfoDTO==null)
            return ResponseEntity.badRequest().body("Invalid User ID");
        return ResponseEntity.ok(orderInfoDTO);
    }


    @GetMapping(value = "/address")
    public ResponseEntity<?> getAddressDetailsByUserId(@RequestHeader("Authorization") String headerData){
        String jwt_token = headerData.split(" ")[1];

        String username = jwtUtil.getUserName(jwt_token);

        AddressInfoDTO addressInfo = commonService.findAddressInfoByUsername(username);
        if (addressInfo == null)
                return ResponseEntity.badRequest().body("Invalid User ID");
        return ResponseEntity.ok(addressInfo);
    }

    @PostMapping(value = "/address")
    public ResponseEntity<?> postAddressDetails(@RequestBody AddressInfoModel addressDetails, @RequestHeader("Authorization") String headerData){
        String jwt_token = headerData.split(" ")[1];

        String username = jwtUtil.getUserName(jwt_token);

        AddressInfoDTO addressInfoDTO = commonService.saveAddressInfoByUsername(addressDetails, username);
        if(addressInfoDTO !=null)
            return ResponseEntity.ok(addressInfoDTO);
        return ResponseEntity.badRequest().body("Address Not Saved");


    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> getUserInfoByToken(@RequestHeader("Authorization") String headerData){

        System.out.println("inside user");

        String jwt_token = headerData.split(" ")[1];

        String username = jwtUtil.getUserName(jwt_token);

        UserInfo userInfo = commonService.findByUserName(username);
        if (userInfo == null)
            return ResponseEntity.badRequest().body("Invalid User ID");
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping(value = "/order")
    public ResponseEntity<?> makeOrderUser(@RequestBody OrderInfoModel orderInfoModel,  @RequestHeader("Authorization") String headerData){
        String jwt_token = headerData.split(" ")[1];

        String username = jwtUtil.getUserName(jwt_token);

        OrderInfoDTO orderInfoDTO = commonService.makeOrder(orderInfoModel, username);

        if(orderInfoDTO == null)
            return ResponseEntity.badRequest().body("could not place order");
        return ResponseEntity.ok(orderInfoDTO);
    }


}
