package com.scribblesphere.aquariuslantern.vo;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Builder
public class TokenData {

    private String accessToken;
    private Date expiration;

}
