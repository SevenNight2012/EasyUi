package com.xxc.dev.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xxc.dev.permission.Sudo;

import java.util.List;

/**
 * activity 基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        String[] requestPermissions = requestPermissions();
        if (requestPermissions.length > 0) {
            // 拒绝授权
            Sudo.getInstance()
                    .prepare()
                    .setPermissions(requestPermissions)
                    .setGranted(allow -> {
                        // 授权
                        initView(savedInstanceState);
                    })
                    .setRefused(this::onPermissionRefused)// 拒绝全选
                    .setNeverAsk(this::onPermissionSilent)// 不再询问
                    .request(this);
        } else {
            initView(savedInstanceState);
        }
    }

    public abstract int layoutId();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    public String[] requestPermissions() {
        return new String[0];
    }

    /**
     * 拒绝权限
     *
     * @param refused 拒绝的权限
     */
    public void onPermissionRefused(List<String> refused) {

    }

    /**
     * 不再询问
     *
     * @param neverAsk 不再询问的权限
     */
    public void onPermissionSilent(List<String> neverAsk) {

    }

}
