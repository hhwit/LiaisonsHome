package com.hhwit.liaisonshome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Hongwen Huang on 2018/10/10
 * Email: hhwit@126.com
 */

public class MultiImageView extends View {
    private Drawable mDrawable1;
    private Drawable mDrawable2;
    private Drawable mDrawable3;
    private Drawable mDrawable4;

    private Drawable curDrawable;


    public MultiImageView(Context context) {
        super(context);
        init(null, 0);
    }

    public MultiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MultiImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MultiImageView, defStyle, 0);

        if (a.hasValue(R.styleable.MultiImageView_multiDrawable1)) {
            mDrawable1 = a.getDrawable(
                    R.styleable.MultiImageView_multiDrawable1);
            mDrawable1.setCallback(this);
        }

        if (a.hasValue(R.styleable.MultiImageView_multiDrawable2)) {
            mDrawable2 = a.getDrawable(
                    R.styleable.MultiImageView_multiDrawable2);
            mDrawable2.setCallback(this);
        }

        if (a.hasValue(R.styleable.MultiImageView_multiDrawable3)) {
            mDrawable3 = a.getDrawable(
                    R.styleable.MultiImageView_multiDrawable3);
            mDrawable3.setCallback(this);
        }

        if (a.hasValue(R.styleable.MultiImageView_multiDrawable4)) {
            mDrawable4 = a.getDrawable(
                    R.styleable.MultiImageView_multiDrawable4);
            mDrawable4.setCallback(this);
        }

        curDrawable = mDrawable1;

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

        if (curDrawable != null) {
            curDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            curDrawable.draw(canvas);
        }
    }

    public void setImage(int index) {
        switch (index) {
            case 1:
                curDrawable = mDrawable1;
                break;
            case 2:
                curDrawable = mDrawable2;
                break;
            case 3:
                curDrawable = mDrawable3;
                break;
            case 4:
                curDrawable = mDrawable4;
                break;
        }
        invalidate();
    }

}
