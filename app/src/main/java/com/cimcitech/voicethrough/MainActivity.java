package com.cimcitech.voicethrough;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Vibrator vibrator;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        editText = this.findViewById(R.id.edit_tv);

        this.findViewById(R.id.call_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //vibrator.vibrate(2000);
                String phone = "18666660749";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        this.findViewById(R.id.start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhoneCallStateService.class);
                startService(intent);// 启动服务
            }
        });
    }
}

