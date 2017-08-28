package com.wsc.work;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wsc.qa.utils.IdCardGenerator;

//对接口进行测试
public class TestMain {
	private static String url = "https://101.71.241.100:49010/";
	private static String charset = "utf-8";

	public static void testpostviamap() {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String httpOrgCreateTest = url + "channel/funds/deposit/noEncrypt/towap";
		Map<String, String> createMap = new HashMap<String, String>();
		createMap.put("channel", "QD000006");
		createMap.put("depositAmount", "0.1");
		createMap.put("bankCardNo", "6225880142391599");
		createMap.put("realName", "****");
		String httpOrgCreateTestRtn = httpClientUtil.doPostviamap(httpOrgCreateTest, createMap, charset);
		System.out.println("result:" + httpOrgCreateTestRtn);

	}

	public static Map<String, String> testpostviaString(String testurl, String body, String cookiedomain,
			String token) {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String httpOrgCreateTest = url + testurl;
		Map<String, String> httpOrgCreateTestRtn = httpClientUtil.doPostViastring(httpOrgCreateTest, body, charset,
				cookiedomain, token);

		System.out.println("result:" + httpOrgCreateTestRtn.get("body"));
		System.out.println("result:" + httpOrgCreateTestRtn.get("token"));
		return httpOrgCreateTestRtn;
	}

	public static void testget(String testurl, String cookiedomain, String token) {
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		// String httpOrgCreateTest =
		// "https://kyfw.12306.cn/otn/leftTicket/init";

		String httpOrgCreateTestRtn = httpClientUtil.doGet(testurl, cookiedomain, token);
		System.out.println("result:" + httpOrgCreateTestRtn);

	}

	public static void main(String[] args) throws Exception {

		//
		// String token =
		// testpostviaString("channel/channel_sys_user/getToken","{\"mobilePhone\":\"18667906998\",\"sId\":\"1\",\"userSource\":\"QD000015\"}",null,null).get("token");
		// String body ="{\"channel\": \"QD000006\",\"depositAmount\":
		// 0.1,\"bankCardNo\": \"6225880142391599\",\"realName\":
		// \"徐丹丹\",\"idCardNo\": \"500231198706238556\",\"type\":
		// 1,\"url_return\":
		// \"https://101.71.241.100:49010/funds-callback/ll/url/return?url=https://jianbing.com/shequ/discovery/index.php?route=bankx/fengedainew/bindsuccess&bank_id=33&user_sid=27414458&from=default#url2=https://jianbing.com/shequ/discovery/index.php?route=bankx/fengedainew/bindfailed&bank_id=33&user_sid=27414458&from=default\"}";
		// testpostviaString("channel/funds/deposit/noEncrypt/towap",body,"101.71.241.100",token);
		String token = "96AB624369AB4356B8695DC32EC08F4B.34FCB8E9F535D17FC8C80E5E2EFE74D1";
		String bodytemp = "{\"sId\":\"1\",\"thirdOrderNo\":\"third%s\",\"bankcardNo\":\"621483571134%s\",\"applyAmount\":1000,\"applyChannel\":\"QD000015\",\"applyRepayType\":\"SJSH\",\"certificateNumber\":%s,\"creditFlag\":\"false\",\"fullname\":\"%s\",\"loginName\":\"%s\",\"mobilePhone\":\"%s\",\"bankPhone\":\"%s\",\"subProductCode\":\"FDDFYD002\",\"extMap\":{\"fields\":[{\"field_id\":\"2238\",\"value\":\"input\"},{\"field_id\":\"451\",\"value\":\"input\"},{\"field_id\":\"100035\",\"value\":\"input\"},{\"field_id\":\"1735\",\"value\":\"input\"},{\"field_id\":\"1990\",\"value\":\"input\"},{\"field_id\":\"100036\",\"value\":\"input\"},{\"field_id\":\"100033\",\"value\":\"input\"},{\"field_id\":\"100028\",\"value\":\"input\"},{\"field_id\":\"100030\",\"value\":\"input\"},{\"field_id\":\"100031\",\"value\":\"input\"},{\"field_id\":\"100032\",\"value\":\"input\"},{\"field_id\":\"1996\",\"value\":\"input\"},{\"field_id\":\"882\",\"value\":\"input\"},{\"field_id\":\"883\",\"value\":\"input\"},{\"field_id\":\"2376\",\"value\":\"input\"},{\"field_id\":\"1\",\"value\":{\"field_file_url\":\"http://qiini/23图片地址\",\"field_file_name\":\"图片名称\"}},{\"field_id\":\"2\",\"value\":{\"field_file_url\":\"http://qiini/23图片地址\",\"field_file_name\":\"图片名称\"}}]}}";

		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
		for (int index = 2250; index < 2300; index++) {
			final int i = index;
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
					String body = String.format(bodytemp, String.valueOf(i), String.valueOf(i),  new IdCardGenerator().generate(),
							"魏士超", "1866791" + String.valueOf(i), "1866791" + String.valueOf(i),
							"1866761" + String.valueOf(i));
//					System.out.println(body);
					testpostviaString("channel/loan_apply/login_auth_apply", body, "101.71.241.100", token);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		fixedThreadPool.shutdown();
		while(true) {
			if(fixedThreadPool.isTerminated()) {
				break;
			}
		}
		
		
		
		

		//
		// for(int i=5050;i<5075;i++){
		// String body=
		// String.format(bodytemp,String.valueOf(i),String.valueOf(i),String.valueOf(i),"魏士超","1866790"+String.valueOf(i),
		// "1866790"+String.valueOf(i), "1866790"+String.valueOf(i));
		// System.out.println(body);
		// testpostviaString("channel/loan_apply/login_auth_apply",body,"101.71.241.100",token);
		// }

		// testget("https://doc.trc.com/#/module/674/service/1029","doc.trc.com","D82DCA9A596B4E32A05837BE739D4D42.BB73AC126DB5B1897B7BD884C9A9A18B");

	}
}