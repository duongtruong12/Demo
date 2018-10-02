package com.example.quangtruong.demo.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class CustomImageView extends android.support.v7.widget.AppCompatImageView implements ValueAnimator.AnimatorUpdateListener {

    private static final int RADIUS = 20;

    private List<Point> points = new ArrayList<>();
    private Paint paint;

    private ValueAnimator mValueAnimator;

    private int mNumCirclesToDraw = 0;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(Color.RED);

        points.add(new Point(100, 100));
        points.add(new Point(200, 200));
        points.add(new Point(300, 300));
        points.add(new Point(400, 400));

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }

    public void startAnimation() {
        mValueAnimator = ValueAnimator.ofInt(0, points.size());
        mValueAnimator.setDuration(500 * points.size());
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(CustomImageView.this);
        mValueAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int val = (Integer) animation.getAnimatedValue();
        if (val != mNumCirclesToDraw) {
            mNumCirclesToDraw = val;
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!points.isEmpty()) {

            for (int i = 0; i < mNumCirclesToDraw; i++) {
                Point point = points.get(i);
                canvas.drawCircle(point.x, point.y, RADIUS, paint);
            }
        }
    }
}