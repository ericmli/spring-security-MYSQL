package io.isiflix.authdemo.security;

public class IsiToken {
    private String token;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public IsiToken() {

    }

    public IsiToken(String token) {
        this.token = token;
    }
}
