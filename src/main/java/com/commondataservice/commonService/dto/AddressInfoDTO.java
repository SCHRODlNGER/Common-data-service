package com.commondataservice.commonService.dto;

import com.commondataservice.commonService.entitiy.AddressInfo;
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
public class AddressInfoDTO implements Serializable {

    private List<AddressInfo> addressInfoList;
    private Integer totalCount;

    public AddressInfoDTO(List<AddressInfo> addressInfoList){
        this.addressInfoList = addressInfoList;
        this.totalCount = addressInfoList.size();
    }


}
