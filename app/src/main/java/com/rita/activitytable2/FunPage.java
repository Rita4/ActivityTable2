package com.rita.activitytable2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;

//{ bottom bar
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
//}

public class FunPage extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fun_page);

		//args.putInt("itemId", getIntent().getIntExtra("itemId", -1));
		//arguments.putString("action"
			//	, getIntent().getStringExtra("action"));
		//{ get intent
		Intent intent = getIntent();
		Bundle params = (Bundle) intent.getExtras();
		String itemStr = params.getString("picture");

		TextView fun_title = (TextView) findViewById(R.id.fun_title);
		TextView fun_place = (TextView) findViewById(R.id.fun_place);
		TextView fun_time = (TextView) findViewById(R.id.fun_time);
		TextView fun_detail = (TextView) findViewById(R.id.fun_detail);
		ImageView fun_picture=(ImageView) findViewById(R.id.fun_picture);

		fun_title.setText("跑步");
		fun_place.setText("北京");
		fun_time.setText("周六");
		fun_detail.setText("锻炼身体");
		fun_picture.setImageResource(R.drawable.fun_pic_1);

		switch (itemStr)
		{
			case "a":
				break;
			case "b":
				fun_title.setText("狼人杀");
				fun_place.setText("北京");
				fun_time.setText("周日");
				fun_detail.setText("锻炼身体");
				fun_picture.setImageResource(R.drawable.fun_pic_2);
				break;
		}
		//}

		//{ bottom bar
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.fun_navigation);
		navigation.setOnNavigationItemSelectedListener(
			new BottomNavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(@NonNull MenuItem item) {
					switch (item.getItemId()) {
						case R.id.navigation_home:
							Intent intent_one = new Intent(FunPage.this,MainActivity.class);
							intent_one.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent_one);
							return true;
						case R.id.navigation_dashboard:
							//Intent intent_second = new Intent(MainActivity.this, AddActivity.class);
							//startActivity(intent_second);
							return true;
						case R.id.navigation_notifications:
							Intent intent_third = new Intent(FunPage.this, MySetting.class);
							intent_third .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent_third);
							return true;
					}
					return false;
				}
			});
		//}


	}
	

}