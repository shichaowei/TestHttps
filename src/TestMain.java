import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
//对接口进行测试
public class TestMain {
	private static String url = "https://192.168.1.101/";
	private static String charset = "utf-8";
	
	
	
	
	public  static void testpost(){
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String httpOrgCreateTest = url + "httpOrg/create";
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("authuser","*****");
		createMap.put("authpass","*****");
		createMap.put("orgkey","****");
		createMap.put("orgname","****");
		String httpOrgCreateTestRtn = httpClientUtil.doPostviamap(httpOrgCreateTest,createMap,charset);
		System.out.println("result:"+httpOrgCreateTestRtn);
		
	}
	
	public  static void testgetclient(){
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		String httpOrgCreateTest = "https://kyfw.12306.cn/otn/leftTicket/init";
		
		String httpOrgCreateTestRtn = httpClientUtil.doGet(httpOrgCreateTest);
		System.out.println("result:"+httpOrgCreateTestRtn);
		
	}
	
	
	public void testget() throws Exception{
		URL reqURL = new URL("https://kyfw.12306.cn/otn/leftTicket/init" ); //创建URL对象
		HttpsURLConnection httpsConn = (HttpsURLConnection)reqURL.openConnection();

		/*下面这段代码实现向Web页面发送数据，实现与网页的交互访问 
		httpsConn.setDoOutput(true); 
		OutputStreamWriter out = new OutputStreamWriter(httpsConn.getOutputStream(), "utf-8"); 
		out.write( "……" ); 
		out.flush(); 
		out.close();
		*/

		//取得该连接的输入流，以读取响应内容 
		InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());

		//读取服务器的响应内容并显示
		int respInt = insr.read();
		while( respInt != -1){
			System.out.print((char)respInt);
			respInt = insr.read();
		}
	}
	
	public static void main(String[] args) throws Exception{
		testpost();
	
	}
}