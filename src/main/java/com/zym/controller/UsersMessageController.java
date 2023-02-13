package com.zym.controller;

import com.zym.pojo.Address;
import com.zym.pojo.District;
import com.zym.pojo.Users;
import com.zym.pojo.UsersMessage;
import com.zym.service.UsersMessageService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/usersMsg")
public class UsersMessageController {

    @Resource
    private UsersMessageService usersMessageService;

    @RequestMapping("/mygrxx.html")
    public String mygrxx(HttpSession session) {
        System.out.println("=========================");
        Users users=(Users) session.getAttribute("users");
        UsersMessage usersMessage1=usersMessageService.getUsersMessageById(users.getId());
        session.setAttribute("usersMessage",usersMessage1);
        return "/mygrxx";
    }



    @RequestMapping("/myprod.html")
    public String myprod() {
        return "/myprod";
    }

    @RequestMapping("/address.html")
    public String address(HttpSession session) {
        return "/address";
    }

    //显示收获地址
    @RequestMapping("/addressShow")
    @ResponseBody
    public List<Address> addressShow(HttpSession session) {
        Users users=(Users) session.getAttribute("users");
        Integer uid=users.getId();
        List<Address> list=usersMessageService.getAddressAll(uid);
        return list;
    }

    @RequestMapping("/mygxin.html")
    public String mygrxx() {
        return "/mygxin";
    }

    @RequestMapping("/remima.html")
    public String remima() {
        return "/remima";
    }

    //编辑用户信息
    @RequestMapping(value = "/editMsg",method = {RequestMethod.POST})
    @ResponseBody
    public HashMap<String, Object> editMsg(@RequestBody UsersMessage usersMessage,HttpSession session) {
        System.out.println(usersMessage);
        HashMap<String, Object> map = new HashMap<>();
        Integer x = usersMessageService.editMessage(usersMessage);
        map.put("editStatus", x);
        if(x>0){
            Users users=(Users) session.getAttribute("users");
            UsersMessage usersMessage1=usersMessageService.getUsersMessageById(users.getId());
            session.setAttribute("usersMessage",usersMessage1);
        }
        return map;
    }

    //设置文件上传的最大值
    public static final int AVATAR_MAX_SIZE=1024*1024*2;

    //限制文件上传的类型
    public static final List<String> AVATAR_TYPE=new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/bmp");
    }


    //修头像
    @RequestMapping("/changeAvatar")
    @ResponseBody
    public HashMap<String,Object> change_avatar(HttpSession session, MultipartFile file,HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<>();
        if(file.isEmpty()){
            map.put("status","F");
            map.put("msg","文件为空!");
            return map;
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            map.put("status","F");
            map.put("msg","文件大小超出指定范围!");
            return map;
        }
        String contextType=file.getContentType();  //获取文件后缀类型
        if(!AVATAR_TYPE.contains(contextType)){
            map.put("status","F");
            map.put("msg","文件类型不支持!");
            return map;
        }
        //获取上传图片存放的目录
        //String filepath=// 获取static目录 获取static就可以完成上传功能了
        //String realPath = ResourceUtils.getURL("classpath:").getPath() + "static";+"files\\";//获取项目路径到webapp
        /*String filepath= null;
        try {
            filepath = ResourceUtils.getURL("classpath:").getPath()+"static/shooping_Imgs/upload";
            filepath = java.net.URLDecoder.decode(filepath, "utf-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
       // String filepath= ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/shooping_Imgs/upload";
        String filepath="D:\\学习文件\\Template Project\\SpringBootThymelef_Mall\\src\\main\\resources\\static\\shooping_Imgs\\upload";
        try {
            filepath = java.net.URLDecoder.decode(filepath, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("filepath="+filepath);

        //判断目录是否存在，如果不存在则创建
        File dir=new File(filepath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        //获取到这个文件的名称,UUID工具来将生成一个新的字符串做为文件名
        String originalFilename=file.getOriginalFilename();


        int index=originalFilename.lastIndexOf(".");
        String suffix=originalFilename.substring(index);  //获取文件后缀
        String filename= UUID.randomUUID().toString().toUpperCase()+suffix;

        File dest=new File(dir,filename);  //创建一个空文件

        //将前端传递的file写入到空文件当中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读写异常");
        }

        Users users=(Users) session.getAttribute("users");
        Integer id=users.getId();

        /*String avatar="../upload/"+filename;*/
        String avatar="upload/"+filename;
        Integer x=usersMessageService.updateAvatarById(id,avatar);
        if(x>0){
            map.put("status","T");
            map.put("msg","修改头像成功!");
        }

        return map;
    }

    //修改密码
    @RequestMapping("/updatePassword")
    @ResponseBody
    public HashMap<String,Object> updatePassword(String password,String newPassword,String checkCode,HttpServletRequest request){
        HashMap<String,Object> map=new HashMap<>();
        String code = (String)request.getSession().getAttribute("randomcode_key");
        if(!code.equals(checkCode)){
            map.put("status","F");
            map.put("msg","验证码错误!");
            return map;
        }

        Users users=(Users)request.getSession().getAttribute("users");
        Integer id=users.getId();

        Integer x=usersMessageService.updatePassword(password,newPassword,id);
        switch (x){
            case -1:
                map.put("status","F");
                map.put("msg","原密码错误!");
                return map;
            case 1:
                map.put("status","T");
                map.put("msg","修改密码成功!");
                return map;
        }
        return map;
    }

    //添加收获地址
    @RequestMapping("/addNewAddress")
    @ResponseBody
    public HashMap<String,Object> addNewAddress(Address address,HttpSession session){
        Users users=(Users) session.getAttribute("users");
        Integer uid=users.getId();
        String username=users.getEmail();
        Integer x=usersMessageService.addNewAddress(uid,username,address);
        HashMap<String,Object> map=new HashMap<>();
        switch (x){
            case -1:
               map.put("status","F");
               map.put("msg","收获地址设置已到达上限");
               return map;
            case -2:
                map.put("status","F");
                map.put("msg","收获地址添加失败!");
                return map;
            case 1:
                map.put("status","T");
                map.put("msg","收获地址添加成功!");
                return map;
        }
        return null;
    }

    //获取省市区
    @RequestMapping("/district")
    @ResponseBody
    public List<District> getDistricts(String parent){
        List<District> list=usersMessageService.findByParent(parent);
        return list;
    }

    //restful风格
    //设置默认收获地址
    @RequestMapping("{aid}/setDefaultAddress")
    @ResponseBody
    public HashMap<String,Object> setDefaultAddress(@PathVariable("aid") Integer aid,HttpSession session){
        HashMap<String,Object> map=new HashMap<>();
        Users users=(Users) session.getAttribute("users");
        Integer uid=users.getId();
        Integer x=usersMessageService.setDefaultAddress(uid,aid);
        System.out.println("x====="+x);
        if (x<1){
            map.put("status","F");
            map.put("msg","设置失败!");
        }else {
            map.put("status","T");
            map.put("msg","设置成功!");
        }
        return map;
    }

    //删除某个收获地址
    @RequestMapping("/deleteAddress/{aid}")
    @ResponseBody
    public HashMap<String,Object> deleteAddress(@PathVariable("aid") Integer aid){
        HashMap<String,Object> map=new HashMap<>();
        Integer x=usersMessageService.deleteAddress(aid);
        if (x==1){
            map.put("status","T");
            map.put("msg","删除成功!");
            return map;
        }else {
            map.put("status","F");
            map.put("msg","删除失败!");
            return map;
        }
    }


}
