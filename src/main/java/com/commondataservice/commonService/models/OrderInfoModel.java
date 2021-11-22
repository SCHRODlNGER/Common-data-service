package com.commondataservice.commonService.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderInfoModel {
    private int address_id;
    private List<String> recipeIds;
}
