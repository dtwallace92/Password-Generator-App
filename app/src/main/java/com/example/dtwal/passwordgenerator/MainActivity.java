package com.example.dtwal.passwordgenerator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ExecutorService threadPool;
    SeekBar topSeekbar;
    SeekBar botSeekbar;
    TextView passCount;
    TextView passLength;
    Button threadButton;
    ArrayList<String> a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = new ArrayList<String>();
        passCount = findViewById(R.id.textView4);
        passLength = findViewById(R.id.textView5);
        topSeekbar = findViewById(R.id.seekBar);
        botSeekbar = findViewById(R.id.seekBar2);
        threadPool = Executors.newFixedThreadPool(2);
        threadButton = findViewById(R.id.button);

        //top seekbar method
        topSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                passCount.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //bot seekbar method
        botSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                passLength.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        threadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoWork dowork = new DoWork (Integer.parseInt(passLength.getText().toString()), Integer.parseInt(passCount.getText().toString()));
                threadPool.execute(dowork);
                a = dowork.returnList();
                AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Passwords").setItems(a, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }


            }
        });

    }

    class DoWork implements Runnable {

        int p; //passlength
        int c; //passcount
        ArrayList passwords = new ArrayList<String>();


        public DoWork(int i, int j) {
            p = i;
            c = j;

        }

        public ArrayList<String> returnList() {
            return passwords;
        }

        @Override
        public void run() {
            for (int i = 0; i < c; i++) {
                passwords.add(Util.getPassword(p));
            }


        }
    }}
