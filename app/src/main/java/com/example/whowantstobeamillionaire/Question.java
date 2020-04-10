package com.example.whowantstobeamillionaire;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Session2Command;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Question extends AppCompatActivity {
private TextView q;
    AlertDialog.Builder builder;
    int loop=0;
    private TextView a1;
    private TextView a2;
    private TextView a3;
    private TextView a4;
    public String result;
    public SharedPreferences pref;
    int set=-1;
    private boolean isCorrect;
    private boolean isAnswered;
    int prize[]={100,500,1000,2000,4000,5000,7000,9000,10000,50000};
    private TextView pri;
    Qclass qu;
    public    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
       qu=new Qclass();
          q=(TextView) findViewById(R.id.question);
          a1=findViewById(R.id.ansA);
        a2=findViewById(R.id.ansB);
        a3=findViewById(R.id.ansC);
        a4=findViewById(R.id.ansD);
        pri=findViewById(R.id.prize);
displayQ();



    }

    public void displayQ(){
        pref = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);

        String loopst=pref.getString("loop", "");
        loop=Integer.parseInt(loopst);
        pri.setText("$"+ String.valueOf(prize[loop]));
        q.setText(String.valueOf(qu.mQuestions[loop]));
        a1.setText(String.valueOf(qu.mChoices[loop][0]));
        a2.setText(String.valueOf(qu.mChoices[loop][1]));
        a3.setText(String.valueOf(qu.mChoices[loop][2]));
        a4.setText(String.valueOf(qu.mChoices[loop][3]));
    }
    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {
                            Intent intent = new Intent(Question.this, MainActivity.class);
                            startActivity(intent);
                                  Toast.makeText(Question.this,
                                    getString(R.string.exit_toast_msg),
                                    Toast.LENGTH_LONG).show();


                        }
                    }
                };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.are_you_sure))
                .setTitle(R.string.exit_game_title)
                .setPositiveButton("", dialogClickListener)
                .setNegativeButton("", dialogClickListener)
                .setIcon(R.drawable.ic_monetization_on_black_24dp)
                .setPositiveButtonIcon(getDrawable(R.drawable.ic_check_black_24dp))
                .setNegativeButtonIcon(getDrawable(R.drawable.ic_close_black_24dp))
                .show();
    }

   public void submit(View view){
       if (isAnswered) {
           Toast.makeText(Question.this,
                   getString(R.string.confirmedAlready),
                   Toast.LENGTH_LONG).show();
           return;
       }


       final TextView selectedButton = (TextView) view;


       DialogInterface.OnClickListener dialogClickListener =
               new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int button) {
                       if (button == DialogInterface.BUTTON_POSITIVE) {

                           if (selectedButton.getText().toString().equals(qu.mCorrectAnswers[loop])) {
                               selectedButton.setBackgroundResource(R.drawable.c);
                               isCorrect = true;

                               Toast.makeText(Question.this,
                                       getString(R.string.confirmed),
                                       Toast.LENGTH_LONG).show();
                           } else {
                               selectedButton.setBackgroundResource(R.drawable.ans_org);

                               Toast.makeText(Question.this,
                                       getString(R.string.confirmed_wrong),
                                       Toast.LENGTH_LONG).show();
                               finish();
                               Intent intent = new Intent(Question.this, Result.class);
                               intent.putExtra("level", loop);
                               intent.putExtra("prize",prize[loop]);
                               intent.putExtra("loop",loop);
                               startActivity(intent);
                           }
                           isAnswered = true;
                       }

                   }
               };

       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setMessage("ARE YOU SURE?")
               .setTitle("CONFIRM")
               .setPositiveButton("Yes", dialogClickListener)
               .setNegativeButton("No", dialogClickListener)
               .setIcon(R.drawable.ic_monetization_on_black_24dp)
               //.setPositiveButtonIcon(getDrawable(R.drawable.ic_check_black_24dp))
              // .setNegativeButtonIcon(getDrawable(R.drawable.ic_close_black_24dp))
               .show();


   }

     public void goto_next_question(View view){

         if(!isAnswered)
             return;
         if(!isCorrect){
             finish();
             Intent intent = new Intent(this, Result.class);
             pref = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
             String loopst=pref.getString("loop", "");
             loop=Integer.parseInt(loopst);
             intent.putExtra("level", ++loop);
             intent.putExtra("prize",prize[loop]);
             intent.putExtra("loop",++loop);
             startActivity(intent);
         } else {
           ++loop;
           if(loop<=9){
             Intent intent = new Intent(this, Question.class);
             pref = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
             editor = pref.edit();
             editor.putString("loop",String.valueOf(loop));
             editor.commit();
             startActivity(intent);}
           else{
               finish();
               Intent intent = new Intent(this, Result.class);
               pref = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
               String loopst=pref.getString("loop", "");
               loop=Integer.parseInt(loopst);
               intent.putExtra("level", ++loop);
               intent.putExtra("prize",prize[9]);
               intent.putExtra("loop",++loop);
               startActivity(intent);
           }
         }
     }
    }


