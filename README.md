"android premisson lib" 
##step 1

add lib
```
<dependency>
  <groupId>com.wpmac</groupId>
  <artifactId>premisson</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>
```

##step 2
edit your base activity

add next callback
```
@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        XPermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
```

##step 3
use lib to check premisson


single 

```
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
    
   ```
   
   many
   ```
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

   
   ```
   