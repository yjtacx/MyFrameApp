package com.yjt.frame.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yjt.frame.R;


public class SetItemView extends LinearLayout{
	private ImageView image_set;
	private TextView tv_set;
	public SetItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SetItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.set_item_layout, this);
		image_set=(ImageView) findViewById(R.id.set_item_image);
		tv_set = (TextView) findViewById(R.id.set_item_tv);
		image_set.setImageResource(attrs.getAttributeResourceValue(null,
				"imgres", 0));
		tv_set.setText(attrs.getAttributeResourceValue(null,"value", 0));
	}
	

}
