package com.roadster.sakhala.firebasecustomnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;

/**
 * Created by atulsakhala on 31/01/18.
 */

public class ActionNotificationBuilder {

    private static final String TAG = ActionNotificationBuilder.class.getName();

    private NotificationCompat.Builder mBuilder;
    private NotificationManager notificationManager;
    private Context context;
    private int mNotificationSmallIconResource = android.R.drawable.ic_dialog_info;
    private int mNotificationBigIconResounce = android.R.drawable.ic_dialog_info;
    private String title;
    private String subTitle;

    public ActionNotificationBuilder(Context activity) {
        this.context = activity;
        mBuilder = new NotificationCompat.Builder(activity);
        notificationManager = (NotificationManager) activity.getSystemService(activity.NOTIFICATION_SERVICE);
    }


    public void setNotificationType(NotificationType type, Intent intent) {
        switch (type) {
            case EXPANDABLE_NOTIFICATION:


            case DOUBLE_ACTION_NOTIFICATION:
                PendingIntent doubleActionIntent = PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                mBuilder
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Something important happened")
                        .setContentText("See the details")
                        .setAutoCancel(true)
                        .setContentIntent(doubleActionIntent)
                        .addAction(android.R.drawable.ic_menu_compass, "Details", doubleActionIntent)
                        .addAction(android.R.drawable.ic_menu_directions, "Show Map", doubleActionIntent);

                notificationManager.notify(852, mBuilder.build());
                break;
            case SINGLE_ACTION_NOTIFICATION:
                PendingIntent singleIntent = PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                mBuilder
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Something important happened")
                        .setContentText("See the details")
                        .setAutoCancel(true)
                        .setContentIntent(singleIntent)
                        .addAction(android.R.drawable.ic_menu_compass, "Details", singleIntent)
                        .addAction(android.R.drawable.ic_menu_directions, "Show Map", singleIntent);

                notificationManager.notify(852, mBuilder.build());

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

    public void showExpandableNotification(Intent intent) {
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
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Data"))
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

    public void showExpandableNotificationWithActions(Intent intent, ArrayList<Intent> actions) {
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
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            int iconResource = action.getIntExtra("IconResource", android.R.drawable.ic_menu_compass);
            mBuilder.addAction(iconResource, "ACCEPT DUTY", buttonAction);
        }

        // Obtain NotificationManager system service in order to show the notification
        notificationManager.notify(852, mBuilder.build());
    }

    public void cancleNotification() {

    }

    public int getNotificationSmallIconResource() {
        return mNotificationSmallIconResource;
    }

    public void setNotificationSmallIconResource(int mNotificationSmallIconResource) {
        this.mNotificationSmallIconResource = mNotificationSmallIconResource;
    }

    public int getNotificationBigIconResounce() {
        return mNotificationBigIconResounce;
    }

    public void setNotificationBigIconResounce(int mNotificationBigIconResounce) {
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
}
