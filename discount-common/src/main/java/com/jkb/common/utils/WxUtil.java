package com.jkb.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.jkb.common.response.WxResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangliang on 2019/7/6
 */
@Slf4j
public class WxUtil {

    private static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx74a0c57de56c7a53&secret=0b504e9e12e8acee43dd7a7d0832b97c";

    private static String openid_url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx74a0c57de56c7a53&secret=0b504e9e12e8acee43dd7a7d0832b97c&js_code={}&grant_type=authorization_code";

    private static String send_url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";

    //审核模版id
    private static String audit_templateId = "0EMoRVnLgK6PUOfjv5jI4DfmcWkAWxx9z76sMPBy-yg";

    public static String getOpenid(String code) {
        String result = HttpUtil.get(StrUtil.format(openid_url, code));
        WxResponse wxResponse = JSONUtil.toBean(result, WxResponse.class);
        String openid = wxResponse.getOpenid();
        return openid;
    }

    public static String getAccessToken() {
        return JSONUtil.toBean(HttpUtil.get(access_token_url), WxResponse.class).getAccess_token();
    }

    public static void main(String[] args) {
        pushOneUser("oN8MZ4zBNtPrd-3faysd5Y9GJyRs", "不通过", "不符","sfsdf");
    }
    public static String pushOneUser(String openid,String result,String remark,String type) {
        log.info("openid [{}]",openid);
        //拼接推送的模版
        WxMssDTO wxMssVo = new WxMssDTO();
        wxMssVo.setTouser(openid);//用户openid
        wxMssVo.setTemplate_id(audit_templateId);//模版id


        Map<String, TemplateData> m = new HashMap<>(5);

        TemplateData keyword1 = new TemplateData();
        keyword1.setValue(result);
        m.put("phrase1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setValue(type);
        m.put("thing2", keyword2);

        TemplateData keyword4 = new TemplateData();
        keyword4.setValue(DateUtil.now());
        m.put("date3", keyword4);

        TemplateData keyword3 = new TemplateData();
        keyword3.setValue(remark);
        m.put("thing7", keyword3);

        wxMssVo.setData(m);
        String jsonStr = JSONUtil.toJsonStr(wxMssVo);
        log.info("jsonStr [{}]",jsonStr);
        String result2 = HttpUtil.post(send_url + getAccessToken(),jsonStr);
        log.info("pushOneUser result [{}]",result2);
        return result2;
    }
}
