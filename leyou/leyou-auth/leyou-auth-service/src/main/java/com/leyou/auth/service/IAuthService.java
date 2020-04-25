package com.leyou.auth.service;

public interface IAuthService {
    /**
     * 授权
     * @param username
     * @param password
     * @return
     */
    String accredit(String username, String password);
}
