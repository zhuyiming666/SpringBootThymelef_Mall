package com.zym.controller;

import com.zym.pojo.Users;
import com.zym.pojo.UsersMessage;
import com.zym.service.UsersService;
import com.zym.utils.RandomValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class RegisterAndLoginController{

    @Resource
    private UsersService usersService;

    @PostMapping (value = "/register")
    public String register(String email, String password, String codestr, HttpServletRequest request){
        String code = (String)request.getSession().getAttribute("randomcode_key");
        if(!code.equals(codestr)){
            request.setAttribute("code_error","验证码错误!");
            return "reg";
        }
        Users users=new Users();
        users.setEmail(email);
        users.setPassword(password);
        users.setIsDelete(1);

        usersService.reg(users);
        usersService.insertUsersMessageId(users.getId());

        return "redirect:/login.html";
    }

    @RequestMapping("/ifEmail")
    @ResponseBody
    public String ifEmail(String email,HttpServletRequest request){
        Users u1=usersService.findUsersByEmail(email);
        if(u1!=null){
            request.setAttribute("msg_reg","邮箱已经被占用!");
            return "F";
        }
        return "T";
    }

    @GetMapping(value = "/templatesTest")
    public String Tt(String username){
        System.out.println("========tttt========="+username);
        return "address";
    }

    @RequestMapping({"/reg","/reg.html"})
    public String reg(){
        return "redirect:/reg.html";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String email,String password,HttpServletRequest request){
        Users users=usersService.loginUsers(email,password);
        if(users==null){
            return "F";
        }
        Integer status=users.getIsDelete();
        switch (status){
            case -1:
                return "F";
            case 0:
                return "0F";
            case 1:
                request.getSession().setAttribute("users",users);
                Integer id=users.getId();
                UsersMessage usersMessage = usersService.getUsersMessageById(id);
                request.getSession().setAttribute("usersMessage",usersMessage);
                if(usersMessage.getAvatar().equals("yimingLogo.jpg")){
                    request.getSession().setAttribute("defaultAvatar","默认头像");
                }
                System.out.println(usersMessage);
                return "T";
        }
        return "F";
    }

    @RequestMapping("/loginOut")
    @ResponseBody
    public String loginOut(HttpSession session){
        session.invalidate();
        System.out.println("===========");
        return "T";
    }

    @RequestMapping("/mygxin")
    public String myHtml(){
        return "/mygxin";
    }


    @RequestMapping(value = "/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");

        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
