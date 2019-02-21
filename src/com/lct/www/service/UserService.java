package com.lct.www.service;

import com.lct.www.domain.User;

import javax.jws.WebService;

/**
 * Created by Administrator on 2019/2/19 0019.
 * 用户接口
 *
 * @WebService ：标识此接口为 webService 接口
 */
@WebService
public interface UserService {

    /**
     * 根据用户id查询用户信息
     *
     * @param uId
     * @return
     */
    public User getUserById(Integer uId);
}
