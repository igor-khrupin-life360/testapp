Error stacktrace:

```
2021-07-07 17:26:55.441 17115-17115/com.testapp.telephonycallback E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.testapp.telephonycallback, PID: 17115
    java.lang.SecurityException: listen
        at android.os.Parcel.createExceptionOrNull(Parcel.java:2418)
        at android.os.Parcel.createException(Parcel.java:2402)
        at android.os.Parcel.readException(Parcel.java:2385)
        at android.os.Parcel.readException(Parcel.java:2327)
        at com.android.internal.telephony.ITelephonyRegistry$Stub$Proxy.listenWithEventList(ITelephonyRegistry.java:1036)
        at android.telephony.TelephonyRegistryManager.listenFromCallback(TelephonyRegistryManager.java:265)
        at android.telephony.TelephonyRegistryManager.registerTelephonyCallback(TelephonyRegistryManager.java:1170)
        at android.telephony.TelephonyManager.registerTelephonyCallback(TelephonyManager.java:15234)
        at com.testapp.telephonycallback.MainActivity.registerPhoneListener(MainActivity.kt:34)
        at com.testapp.telephonycallback.MainActivity.onCreate$lambda-0(MainActivity.kt:26)
        at com.testapp.telephonycallback.MainActivity.lambda$25bvnWQj6gDgC-H37LG3hGREc7Y(Unknown Source:0)
        at com.testapp.telephonycallback.-$$Lambda$MainActivity$25bvnWQj6gDgC-H37LG3hGREc7Y.onClick(Unknown Source:2)
        at android.view.View.performClick(View.java:7434)
        at com.google.android.material.button.MaterialButton.performClick(MaterialButton.java:1119)
        at android.view.View.performClickInternal(View.java:7411)
        at android.view.View.access$3700(View.java:835)
        at android.view.View$PerformClick.run(View.java:28640)
        at android.os.Handler.handleCallback(Handler.java:938)
        at android.os.Handler.dispatchMessage(Handler.java:99)
        at android.os.Looper.loopOnce(Looper.java:201)
        at android.os.Looper.loop(Looper.java:288)
        at android.app.ActivityThread.main(ActivityThread.java:7796)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:974)
     Caused by: android.os.RemoteException: Remote stack trace:
        at com.android.internal.telephony.TelephonyPermissions.enforceCarrierPrivilege(TelephonyPermissions.java:634)
        at com.android.internal.telephony.TelephonyPermissions.checkReadPhoneState(TelephonyPermissions.java:149)
        at com.android.internal.telephony.TelephonyPermissions.checkCallingOrSelfReadPhoneState(TelephonyPermissions.java:94)
        at com.android.server.TelephonyRegistry.checkListenerPermission(TelephonyRegistry.java:3092)
        at com.android.server.TelephonyRegistry.listen(TelephonyRegistry.java:1024)
    ```