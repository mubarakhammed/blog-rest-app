package com.mubarak.blogrestapi.payload;

import com.mubarak.blogrestapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Boolean status;
    private String accessToken;
    private String tokenType = "Bearer";
    private UserDetails userDetails;
}
