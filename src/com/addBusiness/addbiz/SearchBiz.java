package com.addBusiness.addbiz;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class SearchBiz extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ListView lv = (ListView) findViewById(R.id.list);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_biz);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search_biz, menu);
		return true;
	}

}
