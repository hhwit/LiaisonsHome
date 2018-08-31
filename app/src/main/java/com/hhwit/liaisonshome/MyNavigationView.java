package com.hhwit.liaisonshome;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class MyNavigationView extends LinearLayout {
    Context mContext;

    public MyNavigationView(Context context) {
        super(context);
        mContext = context;
        init(null, 0);
    }

    public MyNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs, 0);
    }

    public MyNavigationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater.from(mContext).inflate(R.layout.my_navigation, this,false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
