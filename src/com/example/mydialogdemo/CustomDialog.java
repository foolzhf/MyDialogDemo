package com.example.mydialogdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CustomDialog extends Dialog {

	private Context context;
	/**
	 * 选项的名称集合
	 */
	private String[] items;
	/**
	 * 对话框标题
	 */
	private String title;
	/**
	 * 初始的选中的位置
	 */
	private int defaultSelected;
	/**
	 * 选择监听
	 */
	private OnSelectChangedListener selectChangedListener;
	/**
	 * 选中的位置
	 */
	private int selected;
	private ListView list;

	private List<String> data;

	private ArrayAdapter<String> adapter;

	public CustomDialog(Context context, String[] items, String title,
			int defaultSelected, OnSelectChangedListener selectChangedListener) {
		// TODO Auto-generated constructor stub
		super(context, R.style.MyDialog);
		this.title = title;
		this.context = context;
		this.items = items;
		this.defaultSelected = defaultSelected;
		this.selectChangedListener = selectChangedListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.mydialog);
		list = (ListView) findViewById(R.id.list);
		selected = defaultSelected;
		setDefault();
		showItems();
	}

	public void setDefault() {
		data = new ArrayList<String>();
		for (int i = 0; i < items.length; i++) {
			data.add(items[i]);
		}
		adapter = new ArrayAdapter<String>(context, R.layout.item,
				R.id.name_tv, data);
		list.setAdapter(adapter);
		// adapter异步加载，在setAdapter之后getCount不能立马获取到
		list.post(new Runnable() {

			@Override
			public void run() {
				int m;
				System.out.println(list.getCount());
				for (m = 0; m < list.getCount(); m++) {
					if (m == defaultSelected) {
						View viewx = list.getChildAt(m);
						TextView checkx = (TextView) viewx
								.findViewById(R.id.check_tv);
						TextView name = (TextView) viewx
								.findViewById(R.id.name_tv);
						checkx.setVisibility(View.VISIBLE);
						break;
					}
				}
			}
		});
	}

	private void showItems() {
		this.setTitle(title);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				for (int i = 0; i < list.getCount(); i++) {
					View viewx = list.getChildAt(i);
					TextView checkx = (TextView) viewx
							.findViewById(R.id.check_tv);
					checkx.setVisibility(View.GONE);
				}

				TextView check = (TextView) view.findViewById(R.id.check_tv);
				check.setVisibility(View.VISIBLE);
				selected = position;
				dismiss();
				selectChangedListener.notifySelectChanged(selected);
			}
		});
	}

	public int getSelected() {
		return selected;
	}

	public interface OnSelectChangedListener {
		public void notifySelectChanged(int changeFlag);
	}
}