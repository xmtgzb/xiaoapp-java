package com.kzw.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kzw.TestApplication;

import com.kzw.VO.Result;
import com.kzw.VO.TestVO;
import com.kzw.VO.WeChatUserInfo;
import com.kzw.client.HttpClientHelper;
import com.kzw.constant.SystemConstant;
import com.kzw.entity.CommonItemEO;
import com.kzw.entity.UserEO;
import com.kzw.service.CommonItemService;
import com.kzw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private CommonItemService itemService;
    /**
     * 用户首页，用于登录之后给用户看一些信息
     * 信息可以从数据库中取，这边作为demo，暂时写死
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result home(HttpServletRequest request) {
        UserEO user = new UserEO("oCpAF5m9CLIVpTGYGLs18DJIEKug",null);
        try{
            user=userService.save(user);
            SystemConstant.USER_MAP.put(user.getUserCode(),user);
        }catch (Exception e){
            log.error("登录出错",e);
            return Result.error("登录出错");
        }
        return  Result.ok().put("user",user);
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
    public Result loginSimple(HttpServletRequest request,String code) throws Exception {
        UserEO user =null;
        String url = new StringBuilder().append(WeChatUserInfo.loginUrl)
                .append("appid="+ WeChatUserInfo.appid)
                .append("&secret="+WeChatUserInfo.SECRET)
                .append("&js_code="+code)
                .append("&grant_type=authorization_code")
                .toString();
        String result = HttpClientHelper.get(url);
        if(result == null ) {//请求失败
            log.error("请求失败！！！");
            return Result.error("请求失败！！！");
        }

        JSONObject jsonObj = JSON.parseObject(result);
        log.info(jsonObj.toJSONString());
        String openId = jsonObj.getString("openid");
        WeChatUserInfo weUser = new WeChatUserInfo();
        weUser.setOpenId(openId);
        if (!StringUtils.isEmpty(openId)){
            user =new UserEO(openId,null);
            userService.save(user);
            SystemConstant.USER_MAP.put(user.getUserCode(),user);
        }
        return Result.ok().put("user",user);
    }
    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    @ResponseBody
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "注销成功";
    }
    /**
     * 注销登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/check")
    @ResponseBody
    public Result check(HttpServletRequest request) {
        CommonItemEO eo = itemService.getByCode("env_flag");
        if (eo==null||!"1".equals(eo.getValue())){
            return Result.ok().put("list",null);
        }
        List<TestVO> list = new ArrayList<>();
        TestVO vo =new TestVO();
        vo.setDesc("1.很多人的一生，要耗费许多时间，来和自己的性格，童年的阴影或者原生家庭，某段深刻的影响抗衡，所以他们的路，会比其他人走得更慢。");
        list.add(vo);
        TestVO vo1 =new TestVO();
        vo1.setDesc("2.忧郁是因为自己的无能，烦恼是由于欲望得不到满足，暴躁是一种虚怯的表现。 ");
        list.add(vo1);
        TestVO vo2 =new TestVO();
        vo2.setDesc("3.我天生不合群，一向话少，时而冷场。有过被孤立，有过被诟病，有过自我质疑，也有到过崩坏的边缘。合也无味，弧也无味。党同伐异这是人性。最终决定做个哑巴。少戾气，不言语，从心过活。");
        list.add(vo2);
        TestVO vo3 =new TestVO();
        vo3.setDesc("4.人的崩溃都是悄无声息的，你坐在那里一动不动，内心确是一片狼藉，满地灰烬。");
        list.add(vo3);

        TestVO vo4 =new TestVO();
        vo4.setDesc("5.人的生命格局一大，就不会再琐碎的事情上沉溺。");
        list.add(vo4);

        TestVO vo5 =new TestVO();
        vo5.setDesc("6.当自己的小众爱好突然火了，是一种怎样的滋味？");
        list.add(vo5);

        TestVO vo6 =new TestVO();
        vo6.setDesc("7.难过的不是自己喜欢的事物大众化，而是后来的大多数人都不真正了解它。");
        list.add(vo6);

        TestVO vo7 =new TestVO();
        vo7.setDesc("7.很多时候，沉默并非是无话可说，而是一言难尽。");
        list.add(vo7);

        return Result.error().put("list",list);
    }

}
