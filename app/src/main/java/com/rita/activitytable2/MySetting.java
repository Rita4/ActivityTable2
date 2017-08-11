package com.rita.activitytable2;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//{ bottom bar
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.content.Intent;
//}

public class MySetting extends Activity {
    EditText set_name,set_age,set_work;
    Button bn_save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_setting);

        //{ bottom bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.setting_navigation);
        navigation.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            Intent intent_one = new Intent(MySetting.this,MainActivity.class);
                            intent_one.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent_one);
                            return true;
                        case R.id.navigation_dashboard:
                            Intent intent_second = new Intent(MySetting.this, FunPage.class);
                            intent_second.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Bundle data = new Bundle();
                            data.putString("picture", "a");
                            intent_second.putExtras(data);
                            startActivity(intent_second);
                            return true;
                        case R.id.navigation_notifications:
                            //Intent intent_third = new Intent(FunPage.this, MySetting.class);
                            //intent_third.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            //startActivity(intent_third);
                            return true;
                    }
                    return false;
                }
            });
        //}

        set_name = (EditText) findViewById(R.id.set_name);
        set_age = (EditText) findViewById(R.id.set_age);
        set_work = (EditText) findViewById(R.id.set_work);
        bn_save = (Button) findViewById(R.id.bn_save);
        bn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = set_name.getText().toString();
                String age = set_age.getText().toString();
                String work = set_work.getText().toString();
                set_name.setText(name);
                set_age.setText(age);
                set_work.setText(work);
            }
        });


    }


}