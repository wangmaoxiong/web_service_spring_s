package com.lct.www.controller;

import com.lct.www.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Administrator on 2019/2/19 0019.
 * 用户控制器
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 根据用户id获取用户信息
     * 浏览器访问地址为：http://localhost/web_service_spring_s/getUserById.action
     *
     * @return 直接将内容打印到页面
     */
    @RequestMapping("getUserById.action")
    public void getUserById(HttpServletResponse response) {
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(userService.getUserById(new Random().nextInt(1000)).toString());
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
