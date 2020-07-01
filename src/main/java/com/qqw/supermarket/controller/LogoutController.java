package com.qqw.supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Classname LogoutController
 * @Description TODO()
 * @Date 2020/2/21 15:24
 * @Author by Alex
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {

    /**
     * 退出
     * @param session
     * @return
     */
    @RequestMapping
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        ModelAndView mv = new ModelAndView("management/login");
        return mv;
    }
}
