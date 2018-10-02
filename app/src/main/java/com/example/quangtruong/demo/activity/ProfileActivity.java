package com.example.quangtruong.demo.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quangtruong.demo.R;
import com.example.quangtruong.demo.Utils;
import com.example.quangtruong.demo.constant.Constant;
import com.example.quangtruong.demo.object.UserObject;

public class ProfileActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        if (Constant.Theme.THEME_DEFAULT.equals(Utils.getTheme(context)))
            setContentView(R.layout.activity_profile);
        else
            setContentView(R.layout.activity_profile_purple);

        installView();
    }

    private void installView() {
        installToolbar();

        rlToolbar.setBackgroundColor(Utils.getColorPrimary(context));
        tvTitleToolbar.setText(getString(R.string.profile));
        TextView tvFullName = (TextView) findViewById(R.id.tv_fullname_user);
        UserObject userObject = databaseHandler.getUser();
        if (userObject != null)
            tvFullName.setText(Utils.format(Constant.FormatString.FORMAT_MSG, userObject.getLastName(), userObject.getFirstName()));
        else
            tvFullName.setText("Dương Quang Trường");
    }
}
