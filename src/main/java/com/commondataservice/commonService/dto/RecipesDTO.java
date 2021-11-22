package com.commondataservice.commonService.dto;

import com.commondataservice.commonService.entitiy.Recipe;
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
public class RecipesDTO implements Serializable {
    private List<Recipe> recipeList;
    private Integer totalCount;

    public RecipesDTO(List<Recipe> recipes){
        recipeList = recipes;
        totalCount = recipes.size();
    }
}

