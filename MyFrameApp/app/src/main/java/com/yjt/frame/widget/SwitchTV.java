package com.yjt.frame.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yjt.frame.R;

/**
 * Created by yujiangtao on 16/3/31.
 */
public class SwitchTV extends RelativeLayout implements View.OnClickListener {
    public Button upBtn = null;
    public Button downBtn = null;
    SwitchClickCallback callback=null;
    public interface SwitchClickCallback{
        void callback(int p);
    }
    public void setSwitchClickCallback(SwitchClickCallback call){
        this.callback = call;
    }
    public SwitchTV(Context context) {
        this(context, null);
    }

    public SwitchTV(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_switch_text, this);
        downBtn = (Button) findViewById(R.id.down_btn);
        upBtn = (Button) findViewById(R.id.up_btn);
        downBtn.setOnClickListener(this);
        upBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_btn:
                if (upBtn.isEnabled()) {
                    upBtn.setEnabled(false);
                    downBtn.setEnabled(true);
                    this.callback.callback(0);
                }
                break;
            case R.id.down_btn:
                if (downBtn.isEnabled()) {
                    upBtn.setEnabled(true);
                    downBtn.setEnabled(false);
                    this.callback.callback(1);
                }
                break;
        }
    }
}
