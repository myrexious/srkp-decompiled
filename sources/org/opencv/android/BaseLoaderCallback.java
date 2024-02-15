package org.opencv.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/* loaded from: classes4.dex */
public abstract class BaseLoaderCallback implements LoaderCallbackInterface {
    private static final String TAG = "OpenCVLoader/BaseLoaderCallback";
    protected Context mAppContext;

    public BaseLoaderCallback(Context context) {
        this.mAppContext = context;
    }

    @Override // org.opencv.android.LoaderCallbackInterface
    public void onManagerConnected(int i) {
        if (i != 0) {
            if (i == 2) {
                Log.e(TAG, "Package installation failed!");
                AlertDialog create = new AlertDialog.Builder(this.mAppContext).create();
                create.setTitle("OpenCV Manager");
                create.setMessage("Package installation failed!");
                create.setCancelable(false);
                create.setButton(-1, "OK", new DialogInterface.OnClickListener() { // from class: org.opencv.android.BaseLoaderCallback.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        BaseLoaderCallback.this.finish();
                    }
                });
                create.show();
            } else if (i == 3) {
                Log.d(TAG, "OpenCV library installation was canceled by user");
                finish();
            } else if (i == 4) {
                Log.d(TAG, "OpenCV Manager Service is uncompatible with this app!");
                AlertDialog create2 = new AlertDialog.Builder(this.mAppContext).create();
                create2.setTitle("OpenCV Manager");
                create2.setMessage("OpenCV Manager service is incompatible with this app. Try to update it via Google Play.");
                create2.setCancelable(false);
                create2.setButton(-1, "OK", new DialogInterface.OnClickListener() { // from class: org.opencv.android.BaseLoaderCallback.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        BaseLoaderCallback.this.finish();
                    }
                });
                create2.show();
            } else {
                Log.e(TAG, "OpenCV loading failed!");
                AlertDialog create3 = new AlertDialog.Builder(this.mAppContext).create();
                create3.setTitle("OpenCV error");
                create3.setMessage("OpenCV was not initialised correctly. Application will be shut down");
                create3.setCancelable(false);
                create3.setButton(-1, "OK", new DialogInterface.OnClickListener() { // from class: org.opencv.android.BaseLoaderCallback.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        BaseLoaderCallback.this.finish();
                    }
                });
                create3.show();
            }
        }
    }

    @Override // org.opencv.android.LoaderCallbackInterface
    public void onPackageInstall(int i, final InstallCallbackInterface installCallbackInterface) {
        if (i == 0) {
            AlertDialog create = new AlertDialog.Builder(this.mAppContext).create();
            create.setTitle("Package not found");
            create.setMessage(installCallbackInterface.getPackageName() + " package was not found! Try to install it?");
            create.setCancelable(false);
            create.setButton(-1, "Yes", new DialogInterface.OnClickListener() { // from class: org.opencv.android.BaseLoaderCallback.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    installCallbackInterface.install();
                }
            });
            create.setButton(-2, "No", new DialogInterface.OnClickListener() { // from class: org.opencv.android.BaseLoaderCallback.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    installCallbackInterface.cancel();
                }
            });
            create.show();
        } else if (i != 1) {
        } else {
            AlertDialog create2 = new AlertDialog.Builder(this.mAppContext).create();
            create2.setTitle("OpenCV is not ready");
            create2.setMessage("Installation is in progress. Wait or exit?");
            create2.setCancelable(false);
            create2.setButton(-1, "Wait", new DialogInterface.OnClickListener() { // from class: org.opencv.android.BaseLoaderCallback.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    installCallbackInterface.wait_install();
                }
            });
            create2.setButton(-2, "Exit", new DialogInterface.OnClickListener() { // from class: org.opencv.android.BaseLoaderCallback.7
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    installCallbackInterface.cancel();
                }
            });
            create2.show();
        }
    }

    void finish() {
        ((Activity) this.mAppContext).finish();
    }
}
