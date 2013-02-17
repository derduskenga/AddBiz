package com.addBusiness.addbiz;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.R.string;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddBizDashboardActivity extends Activity {
	public static final String SENDER_ID = "905757219933";// This is the project
															// number as issued
	ProgressDialog pDialog; // by google console
	AccountManager acm = null;
	String USER_GOOGLE_MAIL = "";
	private AddBizDashboardActivity _this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		_this = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_biz_dashboard);

		// create variable for all the dashboard buttons
		Button btn_add_places = (Button) findViewById(R.id.btn_add_places);
		Button btn_add_biz = (Button) findViewById(R.id.btn_add_biz);
		Button btn_search_places = (Button) findViewById(R.id.btn_search_places);
		Button btn_search_biz = (Button) findViewById(R.id.btn_search_biz);
		Button btn_about = (Button) findViewById(R.id.btn_about);

		/**
		 * Handling all button click events i.e. setting click listeners to the
		 * buttons
		 * */

		btn_add_places.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Log.e("Registration",checkAccountManager ());
				if (isOnline()) {

					if (checkPreference()) {// device registered to receive from
											// GCM
						// servers
						SharedPreferences sp = getSharedPreferences(
								Utilities.REGISTRATION_ID_PREFERENCE, 0);
						send_deviceID_to_application_server(
								sp.getString("id", "defValue"),
								Utilities.url_send_devideID_to_applicatio_server);
						// do other stuff i.e call a new intent to do work
						Intent i = new Intent(_this, RegisterBizData.class);
						_this.startActivity(i);

					} else {
						// proceed to checking accounts
						// which boils down to registering device to communicate
						// with GCM servers
						Toast.makeText(_this, "going to register ",
								Toast.LENGTH_LONG).show();
						checkAccountManager();

					}

				} else {

					String dialogTitle = getResources().getString(
							R.string.no_internet_connection);
					String dialogBody = getResources().getString(
							R.string.enable_data_or_leave_airplane_mode);
					String negativeBtnLabel = getResources().getString(
							R.string.cancel);
					String positiveBtnLabel = getResources().getString(
							R.string.settings);
					final String settingArea = Settings.ACTION_AIRPLANE_MODE_SETTINGS;
					promptUserToCreateGoogleAccount(dialogTitle, dialogBody,
							negativeBtnLabel, positiveBtnLabel, settingArea);

				}

			}
		});

		btn_add_biz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// checkAccountManager();
				if (isOnline()) {

					if (checkPreference()) {// device registered to receive from
											// GCM
						// servers
						SharedPreferences sp = getSharedPreferences(
								Utilities.REGISTRATION_ID_PREFERENCE, 0);
						send_deviceID_to_application_server(
								sp.getString("id", "defValue"),
								Utilities.url_send_devideID_to_applicatio_server);
						// do other stuff i.e call a new intent to do work
						Intent i = new Intent(_this, Login.class);
						_this.startActivity(i);

					} else {
						// proceed to checking accounts
						// which boils down to registering device to communicate
						// with GCM servers
						// Toast.makeText(_this,
						// "going to register ",Toast.LENGTH_LONG).show();
						checkAccountManager();

					}

				} else {

					String dialogTitle = getResources().getString(
							R.string.no_internet_connection);
					String dialogBody = getResources().getString(
							R.string.enable_data_or_leave_airplane_mode);
					String negativeBtnLabel = getResources().getString(
							R.string.cancel);
					String positiveBtnLabel = getResources().getString(
							R.string.settings);
					final String settingArea = Settings.ACTION_AIRPLANE_MODE_SETTINGS;
					promptUserToCreateGoogleAccount(dialogTitle, dialogBody,
							negativeBtnLabel, positiveBtnLabel, settingArea);

				}

			}
		});

		btn_search_places.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isOnline()) {

					if (checkPreference()) {// device registered to receive from
											// GCM
						// servers
						SharedPreferences sp = getSharedPreferences(
								Utilities.REGISTRATION_ID_PREFERENCE, 0);
						send_deviceID_to_application_server(
								sp.getString("id", "defValue"),
								Utilities.url_send_devideID_to_applicatio_server);
						// do other stuff i.e call a new intent to do work
						Intent i = new Intent(_this, AroundMe.class);
						_this.startActivity(i);

					} else {
						// proceed to checking accounts
						// which boils down to registering device to communicate
						// with GCM servers
						// Toast.makeText(_this,
						// "going to register ",Toast.LENGTH_LONG).show();
						checkAccountManager();

					}

				} else {

					String dialogTitle = getResources().getString(
							R.string.no_internet_connection);
					String dialogBody = getResources().getString(
							R.string.enable_data_or_leave_airplane_mode);
					String negativeBtnLabel = getResources().getString(
							R.string.cancel);
					String positiveBtnLabel = getResources().getString(
							R.string.settings);
					final String settingArea = Settings.ACTION_AIRPLANE_MODE_SETTINGS;
					promptUserToCreateGoogleAccount(dialogTitle, dialogBody,
							negativeBtnLabel, positiveBtnLabel, settingArea);

				}

			}
		});

		btn_search_biz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				if (isOnline()) {

					if (checkPreference()) {// device registered to receive from
											// GCM
						// servers
						SharedPreferences sp = getSharedPreferences(
								Utilities.REGISTRATION_ID_PREFERENCE, 0);
						send_deviceID_to_application_server(
								sp.getString("id", "defValue"),
								Utilities.url_send_devideID_to_applicatio_server);
						// do other stuff i.e call a new intent to do work
						 

						searchBiz();

					} else {
						// proceed to checking accounts
						// which boils down to registering device to communicate
						// with GCM servers
						// Toast.makeText(_this,
						// "going to register ",Toast.LENGTH_LONG).show();
						checkAccountManager();

					}

				} else {

					String dialogTitle = getResources().getString(
							R.string.no_internet_connection);
					String dialogBody = getResources().getString(
							R.string.enable_data_or_leave_airplane_mode);
					String negativeBtnLabel = getResources().getString(
							R.string.cancel);
					String positiveBtnLabel = getResources().getString(
							R.string.settings);
					final String settingArea = Settings.ACTION_AIRPLANE_MODE_SETTINGS;
					promptUserToCreateGoogleAccount(dialogTitle, dialogBody,
							negativeBtnLabel, positiveBtnLabel, settingArea);

				}

			}
		});

		btn_about.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// // TODO Auto-generated method stub
				Intent intent = new Intent(_this, Help_options.class);
				_this.startActivity(intent);

			}
		});

	}

	public void searchBiz() {

		Thread trd = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Looper.prepare();
				String url = Utilities.url_searchBiz;

				Log.e("Registration", "gone to connectin class with url " + url);

				HttpResponse response = null;
				// code to do the HTTP request
				InputStream is = null;
				String result;
				Log.d("res", "in addListenerToButton func 5");
				// Creating HTTP client
				final HttpParams httpParams = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParams, 12000);
				Log.d("res", "in addListenerToButton func 6");

				HttpClient httpClient = new DefaultHttpClient(httpParams);
				Log.d("res", "in addListenerToButton func 7");

				// Creating HTTP Post
				HttpPost httpPost = new HttpPost(url);

				Log.d("res", "in addListenerToButton func 8");
				// List<NameValuePair> nameValuePair = new
				// ArrayList<NameValuePair>(3);
				Log.d("res", "in addListenerToButton func 7");

				// nameValuePair.add(new BasicNameValuePair("email", email));
				// nameValuePair.add(new BasicNameValuePair("name", name));
				// nameValuePair.add(new BasicNameValuePair("pDescription",
				// pDescription));

				Log.d("res", "in addListenerToButton func 9");
				//
				// try {
				// httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
				// } catch (UnsupportedEncodingException e) {
				// // writing error to Log
				// e.printStackTrace();
				// }

				try {
					response = httpClient.execute(httpPost);

					HttpEntity entity = response.getEntity();

					is = entity.getContent();

					// writing response to log
					Log.d("res", "1 " + response.toString());

				} catch (ConnectTimeoutException ctEx) {

					Toast.makeText(getApplicationContext(),
							"Server not responding", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					// writing exception to log
					Log.d("res", "2 " + e.toString());
					// e.printStackTrace();
				}

				try {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(is, "iso-8859-1"), 8);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line + "\n");
					}
					is.close();

					result = sb.toString();
					String[] arrayOutput = generateArray(result, ",");
					Intent searchBiz = new Intent(_this, SearchBiz.class);
					searchBiz.putExtra("bizList", arrayOutput);
					
					_this.startActivity(searchBiz);
					 
					pDialog.dismiss();

					Log.d("res", "4" + result);
					// call the method that will return a
					// value

				} catch (Exception e) {
					Log.e("log_tag", "Error converting result " + e.toString());
				}
				Looper.loop();

			}

		});

		trd.start();
		openDialog();

	}

	private Handler closeHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (pDialog != null)
				pDialog.dismiss();
		}
	};

	public void openDialog() {
		// Open the dialog
		pDialog = new ProgressDialog(AddBizDashboardActivity.this);
		pDialog.setMessage(Html
				.fromHtml("<b>Please wait...</b><br/>Fetching business details"));
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();

		// Close it after 2 seconds
		closeHandler.sendEmptyMessageDelayed(0, 10000);
	}

	public String[] generateArray(String original, String separator) {

		Vector<String> nodes = new Vector<String>();

		// Parse nodes into vector
		int index = original.indexOf(separator);
		while (index >= 0) {
			nodes.addElement(original.substring(0, index));
			original = original.substring(index + separator.length());
			index = original.indexOf(separator);
		}
		// Get the last node
		nodes.addElement(original);

		// Create splitted string array
		String[] result = new String[nodes.size() - 1];

		if (nodes.size() > 0) {
			for (int loop = 0; loop < nodes.size() - 1; loop++) {
				result[loop] = (String) nodes.elementAt(loop);
				// System.out.println(result[loop]); - uncomment this line to
				// see the result on output console
			}
		}

		return result;

	}

	public void send_deviceID_to_application_server(String registrationID,
			String url) {
		/*
		 * we need deviceID device email address
		 */
		Log.e("Registration", "has gone to register to application server");

		Hashtable<String, String> hTable = new Hashtable<String, String>();
		hTable.put("email", firstGoogleMailAccount());
		hTable.put("regID", registrationID);

		CommonConnectionClass conObject = new CommonConnectionClass(
				AddBizDashboardActivity.this);

		conObject.commonConnectionMethod(hTable, url);

	}

	public boolean checkPreference() {
		Log.e("Registration", "preference checking starting..........");

		SharedPreferences sp = getSharedPreferences(
				Utilities.REGISTRATION_ID_PREFERENCE, 0);
		String stored_id = sp.getString("id", "noValue");

		if (stored_id.equals("noValue")) {
			return false;
		} else {

			return true;

		}

	}

	/**
	 * This is the method that registers a device if it is not registered it is
	 * called when there is atleast one user (gool mail) account to be used to
	 * identify the user
	 * 
	 * @param no
	 *            parameter
	 * @return void
	 */
	private void registerWithGCM() {
		// promptUserToCreateGoogleAccount("going to gcm","nor registers"
		// ,"fdsfgd","ssdf");
		Toast.makeText(_this, "going to register ", Toast.LENGTH_LONG).show();

		Intent registrationIntent = new Intent(
				"com.google.android.c2dm.intent.REGISTER");
		// // sets the app name in the intent
		registrationIntent.putExtra("app",
				PendingIntent.getBroadcast(_this, 0, new Intent(), 0));
		registrationIntent.putExtra("sender", SENDER_ID);
		_this.startService(registrationIntent);
		Log.e("Registration", "reaching gcm");

	}

	/**
	 * Determines whether a user has atleast one google mail account if not He
	 * is prompted to create one by directing the user to user settings this
	 * method is fired if internet is present
	 * 
	 * @param no
	 * @return void
	 */

	private void checkAccountManager() {
		// Log.v("account", "There is no google account");

		if (isOnline()) {
			try {

				acm = AccountManager.get(_this);

				Account[] accounts = acm.getAccountsByType("com.google");

				// if no account has been registered, prompt the user to do so
				if (accounts.length == 0) {
					// propt user to do so by asking him to create one
					// Log.e("account", "There is no google account");
					String dialogTitle = getResources().getString(
							R.string.no_gmail_account_found);
					String dialogBody = getResources().getString(
							R.string.create_gmail_account);
					String negativeBtnLabel = getResources().getString(
							R.string.cancel);
					String positiveBtnLabel = getResources().getString(
							R.string.settings);
					final String settingArea = Settings.ACTION_ADD_ACCOUNT;

					promptUserToCreateGoogleAccount(dialogTitle, dialogBody,
							negativeBtnLabel, positiveBtnLabel, settingArea);
				} else {
					registerWithGCM();
					// read preference
					// regisgter device
					// move to next screen

				}

			} catch (Exception e) {
				Log.e("error", e.toString());
			}

		} else {// Prompt the user to enable data, or leave airplane mode

			String dialogTitle = getResources().getString(
					R.string.no_internet_connection);
			String dialogBody = getResources().getString(
					R.string.enable_data_or_leave_airplane_mode);
			String negativeBtnLabel = getResources().getString(R.string.cancel);
			String positiveBtnLabel = getResources().getString(
					R.string.settings);
			final String settingArea = Settings.ACTION_AIRPLANE_MODE_SETTINGS;
			promptUserToCreateGoogleAccount(dialogTitle, dialogBody,
					negativeBtnLabel, positiveBtnLabel, settingArea);

		}

	}

	/**
	 * Returns the first google mail account in that device
	 * 
	 * @param void
	 * @return USER_GOOGLE_MAIL
	 */

	public String firstGoogleMailAccount() {

		acm = AccountManager.get(_this);

		Account[] accounts = acm.getAccountsByType("com.google");

		if (accounts.length > 0) {

			USER_GOOGLE_MAIL = accounts[0].name.toString();
		}

		return USER_GOOGLE_MAIL;

	}

	/**
	 * Find out whether your device is data enable
	 * 
	 * @param no
	 * @return boolean
	 */

	public boolean isOnline() {

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		if (netInfo != null && netInfo.isConnectedOrConnecting()) {

			return true;
		}
		return false;
	}

	/**
	 * Prompts the user to create a google account by asking to navigate to
	 * account settings
	 * 
	 * @param AlertDialog
	 *            title
	 * 
	 * @param AlertDialog
	 *            Body
	 * @param negative
	 *            button label
	 * @param positive
	 *            button label
	 * @return void
	 */

	private void promptUserToCreateGoogleAccount(String title, String body,
			String negativeButtonLabel, String positiveButtonLabbel,
			final String settingsArea) {
		// setResult(account);
		AlertDialog.Builder settingsAlert = new AlertDialog.Builder(_this);

		// Setting Dialog Title
		settingsAlert.setTitle(title);
		// Setting Dialog Message
		settingsAlert.setMessage(body);
		// On pressing Settings button
		settingsAlert.setPositiveButton(positiveButtonLabbel,
				new DialogInterface.OnClickListener() {
					// On pressing Settings button
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(settingsArea);

						_this.startActivity(intent);

					}
				});

		// on pressing cancel button
		settingsAlert.setNegativeButton(negativeButtonLabel,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		settingsAlert.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_biz_dashboard, menu);
		return true;
	}

}
