package com.example.quangtruong.demo.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quangtruong.demo.R;
import com.example.quangtruong.demo.Utils;
import com.example.quangtruong.demo.adapter.ItemPagerAdapter;
import com.example.quangtruong.demo.constant.Constant;
import com.example.quangtruong.demo.customview.BottomSheetBehavior;
import com.example.quangtruong.demo.customview.CustomImageView;
import com.example.quangtruong.demo.object.UserObject;

public class HomeActivity extends CommonActivity implements NavigationView.OnNavigationItemSelectedListener, ItemPagerAdapter.mBottomAction {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private DrawerLayout drawerLayout;
    private View contentView;
    private NavigationView navView;
    private CoordinatorLayout coordinatorLayout;
    private LinearLayout llHeader, bottomContent;
    private Toolbar toolbar;
    private ImageView imgProfile;
    private ViewPager viewPager;

    private BottomSheetBehavior behavior;
    private ItemPagerAdapter adapter;
    private CustomImageView customImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_home);

        Bundle b = getIntent().getExtras();
        UserObject userObject;
        if (b != null) {
            userObject = b.getParcelable(Constant.ParcelKey.USER_OBJECT);
        } else {
            userObject = databaseHandler.getUser();
        }

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navView = (NavigationView) findViewById(R.id.nav_view);
        viewPager = (ViewPager) findViewById(R.id.pager);
        bottomContent = (LinearLayout) findViewById(R.id.bottom_content);
        contentView = findViewById(R.id.content);
        llHeader = (LinearLayout) navView.getHeaderView(0);
        imgProfile = (ImageView) llHeader.findViewById(R.id.img_profile);
        TextView tvUserName = llHeader.findViewById(R.id.tv_user_name);

        if (userObject != null)
            tvUserName.setText(Utils.format(Constant.FormatString.FORMAT_MSG, userObject.getLastName(), userObject.getFirstName()));
        else
            tvUserName.setText("Dương Quang Trường");
        setToolbar();
        setBackgroundColor();
        installBlankDrawer();
        installBottomSheetBottom();
    }

    private void setToolbar() {
        try {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(" ");
            }

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            llHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchActivityTrasition(imgProfile, HomeActivity.this, ProfileActivity.class);
                }
            });
        } catch (Throwable e) {
            Log.e(TAG, "setToolbar: ", e);
        }
    }

    private void setBackgroundColor() {
        setViewBackground(contentView);
        setViewBackground(coordinatorLayout);
        setViewBackground(llHeader);
        setViewBackground(toolbar);
    }

    private void installBlankDrawer() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawerLayout.setElevation(0.f);
        }
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.drawer_shadow);
        drawerLayout.setDrawerShadow(drawable, GravityCompat.START);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        navView.setNavigationItemSelectedListener(this);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                           @Override
                                           public void onDrawerSlide(View drawerView, float slideOffset) {
                                               final float diffScaledOffset = slideOffset * (1 - Constant.END_SCALE);
                                               final float offsetScale = 1 - diffScaledOffset;
                                               contentView.setScaleX(offsetScale);
                                               contentView.setScaleY(offsetScale);

                                               // Translate the View, accounting for the scaled width
                                               final float xOffset = drawerView.getWidth() * slideOffset;
                                               final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                                               final float xTranslation = xOffset - xOffsetDiff;
                                               contentView.setTranslationX(xTranslation);
                                           }

                                           @Override
                                           public void onDrawerClosed(View drawerView) {
                                               behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                           }
                                       }
        );
    }

    private void installBottomSheetBottom() {
        final View bottomSheet = coordinatorLayout.findViewById(R.id.bottom_sheet);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bottomSheet.setNestedScrollingEnabled(true);
        }
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        onCollapsed();
                        Log.d("bottomsheet-", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
//                         onDraggin();
                        Log.d("bottomsheet-", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        onExpanded();
                        Log.d("bottomsheet-", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_ANCHOR_POINT:
                        onExpanded();
                        Log.d("bottomsheet-", "STATE_ANCHOR_POINT");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("bottomsheet-", "STATE_HIDDEN");
                        break;
                    default:
                        Log.d("bottomsheet-", "STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        int[] mDrawables = {1};
        adapter = new ItemPagerAdapter(this, mDrawables, this);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void onExpanded() {
        bottomContent.setBackgroundColor(getResources().getColor(R.color.background_movie));
        viewPager.setBackgroundColor(getResources().getColor(R.color.background_movie));
        adapter.onExpanded(this);

    }

    public void onCollapsed() {
        bottomContent.setBackground(getResources().getDrawable(R.drawable.bg_item_recycler));
        viewPager.setBackground(getResources().getDrawable(R.drawable.bg_item_recycler));
        adapter.onCollapsed(this);
    }

    @Override
    public void toDismiss() {
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nv_payment:
                break;
            case R.id.nv_message:
                break;
            case R.id.nv_logout:
                logout();
                break;
        }
        return false;
    }
}
