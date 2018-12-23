package com.imooc.user.controller;

import com.imooc.user.constant.CookieConstant;
import com.imooc.user.constant.RedisConstant;
import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.enums.ResultEnum;
import com.imooc.user.enums.RoleEnum;
import com.imooc.user.service.UserService;
import com.imooc.user.util.CookieUtil;
import com.imooc.user.util.ResultVoUtil;
import com.imooc.user.vo.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVo buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response){
        //1. openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2. 判断角色
        if (!RoleEnum.BUYER.getCode().equals(userInfo.getRole())) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3. cookie里设置openid=abc
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ResultVoUtil.success();
    }

    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVo seller(@RequestParam("openid") String openid,
                          HttpServletRequest request,
                          HttpServletResponse response){
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            String key = String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue());
            String token = stringRedisTemplate.opsForValue().get(key);
            if (StringUtils.isNotEmpty(token)) {
                return ResultVoUtil.success();
            }
        }
        //1. openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2. 判断角色
        if (!RoleEnum.SELLER.getCode().equals(userInfo.getRole())) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3. redis设置key=UUID，value=xyz
        String token = UUID.randomUUID().toString();
        String key = String.format(RedisConstant.TOKEN_TEMPLATE, token);
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(key, openid, expire, TimeUnit.SECONDS);
        //4. cookie里设置openid=xyz
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);
        return ResultVoUtil.success();
    }
}
