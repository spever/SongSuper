package com.tencent.qcloud.suixinbo.views.customviews;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tencent.qcloud.suixinbo.R;

/**
 * author: Eric_luo .
 * date:   On 2016/6/22
 */
public class BaseFragment extends Fragment {
    protected View view;

    public String url;
//    protected PostFormBuilder base;


    public ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar();
//        initHttp(url);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initHttp(String url) {

//        base = OkHttpUtils.post().url(UrlConstants.BASE + url).addParams("license", UrlConstants.license).addParams("token", PreferenceDB.getInstance().getUserAccesstoken());
    }


    public void showProgressDialog(String msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            return;
        }
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(msg);
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    public void dismissProgressDialog() {
        if (null != progressDialog && progressDialog.isShowing() == true) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.theme_color));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    protected void initData() {
    }
}
