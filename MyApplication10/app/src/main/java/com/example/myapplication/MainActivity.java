package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2;
    Button register;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        register=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        String path="http://10.0.2.2/register.php"+"?u_name="+editText1.getText().toString()+"&u_password="+editText2.getText().toString();
        class backgroundWorker extends AsyncTask<String,Void,String>{

            @Override
            protected String doInBackground(String... strings) {
                try{
                    URL url=new URL(strings[0]);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    InputStream is=conn.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
                    return  bufferedReader.readLine();
                } catch (Exception e) {
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String s) {
                if(s.equals("registered")){
                    editText1.setText("");
                    editText2.setText("");
                    textView.setTextColor(Color.parseColor("#00F080"));
                    textView.setText(s);
                }
                else {
                    editText1.setText("");
                    editText2.setText("");
                    textView.setTextColor(Color.parseColor("#FF0080"));
                    textView.setText(s);
                }
            }
        }
        backgroundWorker bw=new backgroundWorker();
        bw.execute(path);

    }
}