package com.tencent.qcloud.suixinbo.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tencent.TIMManager;
import com.tencent.av.sdk.AVContext;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.MySelfInfo;
import com.tencent.qcloud.suixinbo.presenters.LoginHelper;
import com.tencent.qcloud.suixinbo.presenters.viewinface.LogoutView;
import com.tencent.qcloud.suixinbo.utils.SxbLog;
import com.tencent.qcloud.suixinbo.views.customviews.BaseActivity;
import com.tencent.qcloud.suixinbo.views.customviews.CustomSwitch;
import com.tencent.qcloud.suixinbo.views.customviews.LineControllerView;
import com.tencent.qcloud.suixinbo.views.customviews.TemplateTitle;

/**
 * 设置页面
 */
public class SetActivity extends BaseActivity implements View.OnClickListener, LogoutView {
    private final static String TAG = "SetActivity";
    private CustomSwitch csAnimator;
    private LineControllerView lcvLog;
    private LineControllerView lcvVersion;
    private TemplateTitle ttHead;
    private Button logout;
    private LoginHelper mLoginHelper;
    private RelativeLayout rl_preson_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set);

        mLoginHelper = new LoginHelper(this, this);
        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        ttHead = (TemplateTitle) findViewById(R.id.tt_head);
        csAnimator = (CustomSwitch) findViewById(R.id.cs_animator);
        rl_preson_edit = (RelativeLayout) findViewById(R.id.rl_preson_edit);
        rl_preson_edit.setOnClickListener(this);
        lcvLog = (LineControllerView) findViewById(R.id.lcv_set_log_level);
        lcvVersion = (LineControllerView) findViewById(R.id.lcv_set_version);
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(this);

        lcvLog.setContent(MySelfInfo.getInstance().getLogLevel().toString());

        csAnimator.setChecked(MySelfInfo.getInstance().isbLiveAnimator(), false);

        ttHead.setReturnListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void changeLogLevel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(SxbLog.getStringValues(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int which) {
                MySelfInfo.getInstance().setLogLevel(SxbLog.SxbLogLevel.values()[which]);
                SxbLog.setLogLevel(MySelfInfo.getInstance().getLogLevel());
                lcvLog.setContent(MySelfInfo.getInstance().getLogLevel().toString());
                MySelfInfo.getInstance().writeToCache(SetActivity.this);
            }
        });
        builder.show();
    }

    private void showSDKVersion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("IM SDK: " + TIMManager.getInstance().getVersion() + "\r\n"
                + "QAL SDK: " + QALSDKManager.getInstance().getSdkVersion() + "\r\n"
                + "AV SDK: " + AVContext.getVersion());
        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cs_animator:
                MySelfInfo.getInstance().setbLiveAnimator(!MySelfInfo.getInstance().isbLiveAnimator());
                MySelfInfo.getInstance().writeToCache(this);
                csAnimator.setChecked(MySelfInfo.getInstance().isbLiveAnimator(), true);
                break;
            case R.id.lcv_set_log_level:
                changeLogLevel();
                break;
            case R.id.lcv_set_version:
                showSDKVersion();
                break;
            case R.id.rl_preson_edit:
                startActivity(new Intent(SetActivity.this, EditProfileActivity.class));
                break;
            case R.id.logout:
                mLoginHelper.imLogout();
                break;
        }
    }

    @Override
    public void logoutSucc() {
        Toast.makeText(this, "Logout and quite", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void logoutFail() {

    }
}
