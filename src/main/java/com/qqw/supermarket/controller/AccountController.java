package com.qqw.supermarket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.qqw.supermarket.model.Account;
import com.qqw.supermarket.model.AccountDTO;
import com.qqw.supermarket.service.AccountService;
import com.qqw.supermarket.service.PermissionService;
import com.qqw.supermarket.service.RoleService;
import com.qqw.supermarket.util.MD5Util;
import com.qqw.supermarket.util.ResultData;
import com.qqw.supermarket.util.UUIDUtil;
import com.qqw.supermarket.util.constan.StringConstans;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname AccountController
 * @Description TODO()
 * @Date 2020/2/10 19:17
 * @Author by Alex
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService service;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    /**
     * 登录验证
     * @param request
     * @param captcha
     * @param password
     * @param account
     * @param remember
     * @param response
     * @param session
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(HttpServletRequest request, String captcha, String password, String account,
                              boolean remember, HttpServletResponse response, HttpSession session,
                              RedirectAttributes redirectAttributes){
        session.setAttribute("account1",service.findByAccount(account));

        ModelAndView mv = new ModelAndView();

        //判断验证码
        if (!CaptchaUtil.ver(captcha, request)) {
            // 清除session中的验证码
            CaptchaUtil.clear(request);
            mv.setViewName("redirect:../login");
            redirectAttributes.addFlashAttribute("msg", "验证码输入错误");
            return mv;
        }

        // 清除session中的验证码
        CaptchaUtil.clear(request);

        //判断账号和密码不能为空
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            mv.setViewName("redirect:../login");
            redirectAttributes.addFlashAttribute("msg","用户名或密码不能为空");
            return mv;
        }else {
            ResultData resultData = service.login(account,password);

            //判断登录是否成功
            if (resultData.getCode() == 1){
                if (remember){

                    //将账号和密码存入cookie
                    Cookie cookie = new Cookie("uid" , account+ StringConstans.OR_STRING+password);
                    cookie.setMaxAge(60*60*24*7);
                    String path = StringConstans.EMPTY_STRING.equals(request.getServletContext().getContextPath()) ?
                            "/" : request.getServletContext().getContextPath();
                    cookie.setPath(path);
                    response.addCookie(cookie);
                }
                AccountDTO accountDTO = (AccountDTO) resultData.getData();
                if (accountDTO.getRoles().size() == 0 ){
                    redirectAttributes.addFlashAttribute("msg","该用户不具有角色可以登录");
                    mv.setViewName("redirect:../login");
                    return mv;
                }
                session.setAttribute("login_permission",permissionService.findAllByRoles(accountDTO.getRoles()));
                session.setAttribute("login_account", accountDTO);
                mv.setViewName("redirect:../index");
                return mv;
            }
            mv.setViewName("redirect:../login");
            redirectAttributes.addFlashAttribute("msg",resultData.getMessage());
            return mv;
        }

    }

    @GetMapping("/index")
    public String toIndex(){
        return "management/admin/systemAccounts/index";
    }

    /**
     * 列表查询
     * 模糊查询
     * @param offset
     * @param limit
     * @param sort
     * @param order
     * @param search
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(defaultValue = "0") int offset,
                                    @RequestParam(defaultValue = "5") int limit,
                                    String sort,String order,String search){
        Map<String, Object> params = new HashMap<>();
        params.put("offset",offset);
        params.put("limit",limit);
        params.put("sort",sort);
        params.put("order",order);
        params.put("search", search);
        Map<String , Object> map = new HashMap<>();
        int count = service.count();
        List<AccountDTO> list = service.findAll(params);
        map.put("total",count);
        map.put("rows",list);
        return map;
    }

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @param search
     * @return
     */
    @GetMapping("/pages")
    @ResponseBody
    public PageInfo<AccountDTO> listByPages(@RequestParam(defaultValue = "1") int pageNum,
                                    @RequestParam(defaultValue = "5") int pageSize,
                                    String sort,String order,String search){
        Map<String, Object> params = new HashMap<>();
        params.put("sort",sort);
        params.put("order",order);
        params.put("search",search);
        List<AccountDTO> list = service.findAllByPage(pageNum, pageSize, params);
        PageInfo<AccountDTO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 获取用户id，查询到用户跳转到编辑页面
     * @param id
     * @return
     */
    @GetMapping("/iframe/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("management/admin/systemAccounts/create");
        mv.addObject("account",service.getById(id));
        return mv;
    }

    /**
     * 修改用户信息
     * @param account
     * @return
     */
    @PutMapping("/iframe/edit")
    @ResponseBody
    public ResultData update(Account account){
        service.updateAccount(account);
        return new ResultData(1,"修改成功",null);
    }

    /**
     * 注册用户
     * @param account
     * @return
     */
    @PostMapping("/iframe/save")
    @ResponseBody
    public ResultData save(Account account){
        int rows = service.saveAccount(account);
        if (rows > 0){
            return new ResultData(1,"注册成功",null);
        }else {
            return new ResultData(0,"注册失败，请检查账号或密码是否符合规范",null);
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultData delete(String id){
        int rows = service.deleteAccount(id);
        if (rows > 0 ){
            return new ResultData(1,"删除成功",null);
        }else {
            return new ResultData(0,"删除失败",null);
        }
    }

    /**
     * 跳转到用户信息页面
     * @param id
     * @return
     */
    @GetMapping("/iframe/info/{id}")
    public ModelAndView info(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("management/admin/systemAccounts/info");
        mv.addObject("account",service.getById(id));
        return mv;
    }

    /**
     * 锁定用户
     * @param id
     * @return
     */
    @PostMapping("/lock")
    @ResponseBody
    public ResultData lock(String id){
        Account account = service.getById(id);
        if(account.getLocked() == false){
            account.setLocked(true);
            service.updateAccount(account);
            return new ResultData(1,"锁定成功",null);
        }else {
            account.setLocked(false);
            service.updateAccount(account);
            return new ResultData(1,"解锁成功",null);
        }
    }


    /**
     * 批量删除
     * @param accountList
     * @return
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    public ResultData batchDelete(String accountList){
        String[] arr = accountList.split(",");
        int rows =  service.batchdelete(arr);
        if (rows > 0) {
            return new ResultData(1,"删除成功",null);
        }else{
            return new ResultData(1,"删除失败",null);
        }
    }

    /**
     * 获取用户角色id
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/iframe/edit/role/{id}")
    public ModelAndView editRole(@PathVariable("id") String id) throws JsonProcessingException {
        ModelAndView mv = new ModelAndView("management/admin/systemAccounts/role");
        mv.addObject("account",service.getById(id));
        ObjectMapper mapper = new ObjectMapper();
        mv.addObject("roles",mapper.writeValueAsString(roleService.findAll()));
        return mv;
    }

    /**
     * 授权
     * @param id
     * @param ids
     * @return
     */
    @PostMapping("/iframe/role/edit")
    @ResponseBody
    public ResultData editRole(String id, Integer[] ids) {
        return service.editRole(id, ids);
    }


    /**
     * 修改密码页面
     * @return
     */
    @GetMapping("/iframe/password")
    public ModelAndView changepwd(){
        ModelAndView mv = new ModelAndView("management/admin/systemAccounts/changepwd");
        return mv;
    }

    /**
     * 修改密码
     * @param account
     * @return
     */
    @PostMapping("/iframe/password")
    @ResponseBody
    public ResultData password(Account account){
        String salt = service.getById(account.getId()).getSalt();
        account.setPassword(MD5Util.encode(account.getPassword(),salt));
       int rows = service.updateAccount(account);
       if (rows > 0) {
           return new ResultData(1,"修改成功",null);
       }
        return new ResultData(1,"修改失败",null);
    }


    @GetMapping("/updateHead")
    public ModelAndView updateHead(){
        ModelAndView mv = new ModelAndView("management/admin/systemAccounts/updateHead");
        return mv;
    }

    /**
     * 上传头像
     * @param uploadFile
     * @param id
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public ModelAndView testResponseJson(MultipartFile
            uploadFile, String id, HttpServletRequest request, Model model) throws Exception {
        //定义文件名
        String fileName = "";
        //1.获取原始文件名
        String uploadFileName = uploadFile.getOriginalFilename();
        //2.截取文件扩展名
        String extendName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1, uploadFileName.length());
        //3.把文件加上随机数，防止文件重复
        fileName = UUIDUtil.createUUID().toUpperCase() + "_" + uploadFileName;

        //2.获取文件路径
        String basePath = "E:\\temp\\images";
        //3.解决同一文件夹中文件过多问题
        //String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //4.判断路径是否存在
        File file = new File(basePath );
        if (!file.exists()) {
            file.mkdirs();
        }
        //5.使用 MulitpartFile 接口中方法，把上传的文件写到指定位置
        uploadFile.transferTo(new File(file, fileName));

        String sqlPath = "/images/" + fileName;
        Account account = service.getById(id);
        account.setHeadPic(sqlPath);
        service.updateAccount(account);
        model.addAttribute("account1",account);
        return new ModelAndView("management/admin/home/index");
    }

}
