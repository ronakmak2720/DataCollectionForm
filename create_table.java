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
public class create_table {
	public static void main(String[] args) {
	//uploadData("Table001","name_varchar(255)@surname_varchar(255)@","createTable");//Create Table
    //uploadData("Table001","Samyak@Vora@","insertTableData");//insert into table
	}
	
	public static void uploadData(String tableName,String data,String fileName) {
		//fileName must be createTable or insertTableData
		String register_url="https://b383c2a5.ngrok.io/"+fileName+".php";
		//System.out.println(register_url);
		try{
		    URL url=new URL(register_url);
                HttpURLConnection hp=(HttpURLConnection)url.openConnection();//connection is established
     		   //System.out.println("Hi");

                hp.setRequestMethod("POST");
                hp.setDoOutput(true);
                OutputStream o=hp.getOutputStream();      
     		   //System.out.println("BI");
               //System.out.println(tableName+"			---"+data);

                BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(o,"UTF-8"));
                String post_data=URLEncoder.encode("tableName","UTF-8")+"="+URLEncoder.encode(tableName,"UTF-8")+"&"+ URLEncoder.encode("data","UTF-8")+"="+URLEncoder.encode(data,"UTF-8");
                //System.out.println(tableName);

                bw.write(post_data);
     		   //System.out.println("Wifi");

                bw.flush();
                bw.close();
                o.close();
                hp.getResponseCode();
		}catch(Exception e){
		   System.out.println(e);
		}	
	}
}
