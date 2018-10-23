package com.example.alamin.preferencehomework;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LinearLayout layout;
    int score;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= findViewById(R.id.textview);
        layout= findViewById(R.id.layout);

        //adding external font family
        Typeface font= Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf"); //fonts is a directory folder
        textView.setTypeface(font); //set font object to Textview
        loadData();
    }

    private void loadData() {
        SharedPreferences sharedPreferences= getSharedPreferences("db", MODE_PRIVATE);
        int lastScore= sharedPreferences.getInt("score", 0);
        textView.setText(String.valueOf(lastScore));

        sharedPreferences= getSharedPreferences("idb", MODE_PRIVATE);
        int lastImage= sharedPreferences.getInt("image",0);
        layout.setBackgroundResource(lastImage);
    }

    public void actionButton(View view) {
        if(view.getId()==R.id.increment){
            int score= Integer.parseInt(textView.getText().toString());
            score++;
            textView.setText(String.valueOf(score));
            saveScore(score);
        }else if(view.getId()==R.id.decrement){
            int score= Integer.parseInt(textView.getText().toString());
            score--;
            textView.setText(String.valueOf(score));
            saveScore(score);
        }
    }

    private void saveScore(int score) {
        SharedPreferences sharedPreferences= getSharedPreferences("db", MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("score", score).commit();
    }

    public void changeBackground(View view) {
        int pic;
        if(count==0){
            pic= R.drawable.one;
        }else if(count==1){
            pic= R.drawable.two;
        }else{
            pic=R.drawable.three;
        }
        layout.setBackgroundResource(pic);
        if(count==2){
            count=0;
        }else{
            count++;
        }
        //save preference
        SharedPreferences sharedPreferences= getSharedPreferences("idb", MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("image", pic).commit();
    }
}
