package com.jkb.common.utils;

import cn.hutool.core.util.StrUtil;
import com.baidu.aip.contentcensor.AipContentCensor;
import com.jkb.common.constants.RespCodeConstants;
import com.jkb.common.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
 * Created by zhangliang on 2019/7/12
 */
@Slf4j
public class BaiduImageCensorUtil {
    //设置APPID/AK/SK
    public static final String APP_ID = "APP_ID";
    public static final String API_KEY = "API_KEY";
    public static final String SECRET_KEY = "SECRET_KEY";
    public static AipContentCensor client;

    static {
        client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }


    public static boolean examine(MultipartFile file) {

        try {
            JSONObject res = client.antiPorn(file.getBytes());
            log.info("BaiduImageCensor -->{}",res);

            if(StrUtil.equals("色情",(String)res.get("conclusion"))){
//                throw new FileException(RespCodeConstants.CHECK_IMG_AUTH_ERROR);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }



}
