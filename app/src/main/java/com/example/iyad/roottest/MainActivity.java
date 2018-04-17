package com.example.iyad.roottest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView tx=(TextView)findViewById(R.id.text);
        try
        {
            List<String> command = new ArrayList<String>();
            command.add("su");
            command.add(";");
            command.add("pm install /sdcard/Download/Showbox.apk");
            ProcessBuilder suProcess=new ProcessBuilder(command) ;
            Process pc=suProcess.start();

            BufferedReader input = new BufferedReader(new InputStreamReader(pc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(pc.getErrorStream()));
            String line="";
            tx.setText("start ");
            while ((line = input.readLine()) != null) {
                tx.setText("hi"+line);
            }
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((line = stdError.readLine()) != null) {
                tx.append(line);
            }
            tx.append(line);
            input.close();
        }catch (Exception e){
            System.err.println(e.getCause());
        }
    }
}
