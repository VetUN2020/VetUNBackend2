package com.vetun.apirest.pojo;

public class TwoFactorAuthenticationPOJO {
    public String username;
    public String secret;

    public TwoFactorAuthenticationPOJO(String secret) {
        this.secret = secret;
    }

    public TwoFactorAuthenticationPOJO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
