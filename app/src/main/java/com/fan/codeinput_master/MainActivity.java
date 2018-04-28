package com.fan.codeinput_master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;

import com.fan.corelibrary.InputCode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputCode editText =  findViewById(R.id.edit_text);
        editText.setListener(new InputCode.OnInputCompleteListener() {
            @Override
            public void onComplete(String code) {
                Log.e("----", code);
            }
        });
    }
}
