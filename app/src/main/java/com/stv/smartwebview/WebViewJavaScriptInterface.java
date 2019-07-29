package com.stv.smartwebview;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

class WebViewJavaScriptInterface {
    Context context;

    public WebViewJavaScriptInterface(Context context)
    {
        this.context = context;
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "WEBVIEW_CHANNEL";
            String description = "Channel for the SmartWebView";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("WEBVIEW_CHANNEL_ID", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @JavascriptInterface
    public void startIntentForUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent();
        intent.setData(uri);
        context.startActivity(intent);
    }

    @JavascriptInterface
    public void showToast(String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void showNotification(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "WEBVIEW_CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(message)
                .setContentText("Notificação enviada pelo JavaScript")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, builder.build());

    };
}
