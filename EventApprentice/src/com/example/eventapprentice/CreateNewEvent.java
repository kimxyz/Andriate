package com.example.eventapprentice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class CreateNewEvent extends Activity {

	public final static String THEME = "com.example.eventapprentice.THEME";
	public final static String DATE = "com.example.eventapprentice.DATE";
	Calendar myCalendar = Calendar.getInstance();
	EditText selectDate;

	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_event);
		selectDate = (EditText) findViewById(R.id.date_picker);
		selectDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new DatePickerDialog(CreateNewEvent.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
	}

	private void updateLabel() {

		String myFormat = "MM/dd/yy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		selectDate.setText(sdf.format(myCalendar.getTime()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_new_event, menu);
		return true;
	}

	public void createNewEvent(View view) {
		Intent intent = new Intent(this, ImportGuestList.class);
		EditText editText = (EditText) findViewById(R.id.event_theme);
		String message = editText.getText().toString();
		intent.putExtra(THEME, message);
		editText = (EditText) findViewById(R.id.date_picker);
		message = editText.getText().toString();
		intent.putExtra(DATE, message);
		startActivity(intent);
	}
}
