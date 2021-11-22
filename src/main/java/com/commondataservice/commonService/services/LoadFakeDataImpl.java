package com.commondataservice.commonService.services;

import com.commondataservice.commonService.dao.*;
import com.commondataservice.commonService.entitiy.Recipe;
import com.commondataservice.commonService.entitiy.Restaurant;
import com.commondataservice.commonService.entitiy.VegCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class LoadFakeDataImpl implements LoadFakeData{

    @Autowired
    private AddressInfoRepository addressInfoRepository;
    @Autowired
    private OrderInfoRepository orderInfoRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private VegCategoryRepository vegCategoryRepository;


    private final String DATADIRECTORY = "seed_data";
    private final String RESTAURANTDATA = "restaurant_data.txt";
    private final String VEGCATDATA = "veg_category_data.txt";
    private final String RECIPEDATA = "recipe_data.txt";

    private boolean loadVegCategoryInfo(String path){

        System.out.println("Loading VegCategory Data");

        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

            if(inputStream == null){
                System.out.println("Veg Category File is empty");
                return false;
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            for(String line; ((line = reader.readLine()))!=null; ){
                System.out.println("Veg Category Line: " + line);

                String[] result = line.split("\\|");

                String type = result[1];

                Optional<VegCategory> vegCategory = vegCategoryRepository.findByType(type);
                if(!vegCategory.isPresent()){
                    VegCategory newVegCategory = new VegCategory(type);
                    vegCategoryRepository.save(newVegCategory);
                }

            }
            reader.close();

            return true;

        }
        catch (FileNotFoundException e){
            System.out.println("An error Occurred");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean loadRestaurantInfo(String path){

        System.out.println("Loading Restaurant Data");

        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

            if(inputStream == null){
                System.out.println("Restaurant File is empty");
                return false;
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            for(String line; ((line = reader.readLine()))!=null; ){
                System.out.println("Restaurant Line: " + line);

                String[] result = line.split("\\|");

                String localFilepath = result[0];
                String imageUrl = result[1];
                String name = result[2];

                Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByName(name);
                if(restaurant.isEmpty()){
                    Restaurant restaurant1 =  Restaurant.builder()
                            .name(name)
                            .imageUrl(imageUrl)
                            .localPath(localFilepath).build();
                    restaurantRepository.save(restaurant1);
                }

            }
            reader.close();

            return true;

        }
        catch (FileNotFoundException e){
            System.out.println("An error Occurred");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    private boolean loadRecipeData(String path){

        System.out.println("Loading Recipe Data");

        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

            if(inputStream == null){
                System.out.println("Recipe File is empty");
                return false;
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            for(String line; ((line = reader.readLine()))!=null; ){
                System.out.println("Recipe Line: " + line);

                String[] result = line.split("\\|");

                String id = result[0];
                String vegCat = result[1];
                String restaurantName = result[2];
                String recipeName = result[3];
                String price = result[4];
                String localFilePath = result[5];
                String imageUrl = result[6];

                Optional<VegCategory> vegCategory = vegCategoryRepository.findByType(vegCat);

                Optional<Restaurant> restaurant = restaurantRepository.getRestaurantByName(restaurantName);

                if(vegCategory.isEmpty() || restaurant.isEmpty()){
                    System.out.println("Veg Category or Restaurant Name not found");
                }

                Optional<Recipe> recipe = recipeRepository.findRecipeByName(recipeName);
                if(recipe.isEmpty()){
                    Recipe recipe1 =  Recipe.builder()
                            .name(recipeName)
                            .price(Integer.parseInt(price))
                            .restaurant(restaurant.get())
                            .vegCategory(vegCategory.get())
                            .imageUrl(imageUrl)
                            .localPath(localFilePath).build();
                    recipeRepository.save(recipe1);
                }

            }
            reader.close();

            return true;

        }
        catch (FileNotFoundException e){
            System.out.println("An error Occurred");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public boolean loadTestData() {
        if(!loadVegCategoryInfo(String.format("%s/%s", DATADIRECTORY, VEGCATDATA))){
            System.out.println("Error loading Veg Cat Data");
            return false;
        }
        if(!loadRestaurantInfo(String.format("%s/%s", DATADIRECTORY, RESTAURANTDATA))){
            System.out.println("Error loading Restaurant Data");
            return false;
        }
        if(!loadRecipeData(String.format("%s/%s", DATADIRECTORY, RECIPEDATA))){
            System.out.println("Error loading Recipe Data");
            return false;
        }
        return true;
    }
}
