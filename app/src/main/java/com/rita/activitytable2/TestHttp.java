package com.rita.activitytable2;

import android.app.Activity;
import android.os.Bundle;
import org.apache.http.client.HttpClient;
import android.widget.TextView;
import android.view.View;
import android.os.Handler;
import android.os.Message;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class TestHttp extends Activity
{
	TextView response;
	HttpClient httpClient;
	
	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(msg.what == 0x123){
				response.append(msg.obj.toString() + "\n"); }
		}
	};
	
	 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_http);
		
		httpClient = new DefaultHttpClient();
		response = (TextView) findViewById(R.id.response);
	}
	
	
	public void accessSecret(View v)
	{
		response.setText("");
		new Thread()
		{
			@Override
			public void run()
			{
		
				HttpGet get = new HttpGet(
						"http://10.212.37.129:8888/foo/secret.jsp");  // ①
				try
				{
					HttpResponse httpResponse = httpClient.execute(get);  // ②
					HttpEntity entity = httpResponse.getEntity();
					if (entity != null)
					{
						BufferedReader br = new BufferedReader(
								new InputStreamReader(entity.getContent()));
						String line = null;

						while ((line = br.readLine()) != null)
						{
							Message msg = new Message();
							msg.what = 0x123;
							msg.obj = line;
							handler.sendMessage(msg);
						}
					}
				}
				catch (Exception e){
					e.printStackTrace();}
			}
		}.start();
	}
	
	
}