package com.rita.activitytable2;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;

import com.rita.activitytable2.LeeSqlite;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;
import static android.widget.AdapterView.OnItemClickListener;
import android.support.v7.app.AppCompatActivity;

import com.rita.activitytable2.util.DialogUtil;

//{ Base adapter
import org.json.JSONArray;
import android.widget.Spinner;
import com.rita.activitytable2.JSONArrayAdapter;
//}

public class OtherTest extends AppCompatActivity
{
	
	//Spinner itemKind;
	ListView succList,kindList;
	
	ListView myList;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_test);
		
		
		//{ sql
		ListView listViewSql = (ListView) findViewById(R.id.list_sql);
		LeeSqlite leeSql= new LeeSqlite(this,listViewSql);
		leeSql.getCenten();
		//}
		
		//{ dialog
		DialogUtil.showDialog(this, "服务器响应出现异常！", false);
		//}

		//View rootView = inflater.inflate(R.layout.add_bid, container , false);
		//View rootView = inflater.inflate(R.layout.simple_item, null);
		//TextView inflate_text = (TextView)rootView.findViewById(R.id.title);
		//inflate_text.setText("inflate_text_ok");	
		
		//{ Base adapter ，另一个kind adapter 只是加了一个linearView
		succList = (ListView)findViewById(R.id.succList);
		try
		{
			String jArrayAdapter="[{\"id\":0,\"kindName\":\"kinga\"},{\"id\":1,\"kindName\":\"kingb\"}]";
			JSONArray JA=new JSONArray(jArrayAdapter);
			JSONArrayAdapter JA_adapter = new JSONArrayAdapter(
					OtherTest.this , JA , "kindName" , true);
			succList.setAdapter(JA_adapter);
		}
		catch (Exception e_jadapter){
			e_jadapter.printStackTrace();}
		succList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id)
			{
				JSONObject jsonObj_ada = (JSONObject) succList
						.getAdapter().getItem(position);
				try {
					Log.i("sunflower", "jsonAadapter");
					Log.i("sunflower", jsonObj_ada.getString("kindName"));
				}
				catch (Exception e_ada){
					e_ada.printStackTrace();}

			}
		});
		//}
		
		
	}
	
	
	
	
	
	
}