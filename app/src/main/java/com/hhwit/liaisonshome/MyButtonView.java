package com.hhwit.liaisonshome;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class MyButtonView extends View {
    private int mBackgroundNormalColor = 0xFFFFFF;
    private int mBackgroundPressedColor = 0xFFFFFF;
    private int mBackgroundDisabledColor = 0xFFFFFF;
    private int mContentNormalColor = Color.WHITE;
    private int mContentPressedColor = Color.GRAY;
    private int mContentDisabledColor = Color.WHITE;
    private String mButtonText;
    private float mButtonTextSize = 0;
    private Drawable mButtonImage;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private OnClickListener mListener;
    private int mCurrentBackgroundColor = -2;
    private int mCurrentContentColor;
    private boolean isDisabled = false;

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
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyButtonView, defStyle, 0);

        if (a.hasValue(R.styleable.MyButtonView_buttonBackgroundNormal)) {
            mBackgroundNormalColor = a.getColor(
                    R.styleable.MyButtonView_buttonBackgroundNormal,
                    mBackgroundNormalColor);
        }

        if (a.hasValue(R.styleable.MyButtonView_buttonBackgroundPressed)) {
            mBackgroundPressedColor = a.getColor(
                    R.styleable.MyButtonView_buttonBackgroundPressed,
                    mBackgroundPressedColor);
        }

        if (a.hasValue(R.styleable.MyButtonView_buttonBackgroundDisabled)) {
            mBackgroundDisabledColor = a.getColor(
                    R.styleable.MyButtonView_buttonBackgroundDisabled,
                    mBackgroundDisabledColor);
        }

        if (a.hasValue(R.styleable.MyButtonView_buttonContentNormal)) {
            mContentNormalColor = a.getColor(
                    R.styleable.MyButtonView_buttonContentNormal,
                    mContentNormalColor);
        }

        if (a.hasValue(R.styleable.MyButtonView_buttonContentPressed)) {
            mContentPressedColor = a.getColor(
                    R.styleable.MyButtonView_buttonContentPressed,
                    mContentPressedColor);
        }

        if (a.hasValue(R.styleable.MyButtonView_buttonContentDisabled)) {
            mContentDisabledColor = a.getColor(
                    R.styleable.MyButtonView_buttonContentDisabled,
                    mContentDisabledColor);
        }

        if (a.hasValue(R.styleable.MyButtonView_buttonImage)) {
            mButtonImage = a.getDrawable(
                    R.styleable.MyButtonView_buttonImage);
            mButtonImage.setCallback(this);
        }

        if (a.hasValue(R.styleable.MyButtonView_buttonText)) {
            mButtonText = a.getString(
                    R.styleable.MyButtonView_buttonText);
        }

        mButtonTextSize = a.getDimension(
                R.styleable.MyButtonView_buttonTextSize,
                mButtonTextSize);

        a.recycle();

        if (mButtonText != null) {
            mTextPaint = new TextPaint();
            mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
            mTextPaint.setTextAlign(Paint.Align.LEFT);
            mTextPaint.setTextSize(mButtonTextSize);
            mTextWidth = mTextPaint.measureText(mButtonText);
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            mTextHeight = fontMetrics.top;
        }

        mCurrentBackgroundColor = mBackgroundNormalColor;
        mCurrentContentColor = mContentNormalColor;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        setBackgroundColor(mCurrentBackgroundColor);

        if (mButtonText != null) {
            mTextPaint.setColor(mCurrentContentColor);
            canvas.drawText(mButtonText,
                    paddingLeft + (contentWidth - mTextWidth) / 2,
                    paddingTop  + (contentHeight - mTextHeight / 2) / 2,
                    mTextPaint);
        }

        if (mButtonImage != null) {
            mButtonImage.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mButtonImage.setTint(mCurrentContentColor);
            mButtonImage.draw(canvas);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isDisabled) {
            return true;
        }
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mCurrentBackgroundColor = mBackgroundPressedColor;
                mCurrentContentColor = mContentPressedColor;
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                mCurrentBackgroundColor = mBackgroundNormalColor;
                mCurrentContentColor = mContentNormalColor;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mCurrentBackgroundColor = mBackgroundNormalColor;
                mCurrentContentColor = mContentNormalColor;
                invalidate();
                if (mListener != null)
                    mListener.onClick(this);
                break;
        }
        return true;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mListener = l;
    }

    public void setDisable() {
        isDisabled = true;
        mCurrentBackgroundColor = mBackgroundDisabledColor;
        mCurrentContentColor = mContentDisabledColor;
        invalidate();
    }

    public void setEnable() {
        isDisabled = false;
        mCurrentBackgroundColor = mBackgroundNormalColor;
        mCurrentContentColor = mContentNormalColor;
        invalidate();
    }

}
