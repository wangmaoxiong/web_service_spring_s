package com.lct.www.service.impl;

import com.lct.www.domain.User;
import com.lct.www.service.UserService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2019/2/19 0019.
 *
 * @WebService ：标识此类为 webService 服务类, 注意 endpointInterface 属性不用再写，否则报错
 */
@WebService
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getGlobal();//日志记录器

    @Override
    public User getUserById(Integer uId) {
        logger.log(Level.INFO, "根据用户id查询用户信息，uId=" + uId);
        //用于演示，则不从数据查询，这里直接造一个假数据返回
        User user = new User();
        user.setuId(uId);
        user.setuName(UUID.randomUUID().toString());
        user.setBithday(new Date());
        user.setPrice(new Random().nextInt(10000000) * 1.0F);

        logger.log(Level.INFO, "查出用户信息为：" + user);
        return user;
    }
}
