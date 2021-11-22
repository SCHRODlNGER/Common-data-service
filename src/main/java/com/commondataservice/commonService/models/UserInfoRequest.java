package com.commondataservice.commonService.models;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class UserInfoRequest {

    private String firstname;
    private String lastname;
    private String username;


}
