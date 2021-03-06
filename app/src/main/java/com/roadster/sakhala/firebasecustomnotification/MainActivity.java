package com.roadster.sakhala.firebasecustomnotification;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.value).setOnClickListener(this);
//        findViewById(R.id.value1).setOnClickListener(this);
//        findViewById(R.id.value2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.value:
//                showNotification(NotificationType.NO_ACTION_NOTIFICATION);
//                break;
//            case R.id.value1:
//                showNotification(NotificationType.EXPANDABLE_NOTIFICATION);
//                break;
//            case R.id.value2:
//                showNotification(NotificationType.DOUBLE_ACTION_NOTIFICATION);
//                break;
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void showNotification(NotificationType notificationType) {
        Intent intent = new Intent(this, MainActivity.class);
        // Send data to NotificationView Class
        intent.putExtra("title", "Hello");
        intent.putExtra("text", "What happen!!");

        ActionNotificationBuilder actionNotificationBuilder = new ActionNotificationBuilder(this, "haha");
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        AudioManager am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
        am.setStreamVolume(am.STREAM_MUSIC, amStreamMusicMaxVol, 0);
        actionNotificationBuilder.setSound(defaultSoundUri);
        actionNotificationBuilder.setTitle("Rivigo");
        actionNotificationBuilder.setSubTitle("what happen my friend you are awesome person can you please check what's issue with that, why flight got canceled");
        actionNotificationBuilder.showBigImageNotification(intent);

    }
}
