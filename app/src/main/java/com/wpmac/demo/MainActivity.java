package com.wpmac.demo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.wpmac.premission.DialogUtil;
import com.wpmac.premission.LocationUtils;
import com.wpmac.premission.PermissionHelper;
import com.wpmac.premission.RequestCode;
import com.wpmac.premission.XPermissionUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        setListener();
    }

    private void setListener() {

    }

    @OnClick(R.id.CAMERA)
    void doOpenCamera() {
        XPermissionUtils.requestPermissions(this, RequestCode.CAMERA, new String[]{ Manifest.permission.CAMERA}
                , new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        if (PermissionHelper.isCameraEnable()) {
                            Toast.makeText(mContext, "获取相机权限成功", Toast.LENGTH_SHORT).show();
                        } else {
                            DialogUtil.showPermissionManagerDialog(mContext, "相机");
                        }
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        StringBuilder sBuider = new StringBuilder();
                        for (String deniedPermission : deniedPermissions) {
                            if (deniedPermission.equals(Manifest.permission.CAMERA)) {
                                sBuider.append("相机");
                                sBuider.append(",");
                            }

                        }

                        if (sBuider.length() > 0) sBuider.deleteCharAt(sBuider.length() - 1);
                        Toast.makeText(mContext, "获取" + sBuider.toString() + "权限失败", Toast.LENGTH_SHORT).show();
                        if (XPermissionUtils.hasAlwaysDeniedPermission(mContext, deniedPermissions)) {
                            DialogUtil.showPermissionManagerDialog(mContext, sBuider.toString());
                        }

                    }
                }, new XPermissionUtils.RationaleHandler() {

                    @Override
                    protected void showRationale() {
                        new AlertDialog.Builder(mContext)
                                .setTitle("温馨提示")
                                .setMessage("我们需要相机权限才能正常使用该功能")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissionsAgain();
                                    }
                                }).show();
                    }
                });
    }

    @OnClick(R.id.EXTERNAL)
    void EXTERNAL() {

        XPermissionUtils.requestPermissions(this, RequestCode.EXTERNAL,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(mContext, "获取外部存储权限成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        StringBuilder sBuilder = new StringBuilder();
                        for (String deniedPermission : deniedPermissions) {
                            if (deniedPermission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                sBuilder.append("读取存储");
                                sBuilder.append(",");
                            }
                            if (deniedPermission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                sBuilder.append("写入存储");
                                sBuilder.append(",");
                            }
                        }
                        if (sBuilder.length() > 0) {
                            sBuilder.deleteCharAt(sBuilder.length() - 1);
                        }
                        Toast.makeText(mContext, "获取" + sBuilder.toString() + "权限失败", Toast.LENGTH_SHORT).show();

                    }


                }, new XPermissionUtils.RationaleHandler() {
                    @Override
                    protected void showRationale() {
                        new AlertDialog.Builder(mContext)
                                .setTitle("温馨提示")
                                .setMessage("我们需要存储权限才能正常使用该功能")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissionsAgain();
                                    }
                                }).show();
                    }
                });

    }

    @OnClick(R.id.MORE)
    void more() {
        XPermissionUtils.requestPermissions(this, RequestCode.CAMERA, new String[]{Manifest
                        .permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}
                , new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        if (PermissionHelper.isCameraEnable()) {
                            Toast.makeText(mContext, "获取相机，外部存储权限成功", Toast.LENGTH_SHORT).show();

                        } else {
                            DialogUtil.showPermissionManagerDialog(mContext, "相机");
                        }
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        StringBuilder sBuider = new StringBuilder();
                        for (String deniedPermission : deniedPermissions) {
                            if (deniedPermission.equals(Manifest.permission.CAMERA)) {
                                sBuider.append("相机");
                                sBuider.append(",");
                            }
                            if (deniedPermission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                sBuider.append("读取存储");
                                sBuider.append(",");
                            }
                            if (deniedPermission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                sBuider.append("写入存储");
                                sBuider.append(",");
                            }
                        }

                        if (sBuider.length() > 0) sBuider.deleteCharAt(sBuider.length() - 1);
                        Toast.makeText(mContext, "获取" + sBuider.toString() + "权限失败", Toast.LENGTH_SHORT).show();
                        if (XPermissionUtils.hasAlwaysDeniedPermission(mContext, deniedPermissions)) {
                            DialogUtil.showPermissionManagerDialog(mContext, sBuider.toString());
                        }

                    }
                }, new XPermissionUtils.RationaleHandler() {

                    @Override
                    protected void showRationale() {
                        new AlertDialog.Builder(mContext)
                                .setTitle("温馨提示")
                                .setMessage("我们需要相机权限,存储权限才能正常使用该功能")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissionsAgain();
                                    }
                                }).show();
                    }
                });


    }

    @OnClick(R.id.LOCATION)
    void LOCATION() {
        if (!PermissionHelper.isLocServiceEnable(this)) {
            DialogUtil.showLocServiceDialog(this);
            return;
        }
        LocationUtils.requestLocation(this);
    }

    @OnClick(R.id.call_phone)
    void phone() {

        XPermissionUtils.requestPermissions(this, RequestCode.PHONE, new String[]{Manifest.permission.CALL_PHONE}
                , new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + "12345678"));
                        startActivity(intent);


                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        StringBuilder sBuider = new StringBuilder();
                        for (String deniedPermission : deniedPermissions) {
                            if (deniedPermission.equals(Manifest.permission.CALL_PHONE)) {
                                sBuider.append("拨号");
                                sBuider.append(",");
                            }

                        }

                        if (sBuider.length() > 0) sBuider.deleteCharAt(sBuider.length() - 1);
                        Toast.makeText(mContext, "获取" + sBuider.toString() + "权限失败", Toast.LENGTH_SHORT).show();
                        if (XPermissionUtils.hasAlwaysDeniedPermission(mContext, deniedPermissions)) {
                            DialogUtil.showPermissionManagerDialog(mContext, sBuider.toString());
                        }

                    }
                }, new XPermissionUtils.RationaleHandler() {

                    @Override
                    protected void showRationale() {
                        new AlertDialog.Builder(mContext)
                                .setTitle("温馨提示")
                                .setMessage("我们需要拨号权限才能正常使用该功能")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissionsAgain();
                                    }
                                }).show();
                    }
                });
    }

    @OnClick(R.id.AUDIO)
    void auto() {
        XPermissionUtils.requestPermissions(this, RequestCode.AUDIO, new String[]{
                        Manifest.permission.RECORD_AUDIO}
                , new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        if (PermissionHelper.isAudioEnable()) {
                            Toast.makeText(mContext, "获取录音权限成功", Toast.LENGTH_SHORT).show();
                        } else {
                            DialogUtil.showPermissionManagerDialog(mContext, "录音或麦克风");
                        }
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        Toast.makeText(mContext, "获取录音或麦克风权限失败", Toast.LENGTH_SHORT).show();
                        if (XPermissionUtils.hasAlwaysDeniedPermission(mContext, deniedPermissions)) {
                            DialogUtil.showPermissionManagerDialog(mContext, "录音或麦克风");
                        }

                    }
                }, new XPermissionUtils.RationaleHandler() {

                    @Override
                    protected void showRationale() {
                        new AlertDialog.Builder(mContext)
                                .setTitle("温馨提示")
                                .setMessage("我们需要录音权限才能正常使用该功能")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissionsAgain();
                                    }
                                }).show();
                    }
                });
    }
}
