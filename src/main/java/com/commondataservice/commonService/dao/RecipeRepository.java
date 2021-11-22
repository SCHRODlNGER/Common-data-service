package com.commondataservice.commonService.dao;

import com.commondataservice.commonService.entitiy.Recipe;
import com.commondataservice.commonService.entitiy.Restaurant;
import com.commondataservice.commonService.entitiy.VegCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    Optional<List<Recipe> > findAllByRestaurant(Restaurant restaurant);

    Optional<List<Recipe> > findAllByVegCategory(VegCategory vegCategory);

    Optional<Recipe> findRecipeByName(String name);

    Optional<List<Recipe>> findAllByRestaurantAndVegCategory(Restaurant restaurant, VegCategory vegCategory);

    @Query(value = "select * from recipe WHERE recipe.restaurant_id IN ?1 AND recipe.veg_id IN ?2", nativeQuery = true)
    Optional<List<Recipe>> findAllByRestaurantIdAndVegCategoryId(List<Integer> restaurantId, List<Integer> vegCategoryId);

    @Query(value = "select * from recipe where recipe.id in ?1", nativeQuery = true)
    Optional<List<Recipe>> findAllByIds(List<String> ids);

}
