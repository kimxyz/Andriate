package com.example.eventapprentice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class ImportGuestList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_import_guest_list);
		// Show the Up button in the action bar.
		setupActionBar();
		NewEventDBEntry();
		
	}
	
	private void NewEventDBEntry() {
		Intent intent = getIntent();
		String theme = intent.getStringExtra(CreateNewEvent.THEME);
		String date = intent.getStringExtra(CreateNewEvent.DATE);
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
		Event newEvent = new Event(theme, sqlDate);
		MySQLiteHelper DbHelper = new MySQLiteHelper(this);
		SQLiteDatabase db = DbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", newEvent.getTheme());
		//Manually enter date value, need to lookup SQL statement
		db.insert(MySQLiteHelper.TABLE_EVENT, null, values);
		
		
	}


	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.import_guest_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
