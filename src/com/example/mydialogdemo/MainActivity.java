package com.example.mydialogdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.mydialogdemo.CustomDialog.OnSelectChangedListener;

public class MainActivity extends Activity implements OnClickListener {
	private Button showDialog;
	private CustomDialog dialog;
	private Activity activity = this;
	private String[] items = { "北京", "上海", "南京", "广州" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	public void init() {
		showDialog = (Button) findViewById(R.id.button1);
		showDialog.setOnClickListener(this);
		dialog = new CustomDialog(this, items, "城市", 0,
				new OnSelectChangedListener() {

					@Override
					public void notifySelectChanged(int changeFlag) {
						// TODO Auto-generated method stub
						Toast.makeText(activity, items[changeFlag],
								Toast.LENGTH_SHORT).show();
					}
				});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.button1) {
			dialog.show();
		}
	}
}
