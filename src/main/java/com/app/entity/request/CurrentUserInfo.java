package com.app.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentUserInfo {
    private String userId;
    private String name;
    private int userType;
}
