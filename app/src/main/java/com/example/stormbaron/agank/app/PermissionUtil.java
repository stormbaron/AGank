package com.example.stormbaron.agank.app;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by stormbaron on 17-6-12.
 *
 * 判断和请求特殊权限的工具累
 */

public class PermissionUtil {
    public static String[] requestPermissionAr = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE};

    public static void requestPermission(Activity object, int code) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;

        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            checkPermission(object);
        }
    }

    public static void checkPermission(Activity object) {

        int ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(object, Manifest.permission.ACCESS_COARSE_LOCATION);
        int READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(object, Manifest.permission.READ_EXTERNAL_STORAGE);
        int CAMERA = ContextCompat.checkSelfPermission(object, Manifest.permission.CAMERA);
        int CALL_PHONE = ContextCompat.checkSelfPermission(object, Manifest.permission.CALL_PHONE);

        int PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED;

        if (ACCESS_COARSE_LOCATION != PERMISSION_GRANTED || READ_EXTERNAL_STORAGE != PERMISSION_GRANTED ||
                CAMERA != PERMISSION_GRANTED || CALL_PHONE != PERMISSION_GRANTED) {
            // Should we show an explanation?
           /* if (ActivityCompat.shouldShowRequestPermissionRationale(object,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {*/
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(object,
                    requestPermissionAr,
                    1);
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            // }
        }

    }

}
