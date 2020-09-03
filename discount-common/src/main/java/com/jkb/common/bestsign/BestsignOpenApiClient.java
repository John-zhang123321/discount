package com.jkb.common.bestsign;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 上上签混合云SDK客户端
 */
@Component
public class BestsignOpenApiClient {

	@Value("${bestsign.server-host}")
	private String serverHost;

	@Value("${bestsign.developerId}")
	private String developerId;

	@Value("${bestsign.privateKey}")
	private String privateKey;

	private static String urlSignParams = "?developerId=%s&rtick=%s&signType=rsa&sign=%s";

	public Pair<Boolean,BestsignRespose> post(String requestBody, String method,Class clazz){
		String host = this.serverHost;
		// 生成一个时间戳参数
		String rtick = RSAUtils.getRtick();
		// 计算参数签名
		String paramsSign = RSAUtils.calcRsaSign(this.developerId,
				this.privateKey, host, method, rtick, null,
				requestBody);
		// 签名参数追加为url参数
		String urlParams = String.format(urlSignParams, this.developerId,
				rtick, paramsSign);
		// 发送请求
		String responseBody = "";
		try {
			responseBody = HttpClientSender.sendHttpPost(host, method,
					urlParams, requestBody);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 返回errno为0，表示成功，其他表示失败
		BestsignRespose bestsignRespose = JSONUtil.toBean(responseBody, BestsignRespose.class);

		if (bestsignRespose.getErrno() ==  0) {
			bestsignRespose.setData(JSONUtil.toBean((cn.hutool.json.JSONObject) bestsignRespose.getData(), clazz));
			return Pair.of(true, bestsignRespose);
		} else {
			return Pair.of(false, bestsignRespose);
		}
	}
	/**
	 * @param requestBody
	 * @param method   "/user/reg/";
	 * @throws IOException
	 */
	public Pair<Boolean,Object> post(String requestBody,String method) throws Exception {
		String host = this.serverHost;
//		String method = "/user/reg/";

		// 组装请求参数，作为requestbody
//		JSONObject requestBody = new JSONObject();
//		requestBody.put("account", account);
//		requestBody.put("name", name);
//		requestBody.put("userType", "1");
//		requestBody.put("mail", mail);
//		requestBody.put("mobile", mobile);
//
//		JSONObject credential = new JSONObject();
//		credential.put("identity", identity);
//		credential.put("identityType", identityType);
//		credential.put("contactMail", contactMail);
//		credential.put("contactMobile", contactMobile);
//		credential.put("province", province);
//		credential.put("city", city);
//		credential.put("address", address);
//		requestBody.put("credential", credential);
//		requestBody.put("applyCert", "1");

		// 生成一个时间戳参数
		String rtick = RSAUtils.getRtick();
		// 计算参数签名
		String paramsSign = RSAUtils.calcRsaSign(this.developerId,
				this.privateKey, host, method, rtick, null,
				requestBody);
		// 签名参数追加为url参数
		String urlParams = String.format(urlSignParams, this.developerId,
				rtick, paramsSign);
		// 发送请求
		String responseBody = HttpClientSender.sendHttpPost(host, method,
				urlParams, requestBody);
		// 返回结果解析
		JSONObject userObj = JSON.parseObject(responseBody);
		// 返回errno为0，表示成功，其他表示失败
		if (userObj.getIntValue("errno") == 0) {
			return Pair.of(true, userObj.get("data"));
		} else {
			return Pair.of(false, userObj);
		}
	}

	/**
	 * @param urlParams url参数（param1=value1&param2=value2&param3=value3）
	 * @param method   "/storage/contract/download/";
	 * @return
	 * @throws Exception
	 */
	public byte[] get(String urlParams,String method) throws Exception {
		String host = this.serverHost;
//		String method = "/storage/contract/download/";

		// 组装url参数
//		String urlParams = "contractId=" + contractId;

		// 生成一个时间戳参数
		String rtick = RSAUtils.getRtick();
		// 计算参数签名
		String paramsSign = RSAUtils.calcRsaSign(this.developerId,
				this.privateKey, host, method, rtick, urlParams, null);
		// 签名参数追加为url参数
		urlParams = String.format(urlSignParams, this.developerId, rtick,
				paramsSign) + "&" + urlParams;
		// 发送请求
		byte[] responseBody = HttpClientSender.sendHttpGet(host, method,
				urlParams);
		// 返回结果解析
		return responseBody;
	}
}
