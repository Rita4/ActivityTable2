package com.rita.activitytable2;

//{ yuan
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
//}
import android.util.Log;

//{simpleAdapter
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//}

//{ intent
import android.content.Intent;	
import android.view.View;
import android.widget.AdapterView;
import static android.widget.AdapterView.OnItemClickListener;
import com.rita.activitytable2.FunPage;
//import android.widget.ListView;
//import android.os.Bundle;
//}

//{action bar
import android.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//}



import android.view.LayoutInflater;

//{ 有可能是base adapter 测试
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
//}



public class MainActivity extends AppCompatActivity {

	private ListView list;
	private List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

	ActionBar actionBar;

	private LayoutInflater inflater;

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		//{ bottom bar
		//mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(
			new BottomNavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(@NonNull MenuItem item) {
					Intent intent_navi=null;
					switch (item.getItemId()) {
						case R.id.navigation_home:
							//Intent intent_one = new Intent(MainActivity.this, AddActivity.class);
							//startActivity(intent_one);
							return true;
						case R.id.navigation_dashboard:
							intent_navi = new Intent(MainActivity.this, FunPage.class);
							intent_navi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							Bundle data = new Bundle();
							data.putString("picture", "a");
							intent_navi.putExtras(data);
							startActivity(intent_navi);
							return true;
						case R.id.navigation_notifications:
							intent_navi = new Intent(MainActivity.this, MySetting.class);
							intent_navi.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent_navi);
							return true;
					}
					return false;
				}
			});
		//}
		
		//{JsonArray -> List -> Adapter -> xml
		try{
			String httpResult="["+
					"{\"picture\":\"a\",\"title\":\"跑步\",\"place\":\"北京\",\"time\":\"周六\"},"+
					"{\"picture\":\"b\",\"title\":\"狼人杀\",\"place\":\"北京\",\"time\":\"周日\"}]";
			int[] imageIds = new int[] {R.drawable.pic1 , R.drawable.pic2};
			JSONArray jsonArray=new JSONArray(httpResult);
			for(int i=0;i<jsonArray.length();i++)
			{
				JSONObject jsonObj=jsonArray.optJSONObject(i);
				Map<String, Object> listItem = new HashMap<String, Object>();
				listItem.put("picture", imageIds[i]);
				//itemStrs[i]=jsonObj.getString("picture");
				listItem.put("title",jsonObj.getString("title"));
				listItem.put("place", jsonObj.getString("place"));
				listItem.put("time", jsonObj.getString("time"));
				listItems.add(listItem);
			}
			SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
					R.layout.simple_item,
					new String[] { "picture", "title" , "place", "time"},
					new int[] { R.id.picture, R.id.title , R.id.place, R.id.time });
			list = (ListView) findViewById(R.id.main_list);
			list.setAdapter(simpleAdapter);
		}
		catch(JSONException e){
			e.printStackTrace();}
		//}

		//{ list click
		list.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				
                Bundle data = new Bundle();
				if(position==0){
					data.putString("picture", "a");}
				else{
					data.putString("picture", "b");}
				Intent intent = new Intent(MainActivity.this, FunPage.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtras(data);
				startActivity(intent);
				
				//viewItemInBid(position);
				
            }
        });	
		//}

		//{ action bar 1
		actionBar = getActionBar();
		// 设置是否显示应用程序图标
		//actionBar.setDisplayShowHomeEnabled(true);
		// 将应用程序图标设置为可点击的按钮
		// actionBar.setHomeButtonEnabled(true);
		// 将应用程序图标设置为可点击的按钮，并在图标上添加向左箭头
		//actionBar.setDisplayHomeAsUpEnabled(true);
		//}

		

		
	}
	
	/*
	private void viewItemInBid(int position)
	{
		// 加载detail_in_bid.xml界面布局代表的视图
		View detailView = getActivity().getLayoutInflater()
				.inflate(R.layout.detail_in_bid, null);
		// 获取detail_in_bid.xml界面中的文本框
		TextView itemName = (TextView) detailView
				.findViewById(R.id.itemName);
		TextView itemKind = (TextView) detailView
				.findViewById(R.id.itemKind);
		TextView maxPrice = (TextView) detailView
				.findViewById(R.id.maxPrice);
		TextView initPrice = (TextView) detailView
				.findViewById(R.id.initPrice);
		TextView endTime = (TextView) detailView
				.findViewById(R.id.endTime);
		TextView itemRemark = (TextView) detailView
				.findViewById(R.id.itemRemark);
		// 获取被单击列表项所包装的JSONObject
		JSONObject jsonObj = (JSONObject) itemList.getAdapter().getItem(
				position);
		try
		{
			// 通过文本框显示物品详情
			itemName.setText(jsonObj.getString("name"));
			itemKind.setText(jsonObj.getString("kind"));
			maxPrice.setText(jsonObj.getString("maxPrice"));
			itemRemark.setText(jsonObj.getString("desc"));
			initPrice.setText(jsonObj.getString("initPrice"));
			endTime.setText(jsonObj.getString("endTime"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		DialogUtil.showDialog(getActivity(), detailView);
	}
	*/
	
	
	//{ action bar 2
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater mInflator = new MenuInflater(this);
		mInflator.inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem mi)
	{
		if(mi.isCheckable()) {
			mi.setChecked(true);}
		switch (mi.getItemId())
		{
			case R.id.add_one:
				Intent intent_add = new Intent(MainActivity.this, AddActivity.class);
				// 添加额外的Flag，将Activity栈中处于FirstActivity之上的Activity弹出
				intent_add.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent_add);
				break;
			case R.id.bar_test_http:
				Intent intent_http = new Intent(MainActivity.this, TestHttp.class);
                intent_http.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent_http);
				break;
			case R.id.login_page:
				Intent intent_login = new Intent(MainActivity.this, Login.class);
				intent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent_login);
				break;
			case R.id.set_sample:
				Intent intent_setsam = new Intent(MainActivity.this, SettingsActivity.class);
				intent_setsam.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent_setsam);
				break;
			case R.id.other_test:
				Intent intent_othertest = new Intent(MainActivity.this, OtherTest.class);
				intent_othertest.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent_othertest);
				break;
		}
		return true;
	}
	//}


}

