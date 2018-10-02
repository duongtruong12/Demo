package com.example.quangtruong.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.quangtruong.demo.R;
import com.example.quangtruong.demo.Utils;

public class ProgressBarDialog extends Dialog {
    @Override
    public void onBackPressed() {
    }

    public ProgressBarDialog(Context context) {
        super(context);
        Window window = this.getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_progess_bar);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(lp);
            window.getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            setCanceledOnTouchOutside(false);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ProgressBar progressbar = (ProgressBar) findViewById(R.id.loading_spinner);
                progressbar.setIndeterminateTintList(ColorStateList.valueOf(Utils.getColorPrimary(context)));
            }
        }
    }
}
