package com.sparkle.webservice;

import java.net.InetAddress;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button _startStop_button = null;
	private EditText _root_text = null;
	private TextView _ip_text = null;
	private EditText _port_text = null;
	private MyLog _myLog = new MyLog(getClass().getName());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		Init();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		UiUpdater.registerClient(_handler);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		UiUpdater.registerClient(_handler);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		UiUpdater.unregisterClient(_handler);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		UiUpdater.unregisterClient(_handler);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		UiUpdater.unregisterClient(_handler);
	}

	private void Init() {

		FetchUIControls();
		BindingEvnents();
		InitUI();
	}

	private void FetchUIControls() {
		_startStop_button = (Button) findViewById(R.id.startStop_button);
		_root_text = (EditText) findViewById(R.id.root_text);
		_ip_text = (TextView) findViewById(R.id.ip_text);
		_port_text = (EditText) findViewById(R.id.port_text);
	}

	private void BindingEvnents() {
		if (_startStop_button != null) {
			_startStop_button.setOnClickListener(new ClickListener());
		}

	}

	private void InitUI() {
		if (_root_text != null) {
			_root_text.setText(Defaults.getRoot());			
		}

		if (_port_text != null) {
			_port_text.setText(String.format("%1d",Defaults.getPort()));
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler _handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGES.UPDATE_UI:
				UpdateUi();
				break;

			default:
				break;
			}
		}
	};

	public void UpdateUi() {

		if (WebServer.isRunning()) {
			_myLog.l(Log.DEBUG, "updateUi: server is running", true);

			_startStop_button.setText(R.string.stop);
			_root_text.setEnabled(false);			
			_port_text.setEnabled(false);
			_root_text.setInputType(InputType.TYPE_NULL);
			_port_text.setInputType(InputType.TYPE_NULL);

			InetAddress address = WebServer.getWifiIp(MainActivity.this);

			if (address != null) {

				_ip_text.setText("http://" + address.getHostAddress() + ":"
						+ WebServer.getPort() + "/");
			} else {
				_myLog.l(Log.VERBOSE, "Null address from getServerAddress()",
						true);
				_ip_text.setText(R.string.can_not_get_ip);
			}
		} else {

			_startStop_button.setText(R.string.start);
			_ip_text.setText(R.string.can_not_get_ip);
			_root_text.setEnabled(true);			
			_port_text.setEnabled(true);	
			_root_text.setInputType(InputType.TYPE_CLASS_TEXT);
			_port_text.setInputType(InputType.TYPE_CLASS_NUMBER);
		}	

	}

	private class ClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.startStop_button: {
				StartOrStop();
				break;
			}
			default: {
				break;
			}
			}
		}

		private void StartOrStop() {

			try {

				String startString = getString(R.string.start);
				String stopString = getString(R.string.stop);
				String buttonText = _startStop_button.getText().toString();

				if (buttonText.equals(startString)) {
					Start();
				} else if (buttonText.equals(stopString)) {
					Stop();
				} else {
					// Do nothing
					_myLog.l(Log.ERROR, "Unrecognized start/stop text");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		private void Start() {
			InitSetting();
			StartListen();
		}

		private void InitSetting() {
			Defaults.setRoot(_root_text.getText().toString());
			Defaults.setPort(Integer.parseInt(_port_text.getText().toString()));
		}

		private void StartListen() {

			Context context = getApplicationContext();
			WebServer.Start(context);

		}

		private void Stop() {

			Context context = getApplicationContext();
			WebServer.Stop(context);

		}

	}

}
