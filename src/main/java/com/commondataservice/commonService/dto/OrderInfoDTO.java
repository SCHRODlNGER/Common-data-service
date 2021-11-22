package com.commondataservice.commonService.dto;


import com.commondataservice.commonService.entitiy.OrderInfo;
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
public class OrderInfoDTO implements Serializable {

    private List<OrderInfo> orderInfoList;
    private Integer totalCount;
    private Integer price = 0;

    public OrderInfoDTO(List<OrderInfo> orderInfoList){
        this.orderInfoList = orderInfoList;
        this.totalCount = orderInfoList.size();
        this.price = getPrice(orderInfoList);
    }

    Integer getPrice(List<OrderInfo> orderInfos){
        System.out.println(orderInfos);
        return 0;
    } 


}