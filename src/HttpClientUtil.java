import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
	public String cookie;
	
	
	public String doPostviamap(String url,Map<String,String> map,String charset){
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try{
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			//设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String,String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
			}
			if(list.size() > 0){
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
				httpPost.setEntity(entity);
			}
			
			HttpResponse response = httpClient.execute(httpPost);
			if(response != null){
				
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public Map<String, String> doPostViastring(String url,String body,String charset ,String cookiedomain,String token){
		
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		Map<String, String> result = new HashMap<String,String>();
		try{
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			//设置参数
			StringEntity entity =new StringEntity(body,Charset.forName("utf-8"));
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			
			if(token !=null && cookiedomain != null){
				 CookieStore cookieStore = new BasicCookieStore();
			     BasicClientCookie cookie1 = new BasicClientCookie("token",token);
			     cookie1.setDomain(cookiedomain);
			     cookieStore.addCookie(cookie1);
				((AbstractHttpClient) httpClient).setCookieStore(cookieStore);
			}
			
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			//拿到token
            List<Cookie> cookies = ((AbstractHttpClient) httpClient).getCookieStore().getCookies();
            if (cookies.isEmpty()) {    
                System.out.println("None");    
            } else {    
                for (int i = 0; i < cookies.size(); i++) {  
                    System.out.println("- " + cookies.get(i).toString());
                	if(cookies.get(i).getName().equals("token")){
                		result.put("token", cookies.get(i).getValue());
                	}
                }    
            } 
            
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					String resultbody = EntityUtils.toString(resEntity,charset);
					result.put("body", resultbody);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	
	public String doGet(String url,String cookiedomain,String token){
		BufferedReader in = null;  
		  
        String content = null;  
        try {  
            // 定义HttpClient  
            HttpClient client = new SSLClient();  
            // 实例化HTTP方法  
            HttpGet request = new HttpGet();  
            request.setURI(new URI(url));  
            
            if(token !=null && cookiedomain != null){
				 CookieStore cookieStore = new BasicCookieStore();
			     BasicClientCookie cookie1 = new BasicClientCookie("token",token);
			     cookie1.setDomain(cookiedomain);
			     cookieStore.addCookie(cookie1);
				((AbstractHttpClient) client).setCookieStore(cookieStore);
			}
            
            HttpResponse response = client.execute(request);  
            //拿到cookie
            List<Cookie> cookies = ((AbstractHttpClient) client).getCookieStore().getCookies();
            if (cookies.isEmpty()) {    
                System.out.println("None");    
            } else {    
                for (int i = 0; i < cookies.size(); i++) {  
                    System.out.println("- " + cookies.get(i).toString());  
                }    
            }  
            
            
            in = new BufferedReader(new InputStreamReader(response.getEntity()  
                    .getContent()));  
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            String NL = System.getProperty("line.separator"); 
          
            while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            }  
            in.close();  
            content = sb.toString();  
        } finally {  
            if (in != null) {  
                try {  
                    in.close();// 最后要关闭BufferedReader  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
            return content;  
        } 
	}
	
}