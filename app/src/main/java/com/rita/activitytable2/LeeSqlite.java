package com.rita.activitytable2;

//import com.rita.ohshit5.BaseUi;
import android.support.v7.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class LeeSqlite
{
	SQLiteDatabase db;
	ListView listView;
	AppCompatActivity ui;
	
	public LeeSqlite (AppCompatActivity ui, ListView listView)
	{
		db = SQLiteDatabase.openOrCreateDatabase(
				ui.getFilesDir().toString()
				+ "/my.db3", null);
		this.listView	=		listView;
		this.ui	=		ui;
	}
	
	public void getCenten()
	{		
		try
		{
			insertData(db, "title111", "content222");
			insertData(db, "title222", "content222");
			Cursor cursor = db.rawQuery("select * from news_inf"
					, null);
			//String reStr = cursor.getString(0);
			inflateList(cursor);
		}
		catch (SQLiteException se)
		{
			// 执行DDL创建数据表
			db.execSQL("create table news_inf(_id integer"
					+ " primary key autoincrement,"
					+ " news_title varchar(50),"
					+ " news_content varchar(255))");
			// 执行insert语句插入数据
			insertData(db, "title111", "content111");
			insertData(db, "title222", "content222");
			// 执行查询
			Cursor cursor = db.rawQuery("select * from news_inf"
					, null);
			//reStr = cursor.getString(0);
			inflateList(cursor);
		}

		
		if (db != null && db.isOpen()){
			db.close();}
		
		//return reStr;

	}
	
	private void insertData(SQLiteDatabase db
			, String title, String content)  // ②
	{
		// 执行插入语句
		db.execSQL("insert into news_inf values(null , ? , ?)"
				, new String[] {title, content });
	}
	
	private void inflateList(Cursor cursor)
	{
		// 填充SimpleCursorAdapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
			ui,
			R.layout.line, cursor,
			new String[] { "news_title", "news_content" }
			, new int[] {R.id.my_title, R.id.my_content },
			CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);  // ③
		// 显示数据
		listView.setAdapter(adapter);
	}
	
	
	
}