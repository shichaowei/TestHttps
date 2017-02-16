package test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class TestEverything {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化 
        try {
			TrustManager[] tm = { new MyX509TrustManager() }; 
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE"); 
			sslContext.init(null, tm, new java.security.SecureRandom()); 
			// 从上述SSLContext对象中得到SSLSocketFactory对象 
			SSLSocketFactory ssf = sslContext.getSocketFactory(); 
			// 创建URL对象 
			URL myURL = new URL("https://kyfw.12306.cn/otn/leftTicket/init"); 
			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象 
			HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection(); 
			httpsConn.setSSLSocketFactory(ssf); 
			// 取得该连接的输入流，以读取响应内容 
			InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream()); 
			// 读取服务器的响应内容并显示 
			int respInt = insr.read(); 
			while (respInt != -1) { 
			    System.out.print((char) respInt); 
			    respInt = insr.read(); 
			}
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
