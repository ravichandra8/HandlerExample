package com.ravi.handlerexample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private Button mStartButton;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        mStartButton = (Button) findViewById(R.id.buttonStart);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgress();
            }
        });

    }
    private void startProgress() {
//      New thread to perform background operation
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 30; i++) {
                    final int currentProgressCount = i;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                  Update the value background thread to UI thread
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(currentProgressCount);
                        }
                    });
                }
            }
        }).start();
    }
}
