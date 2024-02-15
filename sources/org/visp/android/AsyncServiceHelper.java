package org.visp.android;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.tom_roush.pdfbox.contentstream.operator.OperatorName;
import java.io.File;
import java.util.StringTokenizer;
import org.visp.engine.ViSPEngineInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class AsyncServiceHelper {
    protected static final int MINIMUM_ENGINE_VERSION = 2;
    protected static final String TAG = "ViSPManager/Helper";
    protected static final String VISP_SERVICE_URL = "market://details?id=org.visp.engine";
    protected static boolean mLibraryInstallationProgress = false;
    protected static boolean mServiceInstallationProgress = false;
    protected Context mAppContext;
    protected ViSPEngineInterface mEngineService;
    protected ServiceConnection mServiceConnection = new ServiceConnection() { // from class: org.visp.android.AsyncServiceHelper.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(AsyncServiceHelper.TAG, "Service connection created");
            AsyncServiceHelper.this.mEngineService = ViSPEngineInterface.Stub.asInterface(iBinder);
            if (AsyncServiceHelper.this.mEngineService == null) {
                Log.d(AsyncServiceHelper.TAG, "ViSP Manager Service connection fails. May be service was not installed?");
                AsyncServiceHelper.InstallService(AsyncServiceHelper.this.mAppContext, AsyncServiceHelper.this.mUserAppCallback);
                return;
            }
            int i = 0;
            AsyncServiceHelper.mServiceInstallationProgress = false;
            try {
                if (AsyncServiceHelper.this.mEngineService.getEngineVersion() < 2) {
                    Log.d(AsyncServiceHelper.TAG, "Init finished with status 4");
                    Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                    AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                    Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                    AsyncServiceHelper.this.mUserAppCallback.onManagerConnected(4);
                    return;
                }
                Log.d(AsyncServiceHelper.TAG, "Trying to get library path");
                String libPathByVersion = AsyncServiceHelper.this.mEngineService.getLibPathByVersion(AsyncServiceHelper.this.mViSPersion);
                if (libPathByVersion != null && libPathByVersion.length() != 0) {
                    Log.d(AsyncServiceHelper.TAG, "Trying to get library list");
                    AsyncServiceHelper.mLibraryInstallationProgress = false;
                    String libraryList = AsyncServiceHelper.this.mEngineService.getLibraryList(AsyncServiceHelper.this.mViSPersion);
                    Log.d(AsyncServiceHelper.TAG, "Library list: \"" + libraryList + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
                    Log.d(AsyncServiceHelper.TAG, "First attempt to load libs");
                    if (AsyncServiceHelper.this.initViSPLibs(libPathByVersion, libraryList)) {
                        Log.d(AsyncServiceHelper.TAG, "First attempt to load libs is OK");
                    } else {
                        Log.d(AsyncServiceHelper.TAG, "First attempt to load libs fails");
                        i = 255;
                    }
                    Log.d(AsyncServiceHelper.TAG, "Init finished with status " + i);
                    Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                    AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                    Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                    AsyncServiceHelper.this.mUserAppCallback.onManagerConnected(i);
                    return;
                }
                if (!AsyncServiceHelper.mLibraryInstallationProgress) {
                    AsyncServiceHelper.this.mUserAppCallback.onPackageInstall(0, new InstallCallbackInterface() { // from class: org.visp.android.AsyncServiceHelper.3.1
                        @Override // org.visp.android.InstallCallbackInterface
                        public String getPackageName() {
                            return "ViSP library";
                        }

                        @Override // org.visp.android.InstallCallbackInterface
                        public void install() {
                            Log.d(AsyncServiceHelper.TAG, "Trying to install ViSP lib via Google Play");
                            try {
                                if (AsyncServiceHelper.this.mEngineService.installVersion(AsyncServiceHelper.this.mViSPersion)) {
                                    AsyncServiceHelper.mLibraryInstallationProgress = true;
                                    Log.d(AsyncServiceHelper.TAG, "Package installation started");
                                    Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                                    AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                                } else {
                                    Log.d(AsyncServiceHelper.TAG, "ViSP package was not installed!");
                                    Log.d(AsyncServiceHelper.TAG, "Init finished with status 2");
                                    Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                                    AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                                    Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                                    AsyncServiceHelper.this.mUserAppCallback.onManagerConnected(2);
                                }
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                Log.d(AsyncServiceHelper.TAG, "Init finished with status 255");
                                Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                                AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                                Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                                AsyncServiceHelper.this.mUserAppCallback.onManagerConnected(255);
                            }
                        }

                        @Override // org.visp.android.InstallCallbackInterface
                        public void cancel() {
                            Log.d(AsyncServiceHelper.TAG, "ViSP library installation was canceled");
                            Log.d(AsyncServiceHelper.TAG, "Init finished with status 3");
                            Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                            AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                            Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                            AsyncServiceHelper.this.mUserAppCallback.onManagerConnected(3);
                        }

                        @Override // org.visp.android.InstallCallbackInterface
                        public void wait_install() {
                            Log.e(AsyncServiceHelper.TAG, "Installation was not started! Nothing to wait!");
                        }
                    });
                    return;
                }
                AsyncServiceHelper.this.mUserAppCallback.onPackageInstall(1, new InstallCallbackInterface() { // from class: org.visp.android.AsyncServiceHelper.3.2
                    @Override // org.visp.android.InstallCallbackInterface
                    public String getPackageName() {
                        return "ViSP library";
                    }

                    @Override // org.visp.android.InstallCallbackInterface
                    public void install() {
                        Log.e(AsyncServiceHelper.TAG, "Nothing to install we just wait current installation");
                    }

                    @Override // org.visp.android.InstallCallbackInterface
                    public void cancel() {
                        Log.d(AsyncServiceHelper.TAG, "ViSP library installation was canceled");
                        AsyncServiceHelper.mLibraryInstallationProgress = false;
                        Log.d(AsyncServiceHelper.TAG, "Init finished with status 3");
                        Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                        AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                        Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                        AsyncServiceHelper.this.mUserAppCallback.onManagerConnected(3);
                    }

                    @Override // org.visp.android.InstallCallbackInterface
                    public void wait_install() {
                        Log.d(AsyncServiceHelper.TAG, "Waiting for current installation");
                        try {
                            if (!AsyncServiceHelper.this.mEngineService.installVersion(AsyncServiceHelper.this.mViSPersion)) {
                                Log.d(AsyncServiceHelper.TAG, "ViSP package was not installed!");
                                Log.d(AsyncServiceHelper.TAG, "Init finished with status 2");
                                Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                                AsyncServiceHelper.this.mUserAppCallback.onManagerConnected(2);
                            } else {
                                Log.d(AsyncServiceHelper.TAG, "Wating for package installation");
                            }
                            Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                            AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            Log.d(AsyncServiceHelper.TAG, "Init finished with status 255");
                            Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                            AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                            Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                            AsyncServiceHelper.this.mUserAppCallback.onManagerConnected(255);
                        }
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.d(AsyncServiceHelper.TAG, "Init finished with status 255");
                Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                AsyncServiceHelper.this.mAppContext.unbindService(AsyncServiceHelper.this.mServiceConnection);
                Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                AsyncServiceHelper.this.mUserAppCallback.onManagerConnected(255);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            AsyncServiceHelper.this.mEngineService = null;
        }
    };
    protected LoaderCallbackInterface mUserAppCallback;
    protected String mViSPersion;

    public static boolean initViSP(String str, Context context, LoaderCallbackInterface loaderCallbackInterface) {
        AsyncServiceHelper asyncServiceHelper = new AsyncServiceHelper(str, context, loaderCallbackInterface);
        Intent intent = new Intent("org.visp.engine.BIND");
        intent.setPackage("org.visp.engine");
        if (context.bindService(intent, asyncServiceHelper.mServiceConnection, 1)) {
            return true;
        }
        context.unbindService(asyncServiceHelper.mServiceConnection);
        InstallService(context, loaderCallbackInterface);
        return false;
    }

    protected AsyncServiceHelper(String str, Context context, LoaderCallbackInterface loaderCallbackInterface) {
        this.mViSPersion = str;
        this.mUserAppCallback = loaderCallbackInterface;
        this.mAppContext = context;
    }

    protected static boolean InstallServiceQuiet(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(VISP_SERVICE_URL));
            intent.addFlags(268435456);
            context.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    protected static void InstallService(Context context, LoaderCallbackInterface loaderCallbackInterface) {
        if (!mServiceInstallationProgress) {
            Log.d(TAG, "Request new service installation");
            loaderCallbackInterface.onPackageInstall(0, new InstallCallbackInterface(context) { // from class: org.visp.android.AsyncServiceHelper.1
                private LoaderCallbackInterface mUserAppCallback;
                final /* synthetic */ Context val$AppContext;

                @Override // org.visp.android.InstallCallbackInterface
                public String getPackageName() {
                    return "ViSP Manager";
                }

                {
                    LoaderCallbackInterface.this = loaderCallbackInterface;
                    this.val$AppContext = context;
                    this.mUserAppCallback = loaderCallbackInterface;
                }

                @Override // org.visp.android.InstallCallbackInterface
                public void install() {
                    Log.d(AsyncServiceHelper.TAG, "Trying to install ViSP Manager via Google Play");
                    if (AsyncServiceHelper.InstallServiceQuiet(this.val$AppContext)) {
                        AsyncServiceHelper.mServiceInstallationProgress = true;
                        Log.d(AsyncServiceHelper.TAG, "Package installation started");
                        return;
                    }
                    Log.d(AsyncServiceHelper.TAG, "ViSP package was not installed!");
                    Log.d(AsyncServiceHelper.TAG, "Init finished with status 2");
                    Log.d(AsyncServiceHelper.TAG, "Unbind from service");
                    Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                    this.mUserAppCallback.onManagerConnected(2);
                }

                @Override // org.visp.android.InstallCallbackInterface
                public void cancel() {
                    Log.d(AsyncServiceHelper.TAG, "ViSP library installation was canceled");
                    Log.d(AsyncServiceHelper.TAG, "Init finished with status 3");
                    Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                    this.mUserAppCallback.onManagerConnected(3);
                }

                @Override // org.visp.android.InstallCallbackInterface
                public void wait_install() {
                    Log.e(AsyncServiceHelper.TAG, "Installation was not started! Nothing to wait!");
                }
            });
            return;
        }
        Log.d(TAG, "Waiting current installation process");
        loaderCallbackInterface.onPackageInstall(1, new InstallCallbackInterface(context) { // from class: org.visp.android.AsyncServiceHelper.2
            private LoaderCallbackInterface mUserAppCallback;
            final /* synthetic */ Context val$AppContext;

            @Override // org.visp.android.InstallCallbackInterface
            public String getPackageName() {
                return "ViSP Manager";
            }

            {
                LoaderCallbackInterface.this = loaderCallbackInterface;
                this.val$AppContext = context;
                this.mUserAppCallback = loaderCallbackInterface;
            }

            @Override // org.visp.android.InstallCallbackInterface
            public void install() {
                Log.e(AsyncServiceHelper.TAG, "Nothing to install we just wait current installation");
            }

            @Override // org.visp.android.InstallCallbackInterface
            public void cancel() {
                Log.d(AsyncServiceHelper.TAG, "Waiting for ViSP canceled by user");
                AsyncServiceHelper.mServiceInstallationProgress = false;
                Log.d(AsyncServiceHelper.TAG, "Init finished with status 3");
                Log.d(AsyncServiceHelper.TAG, "Calling using callback");
                this.mUserAppCallback.onManagerConnected(3);
            }

            @Override // org.visp.android.InstallCallbackInterface
            public void wait_install() {
                AsyncServiceHelper.InstallServiceQuiet(this.val$AppContext);
            }
        });
    }

    private boolean loadLibrary(String str) {
        Log.d(TAG, "Trying to load library " + str);
        try {
            System.load(str);
            Log.d(TAG, "ViSP libs init was ok!");
            return true;
        } catch (UnsatisfiedLinkError e) {
            Log.d(TAG, "Cannot load library \"" + str + OperatorName.SHOW_TEXT_LINE_AND_SPACE);
            e.printStackTrace();
            return false;
        }
    }

    public boolean initViSPLibs(String str, String str2) {
        Log.d(TAG, "Trying to init ViSP libs");
        if (str != null && str.length() != 0) {
            if (str2 != null && str2.length() != 0) {
                Log.d(TAG, "Trying to load libs by dependency list");
                StringTokenizer stringTokenizer = new StringTokenizer(str2, ";");
                boolean z = true;
                while (stringTokenizer.hasMoreTokens()) {
                    z &= loadLibrary(str + File.separator + stringTokenizer.nextToken());
                }
                return z;
            }
            return loadLibrary(str + File.separator + "libViSP_java4.so");
        }
        Log.d(TAG, "Library path \"" + str + "\" is empty");
        return false;
    }
}
