package com.example.quangtruong.demo.animation;

import android.animation.Animator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.quangtruong.demo.R;

public class AnimationCustom {

    public static void animateRevealColor(Context context, View viewRoot, Drawable drawable, int color) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            if (drawable == null)
//                animateRevealColorFromCoordinates(context, viewRoot, color, cx, cy);
//            else
//                animateRevealDrawableFromCoordinates(context, viewRoot, drawable, color, cx, cy);
//        }
        viewRoot.setBackground(drawable);
    }

    private static void animateRevealColorFromCoordinates(Context context, View viewRoot,
                                                          int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
            viewRoot.setBackgroundColor(color);
            anim.setDuration(context.getResources().getInteger(R.integer.default_animation));
            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.start();
        }
    }

    private static void animateRevealDrawableFromCoordinates(Context context, final View viewRoot, final Drawable drawable, int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
            viewRoot.setBackgroundColor(color);
            anim.setDuration(context.getResources().getInteger(R.integer.default_animation));
            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    viewRoot.setBackground(drawable);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();
        }
    }
}
