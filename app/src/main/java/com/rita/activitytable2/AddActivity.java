package com.rita.activitytable2;

import com.rita.activitytable2.util.DialogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;
import android.util.Log;

public class AddActivity extends Activity 
{
	EditText activityTitle,activityPlace,activityDetail,number_test;
	Spinner activityTime ;
	Button bnAdd, bnCancel;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

		activityTitle = (EditText) findViewById(R.id.activityTitle);
		activityPlace = (EditText) findViewById(R.id.activityPlace);
		activityDetail = (EditText) findViewById(R.id.activityDetail);
		number_test = (EditText) findViewById(R.id.number_test);
		activityTime = (Spinner) findViewById(R.id.activityTime);
		/*
		String url = HttpUtil.BASE_URL + "viewKind.jsp";
		JSONArray jsonArray = null;
		try
		{
			// (获取系统中所有的物品种类)
			jsonArray = new JSONArray(HttpUtil.getRequest(url));  // ①
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		JSONArrayAdapter adapter = new JSONArrayAdapter(
				getActivity() , jsonArray , "kindName" , false);
		// (显示物品种类列表)
		itemKind.setAdapter(adapter);
		*/
		bnAdd = (Button) findViewById(R.id.bnAdd);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		//bnCancel.setOnClickListener(new HomeListener(getActivity()));
		bnCancel.setOnClickListener(new HomeListener(this));
		/*
		bnCancel.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) {
				 Intent intent_cancel = new Intent(AddActivity.this, MainActivity.class);
				 intent_cancel.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				 startActivity(intent_cancel);
			 }
		});
		*/
		
		bnAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 执行输入校验
				if (validate())
				{
					String title = activityTitle.getText().toString();
					String place = activityPlace.getText().toString();
					String detail = activityDetail.getText().toString();
					String strNumber = number_test.getText().toString();
					//String price = initPrice.getText().toString();
					//这两个get方法 可能是spinner自带的
					//JSONObject kind = (JSONObject) itemKind.getSelectedItem();
					int time = activityTime.getSelectedItemPosition();
					/*
					//根据用户选择有效时间选项，指定实际的有效时间
					switch(avail)
					{
						case 5 :
							avail = 7;
							break;
						case 6 :
							avail = 30;
							break;
						default :
							avail += 1;
							break;
					}
					try
					{			
						// 添加物品
						String result = addItem(name, desc
								, remark , price , kind.getInt("id") , avail);
						// 显示对话框
						DialogUtil.showDialog(getActivity()
								, result , true);
					}
					catch (Exception e)
					{
						DialogUtil.showDialog(getActivity()
								, "服务器响应异常，请稍后再试！" , false);
						e.printStackTrace();
					}
					*/
					Log.i("sunflower","add activity title");
					Log.i("sunflower",title);
				}
			}
		});
		//return rootView;
	}

	
	private boolean validate()
	{
		String title = activityTitle.getText().toString().trim();
		if (title.equals(""))
		{
			DialogUtil.showDialog(this , "活动标题是必填项！" , false);
			return false;
		}
		String strNumber = number_test.getText().toString().trim();
		if (strNumber.equals(""))
		{
			DialogUtil.showDialog(this , "数字测试是必填项！" , false);
			return false;
		}
		//xml定义了数值，就输入不了字符串
		//或者没有定义数值，在这里检查
		try
		{
			// 尝试把起拍价格转换为浮点数
			Double.parseDouble(strNumber);
		}
		catch(NumberFormatException e)
		{
			DialogUtil.showDialog(this , "起拍价格必须是数值！" , false);
			return false;
		}

		return true;
	}

	/*
	private String addItem(String name, String desc
			, String remark , String initPrice , int kindId , int availTime)
			throws Exception
	{
		// (使用Map封装请求参数)
		Map<String , String> map = new HashMap<>();
		map.put("activityTitle" , title);
		map.put("activityPlace" , place);
		map.put("activityDetail" , detail);
		//map.put("initPrice" , initPrice);
		//map.put("kindId" , kindId + "");
		map.put("activityTime" , time + "");
		//String url = HttpUtil.BASE_URL + "addItem.jsp";
		//return HttpUtil.postRequest(url , map);
	}
	*/
	
}