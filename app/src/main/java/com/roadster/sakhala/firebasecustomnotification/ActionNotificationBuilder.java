package com.roadster.sakhala.firebasecustomnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
    private String backGroundImageUrl;

    public ActionNotificationBuilder(Context activity) {
        this.context = activity;
        mBuilder = new NotificationCompat.Builder(activity);
        notificationManager = (NotificationManager) activity.getSystemService(activity.NOTIFICATION_SERVICE);
        mNotificationBigIconResounce = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground);
        mNotificationSmallIconResource = android.R.drawable.ic_dialog_info;
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
                    PendingIntent.FLAG_UPDATE_CURRENT
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
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            int iconResource = action.getIntExtra(BUTTON_ICON, android.R.drawable.ic_dialog_info);
            mBuilder.addAction(iconResource, action.getStringExtra(BUTTON_ACTION), buttonAction);
        }

        // Obtain NotificationManager system service in order to show the notification
        notificationManager.notify(852, mBuilder.build());
    }


    public void showExpandableNotification(Intent intent) {
        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setLargeIcon(getNotificationBigIconResounce())
                .setContentIntent(expandableIntent)
                .setAutoCancel(true);

        try {
            Bitmap myBitmap = Glide.with(context)
                    .load(getBackGroundImageUrl())
                    .asBitmap()
                    .fitCenter()
                    .into(500, 500)
                    .get();
            NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(myBitmap);
            String title = getTitle();
            String message = getSubTitle();
            if (title.length() > 20) {
                title = title.substring(0, 20);
                title = title + "...";
            }

            if (message.length() > 100) {
                message = message.substring(0, 100);
                message = message + "...";
            }
            s.setBigContentTitle(title);
            s.setSummaryText(message);
            mBuilder.setStyle(s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        notificationManager.notify(852, mBuilder.build());
    }

    public void showExpandableNotificationWithAction(Intent intent, ArrayList<Intent> actions) {
        PendingIntent expandableIntent = PendingIntent.getActivity(
                context,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder
                .setSmallIcon(getNotificationSmallIconResource())
                .setLargeIcon(getNotificationBigIconResounce())
                .setContentIntent(expandableIntent)
                .setAutoCancel(true);

        try {
            Bitmap myBitmap = Glide.with(context)
                    .load(getBackGroundImageUrl())
                    .asBitmap()
                    .fitCenter()
                    .into(500, 500)
                    .get();
            NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(myBitmap);
            String title = getTitle();
            String message = getSubTitle();
            if (title.length() > 20) {
                title = title.substring(0, 20);
                title = title + "...";
            }

            if (message.length() > 100) {
                message = message.substring(0, 100);
                message = message + "...";
            }
            s.setBigContentTitle(title);
            s.setSummaryText(message);
            mBuilder.setStyle(s);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        for (Intent action : actions) {
            PendingIntent buttonAction = PendingIntent.getActivity(
                    context,
                    0,
                    action,
                    PendingIntent.FLAG_UPDATE_CURRENT
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

    public String getBackGroundImageUrl() {
        return backGroundImageUrl;
    }

    public void setBackGroundImageUrl(String backGroundImageUrl) {
        this.backGroundImageUrl = backGroundImageUrl;
    }
}
