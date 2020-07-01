package com.qqw.supermarket.controller;

import com.qqw.supermarket.util.ResultData;
import com.qqw.supermarket.util.UUIDUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Classname ArticleController
 * @Description TODO()
 * @Date 2020/2/15 9:32
 * @Author by Alex
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    public ArticleController(){}

    /**
     * 保存信息
     * @param request
     * @param message
     * @return
     */
    @PostMapping({"/message"})
    public ResultData message(HttpServletRequest request, String message) {
        System.out.println(message);
        FileOutputStream fos = null;
        String local = request.getServletContext().getRealPath("/static/upload");

        try {
            fos = new FileOutputStream(local + UUIDUtil.createUUID() + ".txt");
            byte[] bytes = message.getBytes();
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return new ResultData(1, "成功");
    }

    /**
     * 法律政策
     * @return
     */
    @RequestMapping("/law")
    public ModelAndView law(){
        ModelAndView mv = new ModelAndView("/management/admin/articles/1/FLZC/index");
        return mv;
    }

    /**
     * 跳转 购物指南
     * @return
     */
    @RequestMapping("/guide")
    public ModelAndView guide(){
        ModelAndView mv = new ModelAndView("/management/admin/articles/1/GWZN/index");
        return mv;
    }

    /**
     * 跳转到实名认证
     * @return
     */
    @RequestMapping("/certification")
    public ModelAndView certification(){
        ModelAndView mv = new ModelAndView("/management/admin/articles/1/SMRZ/index");
        return mv;
    }

    /**
     * 跳转到运输时效
     * @return
     */
    @RequestMapping("/transportation")
    public ModelAndView transportation(){
        ModelAndView mv = new ModelAndView("/management/admin/articles/1/YSSX/index");
        return mv;
    }

    /**
     * 跳转到签收说明
     * @return
     */
    @RequestMapping("/sign")
    public ModelAndView sign(){
        ModelAndView mv = new ModelAndView("/management/admin/articles/1/QSSM/index");
        return mv;
    }

    /**
     * 真品保证
     * @return
     */
    @RequestMapping("/real")
    public ModelAndView real(){
        ModelAndView mv = new ModelAndView("/management/admin/articles/1/ZPBZ/index");
        return mv;
    }

    /**
     * 费用说明
     * @return
     */
    @RequestMapping("/cost")
    public ModelAndView cost(){
        ModelAndView mv = new ModelAndView("/management/admin/articles/1/FYSM/index");
        return mv;
    }

}
