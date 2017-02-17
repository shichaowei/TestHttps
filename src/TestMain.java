import java.util.HashMap;
import java.util.Map;
//对接口进行测试
public class TestMain {
	private static String url = "https://101.71.241.100:49010/";
	private static String charset = "utf-8";
	
	
	
	
	public  static void testpostviamap(){
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String httpOrgCreateTest = url + "channel/funds/deposit/noEncrypt/towap";
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("channel","QD000006");
		createMap.put("depositAmount","0.1");
		createMap.put("bankCardNo","6225880142391599");
		createMap.put("realName","****");
		String httpOrgCreateTestRtn = httpClientUtil.doPostviamap(httpOrgCreateTest,createMap,charset);
		System.out.println("result:"+httpOrgCreateTestRtn);
		
	}
	public  static Map<String, String> testpostviaString(String testurl,String body,String cookiedomain,String token){
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String httpOrgCreateTest = url + testurl;
		Map<String, String> httpOrgCreateTestRtn = httpClientUtil.doPostViastring(httpOrgCreateTest,body,charset,cookiedomain,token);
		
		System.out.println("result:"+httpOrgCreateTestRtn.get("body"));
		System.out.println("result:"+httpOrgCreateTestRtn.get("token"));
		return httpOrgCreateTestRtn;
	}
	
	
	
	
	public  static void testget(){
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String httpOrgCreateTest = "https://kyfw.12306.cn/otn/leftTicket/init";
		
		String httpOrgCreateTestRtn = httpClientUtil.doGet(httpOrgCreateTest);
		System.out.println("result:"+httpOrgCreateTestRtn);
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception{
		

		String token = testpostviaString("channel/channel_sys_user/getToken","{\"mobilePhone\":\"18667906998\",\"sId\":\"1\",\"userSource\":\"QD000015\"}",null,null).get("token");
		String  body ="{\"channel\": \"QD000006\",\"depositAmount\": 0.1,\"bankCardNo\": \"6225880142391500\",\"realName\": \"徐丹丹\",\"idCardNo\": \"500231198706238556\",\"type\": 1,\"url_return\": \"https://101.71.241.100:49010/funds-callback/ll/url/return?url=https://jianbing.com/shequ/discovery/index.php?route=bankx/fengedainew/bindsuccess&bank_id=33&user_sid=27414458&from=default#url2=https://jianbing.com/shequ/discovery/index.php?route=bankx/fengedainew/bindfailed&bank_id=33&user_sid=27414458&from=default\"}";
		testpostviaString("channel/funds/deposit/noEncrypt/towap",body,"101.71.241.100",token);
		
		
//		testget();
	
	}
}