package com.hhwit.liaisonshome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class MyButtonView extends View {
    private int mLiaisonsColor1 = Color.WHITE;
    private int mLiaisonsColor2 = Color.GRAY;
    private Drawable mLiaisonsDrawable;

    private OnClickListener mListener;
    private int mCurrentColor = mLiaisonsColor1;

    public MyButtonView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyButtonView, defStyle, 0);

        mLiaisonsColor1 = a.getColor(
                R.styleable.MyButtonView_buttonColor1,
                mLiaisonsColor1);

        mLiaisonsColor2 = a.getColor(
                R.styleable.MyButtonView_buttonColor2,
                mLiaisonsColor2);

        if (a.hasValue(R.styleable.MyButtonView_buttonDrawable)) {
            mLiaisonsDrawable = a.getDrawable(
                    R.styleable.MyButtonView_buttonDrawable);
            mLiaisonsDrawable.setCallback(this);
        }

        a.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        setBackgroundColor(mCurrentColor);

        if (mLiaisonsDrawable != null) {
            mLiaisonsDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mLiaisonsDrawable.draw(canvas);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mCurrentColor = mLiaisonsColor2;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mCurrentColor = mLiaisonsColor1;
                if (mListener != null)
                    mListener.onClick(this);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mListener = l;
    }

}
