package com.roadster.sakhala.firebasecustomnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;

/**
 * Created by atulsakhala on 31/01/18.
 */

public class ActionNotificationBuilder {

    public static final String BUTTON_ACTION = "button_action";
    public static final String BUTTON_ICON = "button_icon";
    public static final String UPDATE_STATUS_ID = "update_status_id";
    private static final String TAG = ActionNotificationBuilder.class.getName();
    private NotificationCompat.Builder mBuilder;
    private NotificationManager notificationManager;
    private Context context;
    private int mNotificationSmallIconResource;
    private Bitmap mNotificationBigIconResounce;
    private String title;
    private String subTitle;
    private Bitmap bitmap;
    private NotificationChannel notificationChannel;
    private Uri uri;


    public ActionNotificationBuilder(Context activity, String NOTIFICATION_CHANNEL_ID) {
        this.context = activity;
        notificationManager = (NotificationManager) activity.getSystemService(activity.NOTIFICATION_SERVICE);
        createNotificationChannel(NOTIFICATION_CHANNEL_ID);
        mBuilder = new NotificationCompat.Builder(activity, NOTIFICATION_CHANNEL_ID);
        mNotificationBigIconResounce = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_background);
        mNotificationSmallIconResource = android.R.drawable.ic_dialog_info;
    }

    private void  createNotificationChannel(String NOTIFICATION_CHANNEL_ID){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{500, 500, 500, 500, 500, 500, 500});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }
    public void showNotification(Intent intent) {
        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setContentTitle(getTitle())
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500})
                .setSound(getSound())
                .setContentIntent(expandableIntent)
                .setContentText(getSubTitle());

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(getNotificationSmallIconResource());
            mBuilder.setColor(context.getResources().getColor(android.R.color.white));
        } else {
            mBuilder.setSmallIcon(getNotificationSmallIconResource());
        }

        // Obtain NotificationManager system service in order to show the notification
        notificationManager.notify(852, mBuilder.build());
    }

    public void showNotificationWithActions(Intent intent, ArrayList<Intent> actions) {
        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setContentTitle(getTitle())
                .setContentText(getSubTitle())
                .setContentIntent(expandableIntent)
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500})
                .setSound(getSound())
                .setAutoCancel(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(getNotificationSmallIconResource());
            mBuilder.setColor(context.getResources().getColor(android.R.color.white));
        } else {
            mBuilder.setSmallIcon(getNotificationSmallIconResource());
        }

        for (Intent action : actions) {
            PendingIntent buttonAction = PendingIntent.getActivity(
                    context,
                    0,
                    action,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );
            int iconResource = action.getIntExtra(BUTTON_ICON, android.R.drawable.ic_dialog_info);
            mBuilder.addAction(iconResource, action.getStringExtra(BUTTON_ACTION), buttonAction);
        }

        // Obtain NotificationManager system service in order to show the notification
        notificationManager.notify(852, mBuilder.build());
    }

    public void showBigImageNotification(Intent intent) {
        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setLargeIcon(getNotificationBigIconResounce())
                .setContentTitle(getTitle())
                .setContentText(getSubTitle())
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500})
                .setSound(getSound())
                .setContentIntent(expandableIntent)
                .setAutoCancel(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(getNotificationSmallIconResource());
            mBuilder.setColor(context.getResources().getColor(android.R.color.white));
        } else {
            mBuilder.setSmallIcon(getNotificationSmallIconResource());
        }

        // Obtain NotificationManager system service in order to show the notification
        notificationManager.notify(852, mBuilder.build());
    }


    public void showBigImageNotificationWithActions(Intent intent, ArrayList<Intent> actions) {
        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setLargeIcon(getNotificationBigIconResounce())
                .setContentTitle(getTitle())
                .setContentText(getSubTitle())
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500})
                .setSound(getSound())
                .setContentIntent(expandableIntent)
                .setAutoCancel(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(getNotificationSmallIconResource());
            mBuilder.setColor(context.getResources().getColor(android.R.color.white));
        } else {
            mBuilder.setSmallIcon(getNotificationSmallIconResource());
        }

        for (Intent action : actions) {
            PendingIntent buttonAction = PendingIntent.getActivity(
                    context,
                    0,
                    action,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );
            int iconResource = action.getIntExtra(BUTTON_ICON, android.R.drawable.ic_dialog_info);
            mBuilder.addAction(iconResource, action.getStringExtra(BUTTON_ACTION), buttonAction);
        }

        // Obtain NotificationManager system service in order to show the notification
        notificationManager.notify(852, mBuilder.build());
    }


    public void showBigPictureNotification(Intent intent, Bitmap resource) {

        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setLargeIcon(getNotificationBigIconResounce())
                .setContentTitle(getTitle())
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500})
                .setSound(getSound())
                .setContentIntent(expandableIntent)
                .setAutoCancel(true);

        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(resource);
        String message = getSubTitle();

        if (message.length() > 100) {
            message = message.substring(0, 100);
            message = message + "...";
        }
        s.setSummaryText(message);
        mBuilder.setStyle(s);
        notificationManager.notify(852, mBuilder.build());


    }

    public void showBigPictureNotificationWithAction(Intent intent, ArrayList<Intent> actions, Bitmap resource) {

        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setLargeIcon(getNotificationBigIconResounce())
                .setContentTitle(getTitle())
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500})
                .setSound(getSound())
                .setContentIntent(expandableIntent)
                .setAutoCancel(true);

        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(resource);
        String message = getSubTitle();

        if (message.length() > 100) {
            message = message.substring(0, 100);
            message = message + "...";
        }
        s.setSummaryText(message);
        mBuilder.setStyle(s);

        for (Intent action : actions) {
            PendingIntent buttonAction = PendingIntent.getActivity(
                    context,
                    0,
                    action,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );
            int iconResource = action.getIntExtra(BUTTON_ICON, android.R.drawable.ic_dialog_info);
            mBuilder.addAction(iconResource, action.getStringExtra(BUTTON_ACTION), buttonAction);
        }

        notificationManager.notify(852, mBuilder.build());
    }


    public void showBigTextExpandableNotification(Intent intent) {
        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setLargeIcon(getNotificationBigIconResounce())
                .setContentTitle(getTitle())
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500})
                .setSound(getSound())
                .setContentIntent(expandableIntent)
                .setAutoCancel(true);

        NotificationCompat.BigTextStyle s = new NotificationCompat.BigTextStyle();
        String title = getTitle();
        String message = getSubTitle();

        s.bigText(message);
        s.setBigContentTitle(title);
        //s.setSummaryText(message);
        mBuilder.setStyle(s);

        notificationManager.notify(852, mBuilder.build());
    }

    public void showBigTextExpandableNotificationWithAction(Intent intent, ArrayList<Intent> actions) {
        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setLargeIcon(getNotificationBigIconResounce())
                .setContentTitle(getTitle())
                .setVibrate(new long[]{500, 500, 500, 500, 500, 500, 500})
                .setSound(getSound())
                .setContentIntent(expandableIntent)
                .setAutoCancel(true);

        NotificationCompat.BigTextStyle s = new NotificationCompat.BigTextStyle();
        String title = getTitle();
        String message = getSubTitle();
        s.bigText(message);
        s.setBigContentTitle(title);
        //s.setSummaryText(message);
        mBuilder.setStyle(s);

        for (Intent action : actions) {
            PendingIntent buttonAction = PendingIntent.getActivity(
                    context,
                    0,
                    action,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );
            int iconResource = action.getIntExtra(BUTTON_ICON, android.R.drawable.ic_dialog_info);
            mBuilder.addAction(iconResource, action.getStringExtra(BUTTON_ACTION), buttonAction);
        }

        notificationManager.notify(852, mBuilder.build());
    }


    public void cancelNotification() {
        notificationManager.cancel(852);
    }

    public int getNotificationSmallIconResource() {
        return mNotificationSmallIconResource;
    }

    public void setNotificationSmallIconResource(int mNotificationSmallIconResource) {
        this.mNotificationSmallIconResource = mNotificationSmallIconResource;
    }

    public Bitmap getNotificationBigIconResounce() {
        return mNotificationBigIconResounce;
    }

    public void setNotificationBigIconResounce(Bitmap mNotificationBigIconResounce) {
        this.mNotificationBigIconResounce = mNotificationBigIconResounce;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Uri getSound() {
        return uri;
    }

    public void setSound(Uri uri){
        this.uri = uri;
    }
}
