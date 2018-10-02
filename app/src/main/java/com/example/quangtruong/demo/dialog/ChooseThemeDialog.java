package com.example.quangtruong.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.quangtruong.demo.R;
import com.example.quangtruong.demo.Utils;
import com.example.quangtruong.demo.animation.AnimationCustom;
import com.example.quangtruong.demo.constant.Constant;

public class ChooseThemeDialog extends Dialog implements CompoundButton.OnCheckedChangeListener {
    private RadioButton rbBlue, rbPurple;
    private Button btnOk;
    private String strTheme;
    private Context context;
    private OnChooseThemeListener onChooseThemeListener;

    public interface OnChooseThemeListener {
        void onChooseTheme(String theme);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 || super.onKeyDown(keyCode, event);
    }

    public ChooseThemeDialog(Context context, OnChooseThemeListener onChooseThemeListener) {
        super(context);
        this.context = context;
        this.onChooseThemeListener = onChooseThemeListener;
        Window window = this.getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_choose_theme);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
            window.getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            setCanceledOnTouchOutside(false);
            installView();
        }
    }

    private void installView() {
        rbBlue = (RadioButton) findViewById(R.id.rb_blue);
        rbPurple = (RadioButton) findViewById(R.id.rb_purple);

        btnOk = (Button) findViewById(R.id.btn_ok);

        rbBlue.setOnCheckedChangeListener(this);
        rbPurple.setOnCheckedChangeListener(this);
        strTheme = Utils.getTheme(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int color = Utils.getColorPrimary(context);
            rbPurple.setButtonTintList(ColorStateList.valueOf(color));
            rbBlue.setButtonTintList(ColorStateList.valueOf(color));
        }
        if (Constant.Theme.THEME_DEFAULT.equals(strTheme)) {
            rbBlue.setChecked(true);
        } else {
            rbPurple.setChecked(true);
        }
        btnOk.setBackground(Utils.getButtonBackground(context));
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onChooseThemeListener != null)
                    onChooseThemeListener.onChooseTheme(strTheme);
            }
        });
    }

    private void setBackground() {
        Utils.saveTheme(context, strTheme);
        int color = Utils.getColorPrimary(context);
        AnimationCustom.animateRevealColor(context, btnOk, Utils.getButtonBackground(context), color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rbPurple.setButtonTintList(ColorStateList.valueOf(color));
            rbBlue.setButtonTintList(ColorStateList.valueOf(color));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.rb_blue:
                if (b) {
                    if (!Constant.Theme.THEME_DEFAULT.equals(strTheme)) {
                        strTheme = Constant.Theme.THEME_DEFAULT;
                        setBackground();
                    }
                }
                break;
            case R.id.rb_purple:
                if (b) {
                    if (!Constant.Theme.THEME_PURPLE.equals(strTheme)) {
                        strTheme = Constant.Theme.THEME_PURPLE;
                        setBackground();
                    }
                }
                break;
        }
    }
}
