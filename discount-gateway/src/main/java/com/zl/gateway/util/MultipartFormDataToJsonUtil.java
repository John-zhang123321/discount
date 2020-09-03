package com.zl.gateway.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author zhangliang
 */
public class MultipartFormDataToJsonUtil {

    private static final String CONTENT_TYPE="content-type";
    private static final String MULTIPART_FORM_DATA="multipart/form-data";
    private static final String BOUNDARY="boundary";

    public static Map<String,String> toMap(HttpHeaders httpHeader, String params){
        Map<String,String> map=new HashMap<String,String>();
        //判断是否为multipart/form-data请求
        if(isMultipartFormData(httpHeader)){
            //获得分隔符
            String boundary=getBoundary(httpHeader);
            //获得分割后的参数
            String[] ps= Optional.ofNullable(params).orElse("").split(boundary);
            for(String p : ps){
                p=p.trim().replaceAll("\r", "");
                p=p.trim().replaceAll("\n", "");
                if(p.equals("--") || p.contains("Content-Type")){
                    continue;
                }

                String[] ds=p.split(";");
                //获得参数名
                String nameMeta=Arrays.asList(ds).stream()
                        .filter(d -> d.trim().startsWith("name="))
                        .findAny()
                        .orElse("");
                String name=Optional.ofNullable(nameMeta.split("\"|\"")[1]).orElse("");
                //获得参数值
                String[] split = nameMeta.split("\"|\"");
                String s = split[2];
                String valueMeta=Optional.ofNullable(s).orElse("");
                String value=valueMeta.substring(0,valueMeta.lastIndexOf("--"));
                map.put(name, value);
            }
        }
        return map;
    }

    public static String toJsonString(HttpHeaders httpHeader,String params){
        return JSON.toJSONStringWithDateFormat(toMap(httpHeader,params),"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

    public static boolean isMultipartFormData(HttpHeaders httpHeader){
        return hasKey(httpHeader,MULTIPART_FORM_DATA);
    }

    public static String getBoundary(HttpHeaders httpHeader){
        return getValue(httpHeader,BOUNDARY);
    }

    private static String  getValue(HttpHeaders httpHeader,String key){
        String meta=httpHeader.get(CONTENT_TYPE)
                .stream()
                .filter(all -> {
                    String[] ks=all.split(";");
                    return Arrays.asList(ks)
                            .stream()
                            .filter(k -> {
                                return k.trim().startsWith(key+"=");
                            })
                            .findAny()
                            .isPresent();
                })
                .findAny()
                .orElse("");

        return Optional.ofNullable(meta.split("=")[1]).orElse("");
    }

    private static boolean  hasKey(HttpHeaders httpHeader,String key){
        return httpHeader.get(CONTENT_TYPE)
                .stream()
                .filter(all -> {
                    String[] ks=all.split(";");
                    return Arrays.asList(ks).stream()
                            .filter(k -> k.equals(key))
                            .findAny()
                            .isPresent();
                })
                .findAny()
                .isPresent();
    }

}
