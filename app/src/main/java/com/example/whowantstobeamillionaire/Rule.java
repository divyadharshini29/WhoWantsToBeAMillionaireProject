package com.example.whowantstobeamillionaire;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Rule extends AppCompatActivity {
    private TextView t;
    private Button b;
    public SharedPreferences pref;
    public    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        t=findViewById(R.id.textView);
        b=(Button) findViewById(R.id.begin);
        t.setText("1.Play individually or in groups.  If you play in groups, you will want to play multiple games, or you may want to alternate questions between the groups.\n" +
                "2.Start the game.\n" +
                "3.Have the student give the answer to the question.\n" +
                "4.When the correct answer is selected, a new slide appears, click the word NEXT to move back to the main game board.\n" +
                "5.The game is over when a question is missed or a player/team reaches the $1 Million mark");


    }
    public  void click(View view){
        Intent intent = new Intent(Rule.this,Question.class);
        pref = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("loop","0");
        editor.commit();
        startActivity(intent);
    }


}
