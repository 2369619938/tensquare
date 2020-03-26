package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FrientService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FrientController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private FrientService frientService;

    @Autowired
    private UserClient userClient;
    /***
     * 添加好友或者非好友
     * @return
     */
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid,@PathVariable String type){
        //验证是否登陆 并且 拿到登陆用户的id
        Claims claims_user = (Claims) httpServletRequest.getAttribute("claims_user");
        if(claims_user==null){
            return  new Result(false, StatusCode.LOGINERROR,"权限不足");
        }
        String id = claims_user.getId();
        //判断是添加好友还是添加非好友
        if(type!=null ){
            if(type.equals("1")){
                //添加好友 得到登陆的用户id
             int flag= frientService.addFriend(id,friendid);
             if(flag==0){
                 return  new Result(false, StatusCode.ERROR,"不能重复添加好友");
             }else if(flag==1){
                 userClient.updaefanscountandfollowcount(id,friendid,1);
                 return  new Result(true, StatusCode.OK,"添加成功");
             }//添加非好友
            }else if(type.equals("2")){
               int flag=frientService.addNoFriend(id,friendid);
                if(flag==0){
                    return  new Result(false, StatusCode.ERROR,"不能重复添加非好友");
                }else if(flag==1){
                    return  new Result(true, StatusCode.OK,"添加成功");
                }
            }
            return  new Result(false, StatusCode.ERROR,"参数异常");
        }else {
            return  new Result(false, StatusCode.ERROR,"参数异常");
        }
    }

    @RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendid){
        //验证是否登陆 并且 拿到登陆用户的id
        Claims claims_user = (Claims) httpServletRequest.getAttribute("claims_user");
        if(claims_user==null){
            return  new Result(false, StatusCode.LOGINERROR,"权限不足");
        }
        String id = claims_user.getId();
        frientService.deleteFriend(id,friendid);
        userClient.updaefanscountandfollowcount(id,friendid,-1);
        return  new Result(true, StatusCode.OK,"删除成功");

    }
}
