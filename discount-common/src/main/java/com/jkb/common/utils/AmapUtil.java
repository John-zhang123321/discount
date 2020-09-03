package com.jkb.common.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.jkb.common.entity.amap.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Description 高德地图工具类
 */
public class AmapUtil {
    private Logger logger = LoggerFactory.getLogger(AmapUtil.class);
    //高德地图key
    private static String key = "key";

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                logger.info(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
//            logger.info("发送GET请求出现异常！" + e.getMessage());
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String url, String param) throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            logger.info("发送 POST 请求出现异常！" + e.getMessage());
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * GET请求数据
     *
     * @param get_url url地址
     * @param content key=value形式
     * @return 返回结果
     * @throws Exception
     */
    public static String sendGetData(String get_url, String content) throws Exception {
        String result = "";
        URL getUrl = null;
        BufferedReader reader = null;
        String lines = "";
        HttpURLConnection connection = null;
        try {
            if (content != null && !content.equals(""))
                get_url = get_url + "?" + content;
            // get_url = get_url + "?" + URLEncoder.encode(content, "utf-8");
            getUrl = new URL(get_url);
            connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            // 取得输入流，并使用Reader读取
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码
            while ((lines = reader.readLine()) != null) {
                result = result + lines;
            }
            return result;
        } catch (IOException e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
                reader = null;
            }
            connection.disconnect();
        }
    }

    /**
     * @param POST_URL url地址
     * @param content  key=value形式
     * @return 返回结果
     * @throws Exception
     */
    public static String sendPostData(String POST_URL, String content) throws Exception {
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        BufferedReader reader = null;
        String line = "";
        String result = "";
        try {
            URL postUrl = new URL(POST_URL);
            connection = (HttpURLConnection) postUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();

            out = new DataOutputStream(connection.getOutputStream());
            // content = URLEncoder.encode(content, "utf-8");
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符�?8位的字符形式写道流里�?
            out.writeBytes(content);
            out.flush();
            out.close();
            // 获取结果
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码
            while ((line = reader.readLine()) != null) {
                result = result + line;
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
            if (reader != null) {
                reader.close();
                reader = null;
            }
            connection.disconnect();
        }
    }

    /*
     * 过滤掉html里不安全的标签，不允许用户输入这些标�?
     */
    public static String htmlFilter(String inputString) {
        // return inputString;
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        java.util.regex.Matcher m_script;

        try {
            String regEx_script = "<[\\s]*?(script|style)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(script|style)[\\s]*?>";
            String regEx_onevent = "on[^\\s]+=\\s*";
            String regEx_hrefjs = "href=javascript:";
            String regEx_iframe = "<[\\s]*?(iframe|frameset)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(iframe|frameset)" +
                    "[\\s]*?>";
            String regEx_link = "<[\\s]*?link[^>]*?/>";

            htmlStr = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");
            htmlStr = Pattern.compile(regEx_onevent, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");
            htmlStr = Pattern.compile(regEx_hrefjs, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");
            htmlStr = Pattern.compile(regEx_iframe, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");
            htmlStr = Pattern.compile(regEx_link, Pattern.CASE_INSENSITIVE).matcher(htmlStr).replaceAll("");
            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return textStr;
    }


    /**
     * api 货车路径规划
     * https://lbs.amap.com/api/webservice/guide/api/direction#t9
     *
     * @param origin       起点经纬度
     * @param destination  终点经纬度
     * @param truckSize    货车类型 1：微型车，2：轻型车（默认值），3：中型车，4：重型车
     * @param showpolyline 是否返回路线数据 1 返回  2 不返回
     * @param nosteps      是否返回steps字段内容 0 返回 1 不返回
     * @return 最短距离
     * @throws IOException
     */
    public String getTruckDistance(String origin, String destination, String truckSize, String showpolyline, String nosteps) throws IOException {
        if (StringUtils.isBlank(truckSize)) {
            truckSize = "2";
        }
        if (StringUtils.isBlank(showpolyline)) {
            showpolyline = "2";
        }
        if (StringUtils.isBlank(nosteps)) {
            nosteps = "1";
        }
        StringBuffer s = new StringBuffer();
        s.append("key=").append(key).
                append("&origin=").append(origin).
                append("&destination=").append(destination).
                append("&size=").append(truckSize).
                append("&showpolyline=").append(showpolyline).
                append("&nosteps=").append(nosteps);
        String res = sendGet("https://restapi.amap.com/v4/direction/truck", s.toString());
        logger.info(res);
        AmapEntity amapEntity = JSONUtil.toBean(res, AmapEntity.class);
        Route route = JSONUtil.toBean(amapEntity.getData().toString(), Route.class);
        RouteChild routePathList = route.getRoute();
        if (routePathList != null) {
            List<RouteChildPath> childPathList = routePathList.getPaths();
            if (!CollectionUtils.isEmpty(childPathList)) {
                System.out.println(childPathList);
                System.out.println(childPathList.get(0).getDistance());
                return childPathList.get(0).getDistance() + "";
            }
        }
        return null;
    }

    /**
     * api 距离测量
     * https://lbs.amap.com/api/webservice/guide/api/direction#distance
     *
     * @param origins      起点经纬度:116.481028,39.989643|114.481028,39.989643|115.481028,39.989643  坐标对见用“| ”分隔；经度和纬度用","分隔
     * @param destination  终点经纬度
     * @param type    路径计算的方式和方法 0：直线距离1：驾车导航距离2：公交规划距离3：步行规划距离
     * @param output 返回数据格式类型 JSON  ,XML
     * @return Map<String,Integer>  {1=260296, 2=103903, 3=198544}   key 坐标序号  val距离
     * @throws IOException
     */
    public static Map<Integer,Integer> getDistanceList(String origins, String destination, String type,String output){
        if(StringUtils.isBlank(origins)||StringUtils.isBlank(destination)){
//            throw new BaseException("经纬度参数不能为空");
            return new HashMap<>();
        }
        if (StringUtils.isBlank(type)) {
            type = "1";
        }
        if (StringUtils.isBlank(output)) {
            output = "JSON";
        }
        StringBuffer s = new StringBuffer();
        s.append("key=").append(key).
                append("&origins=").append(origins).
                append("&destination=").append(destination).
                append("&type=").append(type).
                append("&output=").append(output);
        String res = sendGet("https://restapi.amap.com/v3/distance", s.toString());
        AmapEntity amapEntity = JSONUtil.toBean(res, AmapEntity.class);
        JSONArray jsonArray = (JSONArray) amapEntity.getResults();

        List<DistantResult> distantResultList = JSONUtil.toList(jsonArray,DistantResult.class);
        Map<Integer,Integer> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(distantResultList)) {
            for(DistantResult distantResult : distantResultList){
                map.put(distantResult.getOrigin_id(),distantResult.getDistance());
            }
            return map;
        }
        return null;
    }


//    public static void main(String[] args) throws IOException{
//        String origins = "116.481028,39.989643|114.481028,39.989643|115.481028,39.989643";
//        String destination = "114.465302,40.004717";
//        Map<String,Integer> map =  AmapUtil.getDistanceList(origins,destination,"","");
//        System.out.println(map);
//    }
}
