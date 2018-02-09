package com.roadster.sakhala.firebasecustomnotification;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

/**
 * Created by atulsakhala on 08/02/18.
 */

public class NotificationRunnable implements Runnable {

    Context context;
    String path;
    onDownload listener;

    public NotificationRunnable(Context ctx, String path, onDownload onDownload) {
        this.path = path;
        this.context = ctx;
        this.listener = onDownload;
    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
        try {
            Bitmap bitmap = Glide.with(context).load(path).asBitmap().listener(new RequestListener<String, Bitmap>() {
                @Override
                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    listener.onSuccess(resource);
                    return false;
                }
            }).into(500, 500).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
