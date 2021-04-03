package dataCollectionForm; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class fetch {
	public static void fetchData(String tableName) {
				String register_url="https://b383c2a5.ngrok.io/fetcher.php";
				try{
				    URL url=new URL(register_url);
		                HttpURLConnection hp=(HttpURLConnection)url.openConnection();//connection is established
		                hp.setRequestMethod("POST");
		                hp.setDoOutput(true);
		                OutputStream o=hp.getOutputStream();        
		                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(o,"UTF-8"));
		                String post_data=URLEncoder.encode("tableName","UTF-8")+"="+URLEncoder.encode(tableName,"UTF-8");
		                bw.write(post_data);
		                bw.flush();
		                bw.close();
		                o.close();
		                hp.getResponseCode();           
		                InputStream i=hp.getInputStream();
		                BufferedReader br=new BufferedReader(new InputStreamReader(i,"iso-8859-1"));
		                String result="";
		                String line="";
		                while((line=br.readLine())!=null){
		                	//System.out.println(line);
		                }
		                br.close();
		                i.close();
		                hp.disconnect();
				}catch(Exception e){
				   System.out.println(e);
				}
	}
}