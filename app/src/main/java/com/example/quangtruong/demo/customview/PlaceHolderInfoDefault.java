package com.example.quangtruong.demo.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quangtruong.demo.Utils;

public class PlaceHolderInfoDefault extends android.support.v7.widget.AppCompatAutoCompleteTextView implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {
    private ImageView imgError;
    private TextView tvError;
    private String message;
    private boolean isError;
    private Context context;

    public void setError(boolean error) {
        isError = error;
    }

    public enum Location {
        LEFT(0), RIGHT(2);

        final int idx;

        Location(int idx) {
            this.idx = idx;
        }
    }

    public interface OnClearTextListener {
        void didClearText();

        void willClearText();
    }

    public interface OnDrawableLeftClick {
        void onClick();
    }

    public PlaceHolderInfoDefault(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PlaceHolderInfoDefault(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public PlaceHolderInfoDefault(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public void setOnClearTextListener(OnClearTextListener onClearTextListener) {
        this.onClearTextListener = onClearTextListener;
    }

    /**
     * null disables the icon
     */
    public void setImageViewError(ImageView img) {
        this.imgError = img;
    }

    public void setTextViewError(TextView img, String message) {
        this.tvError = img;
        this.message = message;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        this.l = l;
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener f) {
        this.f = f;
    }

    private Location loc = Location.RIGHT;

    private Drawable xD;
    private OnClearTextListener onClearTextListener;


    private Location locLeft = Location.LEFT;
    private OnDrawableLeftClick onDrawableLeftClick;
    private OnTouchListener l;
    private OnFocusChangeListener f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getDisplayedDrawable() != null) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int left = (loc == Location.LEFT) ? 0 : getWidth() - getPaddingRight() - xD.getIntrinsicWidth();
            int right = (loc == Location.LEFT) ? getPaddingLeft() + xD.getIntrinsicWidth() : getWidth();
            boolean tappedX = x >= left && x <= right && y >= 0 && y <= (getBottom() - getTop());
            if (tappedX) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (onClearTextListener != null) {
                        onClearTextListener.willClearText();
                    }
                    setText("");
                    if (onClearTextListener != null) {
                        onClearTextListener.didClearText();
                    }
                }
                return true;
            }
            if (isFocused()) {
                int left2 = (locLeft == Location.LEFT) ? 0 : getWidth() - getPaddingRight() - xD.getIntrinsicWidth();
                int right2 = (locLeft == Location.LEFT) ? getPaddingLeft() + xD.getIntrinsicWidth() : getWidth();
                boolean tappedX2 = x >= left2 && x <= right2 && y >= 0 && y <= (getBottom() - getTop());
                if (tappedX2) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (onDrawableLeftClick != null) {
                            onDrawableLeftClick.onClick();
                            clearFocus();
                        }
                    }
                    return true;
                }
            }
        }
        return l != null && v != null && l.onTouch(v, event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(!Utils.isNullOrEmpty(getText().toString()));
        } else {
            setClearIconVisible(false);
        }
        if (f != null) {
            f.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public void onTextChanged(EditText view, String text) {
        if (isFocused()) {
            setClearIconVisible(!Utils.isNullOrEmpty(text));
            if (isError) {
                Utils.setDefault(context, this, tvError, imgError, message);
                isError = false;
            }
        }
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable
            bottom) {
        super.setCompoundDrawables(left, top, right, bottom);
        initIcon();
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        initIcon();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcherAdapter(this, this));
        initIcon();
        setClearIconVisible(false);
    }

    private void initIcon() {
        xD = null;
        if (loc != null) {
            xD = getCompoundDrawables()[loc.idx];
        }
        if (xD == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                xD = getResources().getDrawable(android.R.drawable.presence_offline, null);
            } else {
                xD = getResources().getDrawable(android.R.drawable.presence_offline);
            }

        }
        xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
        int min = getPaddingTop() + xD.getIntrinsicHeight() + getPaddingBottom();
        if (getSuggestedMinimumHeight() < min) {
            setMinimumHeight(min);
        }
    }

    private Drawable getDisplayedDrawable() {
        return (loc != null) ? getCompoundDrawables()[loc.idx] : null;
    }

    protected void setClearIconVisible(boolean visible) {
        Drawable[] cd = getCompoundDrawables();
        Drawable displayed = getDisplayedDrawable();
        boolean wasVisible = (displayed != null);
        if (visible != wasVisible) {
            Drawable x = visible ? xD : null;
            super.setCompoundDrawables((loc == Location.LEFT) ? x : cd[0], cd[1], (loc == Location.RIGHT) ? x : cd[2],
                    cd[3]);
        }
    }

    public void setOnDrawableLeftClick(OnDrawableLeftClick onDrawableLeftClick) {
        this.onDrawableLeftClick = onDrawableLeftClick;
    }
}