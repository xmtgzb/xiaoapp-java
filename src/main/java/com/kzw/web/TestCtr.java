package com.kzw.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kzw.TestApplication;

import com.kzw.VO.WeChatUserInfo;
import com.kzw.client.HttpClientHelper;
import com.kzw.constant.SystemConstant;
import com.kzw.entity.UserEO;
import com.kzw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.ResourceLoader;

import javax.servlet.http.HttpServletRequest;

/**
 * @author panaidong
 * @version V1.0
 * @Title:
 * @Description:
 * @date
 */
@Controller
//@EnableAutoConfiguration
public class TestCtr {
    private static Logger log = LoggerFactory.getLogger(TestApplication.class);

    @Autowired
    private UserService userService;
    /**
     * 用户首页，用于登录之后给用户看一些信息
     * 信息可以从数据库中取，这边作为demo，暂时写死
     * @return
     */
    @RequestMapping("/login")
    //@ResponseBody
    String home(HttpServletRequest request,UserEO user) {

//        try{
//            userService.save(user);
//        }catch (Exception e){
//            log.error("登录出错",e);
//            return "error";
//        }
        return "index";
    }

/**
 * @Author kzw
 * @Description //TODO
 * @Date 11:01 2019/8/23
 * @Param
 * @return
 **/
    @RequestMapping("/login_wx")
    @ResponseBody
    public String loginSimple(HttpServletRequest request,String code) throws Exception {

        String url = new StringBuilder().append(WeChatUserInfo.loginUrl)
                .append("appid="+ WeChatUserInfo.appid)
                .append("&secret="+WeChatUserInfo.SECRET)
                .append("&js_code="+code)
                .append("&grant_type=authorization_code")
                .toString();

        String result = HttpClientHelper.get(url);
        if(result == null ) {//请求失败
            log.error("请求失败！！！");
            return null;
        }

        JSONObject jsonObj = JSON.parseObject(result);
        log.info(jsonObj.toJSONString());
        String openId = jsonObj.getString("openid");
        WeChatUserInfo weUser = new WeChatUserInfo();
        weUser.setOpenId(openId);
        if (!StringUtils.isEmpty(openId)){
            userService.save(new UserEO(openId,null));
            request.getSession().setAttribute("user",new UserEO(openId,null));
        }
        return openId;
    }
    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index.html";
    }


}
