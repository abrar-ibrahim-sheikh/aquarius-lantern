package com.scribblesphere.aquariuslantern.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {

    public String username;
    public String password;

}
