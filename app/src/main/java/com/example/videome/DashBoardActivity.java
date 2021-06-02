package com.example.videome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashBoardActivity extends AppCompatActivity {
    TextView call;
    EditText code;
    Button codebox;
    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        call=findViewById(R.id.call);
        code=findViewById(R.id.code);
        codebox=findViewById(R.id.codebox) ;
        share=findViewById(R.id.share);

        URL serverUrl;

        try {
            serverUrl=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultoptions= new JitsiMeetConferenceOptions.Builder()
                    .setRoom(code.getText().toString())
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultoptions);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }




        codebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options= new JitsiMeetConferenceOptions.Builder()
                        .setRoom(code.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(DashBoardActivity.this,options);
            }
        });
    }
}