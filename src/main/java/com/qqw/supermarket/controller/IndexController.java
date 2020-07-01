package com.qqw.supermarket.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Classname IndexController
 * @Description TODO()
 * @Date 2020/2/10 17:35
 * @Author by Alex
 */
@Controller
public class IndexController {

    /**
     * 登录，记住密码登录
     * @param uid
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(@CookieValue(name = "uid",required = false) String uid){
        ModelAndView mv = new ModelAndView("management/login");
        //判断uid是否为空
        if(!StringUtils.isEmpty(uid)) {
            String[] values = uid.split("\\|");
            mv.addObject("account",values[0]);
            mv.addObject("password",values[1]);
        }
        return mv;
    }

    /**
     * 验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(4, request, response);
    }

    /**
     * 主页
     * @return
     */
    @GetMapping("/index")
    public String toIndex(){
        return "management/admin/home/index";
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
