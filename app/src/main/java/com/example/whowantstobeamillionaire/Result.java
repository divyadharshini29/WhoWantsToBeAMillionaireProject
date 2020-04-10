package com.example.whowantstobeamillionaire;

import android.content.Context;
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Result extends AppCompatActivity {

    TextView res_text;
    TextView your_money;
    ImageView i ;
    int level;
    int claim =0;
int loop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
          i=findViewById(R.id.img_gif);
        res_text = (TextView) findViewById(R.id.res_text);
        level = getIntent().getExtras().getInt("level");
        claim = getIntent().getExtras().getInt("prize",0);
        loop = getIntent().getExtras().getInt("loop");
        if(level==10) {
            i.setImageResource(R.drawable.clap);
            res_text.setText("CONGRAGULATIONS, YOU ARE THE WINNER");

        }
        else{
        res_text = (TextView) findViewById(R.id.res_text);}
        your_money = (TextView) findViewById(R.id.your_money);
        your_money.setText(String.format("You won $ %s", claim));

        i = findViewById(R.id.img_gif);


    }

    public void goto_next_question(View view) {
        Intent intent = new Intent(this, Rule.class);

        startActivity(intent);

    }


}
