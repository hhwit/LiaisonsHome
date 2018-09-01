package com.hhwit.liaisonshome;

import android.annotation.SuppressLint;
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

public class NavigationIconView extends View {

    private OnClickListener mListener;
    private int mLiaisonsColor1 = Color.GRAY;
    private int mLiaisonsColor2 = Color.BLUE;
    private Drawable mLiaisonsDrawable1;
    private Drawable mLiaisonsDrawable2;
    private String mLiaisonsString;
    private float mLiaisonsDimension = 0;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private boolean mDotFlag = false;
    private int currentColor = mLiaisonsColor1;
    private Drawable currentDrawable;

    public NavigationIconView(Context context) {
        super(context);
        init(null, 0);
    }

    public NavigationIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public NavigationIconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.NavigationIconView, defStyle, 0);

        mLiaisonsColor1 = a.getColor(
                R.styleable.NavigationIconView_liaisonsColor1,
                mLiaisonsColor1);

        mLiaisonsColor2 = a.getColor(
                R.styleable.NavigationIconView_liaisonsColor2,
                mLiaisonsColor2);

        currentColor = mLiaisonsColor1;

        if (a.hasValue(R.styleable.NavigationIconView_liaisonsDrawable1)) {
            mLiaisonsDrawable1 = a.getDrawable(
                    R.styleable.NavigationIconView_liaisonsDrawable1);
            mLiaisonsDrawable1.setCallback(this);
        }

        if (a.hasValue(R.styleable.NavigationIconView_liaisonsDrawable2)) {
            mLiaisonsDrawable2 = a.getDrawable(
                    R.styleable.NavigationIconView_liaisonsDrawable2);
            mLiaisonsDrawable2.setCallback(this);
        }

        currentDrawable = mLiaisonsDrawable1;

        mLiaisonsString = a.getString(
                R.styleable.NavigationIconView_liaisonsString);

        mLiaisonsDimension = a.getDimension(
                R.styleable.NavigationIconView_liaisonsDimension,
                mLiaisonsDimension);

        a.recycle();

        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mLiaisonsDimension);
        mTextWidth = mTextPaint.measureText(mLiaisonsString);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int totalWidth = getWidth();
        int totalHeight = getHeight();

        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int contentHeight = totalHeight - paddingTop - paddingBottom;
        int imageWidth = contentHeight * 3 / 5;
        int textHeight = contentHeight - imageWidth;

        mTextPaint.setColor(currentColor);
        canvas.drawText(mLiaisonsString,
                (totalWidth - mTextWidth) / 2,
                paddingTop + imageWidth + (textHeight + mTextHeight) / 2,
                mTextPaint);

        if (currentDrawable != null) {
            currentDrawable.setBounds((totalWidth - imageWidth) / 2, paddingTop,
                    (totalWidth + imageWidth) / 2, paddingTop + imageWidth);
            currentDrawable.setTint(currentColor);
            currentDrawable.draw(canvas);
        }

        if (mDotFlag) {
            @SuppressLint("DrawAllocation") Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle((totalWidth + imageWidth) / 2  + imageWidth - imageWidth * 6 / 8,
                    paddingTop + imageWidth * 2 / 8,
                    imageWidth / 8, paint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
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

    public void setFirstColor() {
        currentColor = mLiaisonsColor1;
        invalidate();
    }

    public void setSecondColor() {
        currentColor = mLiaisonsColor2;
        invalidate();
    }

    public void showFirstIcon() {
        currentDrawable = mLiaisonsDrawable1;
        invalidate();
    }

    public void showSecondIcon() {
        currentDrawable = mLiaisonsDrawable2;
        invalidate();
    }

    public void showDot() {
        mDotFlag = true;
        invalidate();
    }

    public void hideDot() {
        mDotFlag = false;
        invalidate();
    }
}
