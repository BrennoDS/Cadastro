package com.BrennoDS.cadastro.security;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
    
}
