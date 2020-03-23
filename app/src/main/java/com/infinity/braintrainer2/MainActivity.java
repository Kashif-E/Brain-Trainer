package com.infinity.braintrainer2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView timer;
    TextView score;
    TextView problem;
    GridLayout answersView;
    TextView result;
    int ans;
    int a=0, b=0,c=0;
    Random random;
   int question=0,rightAns,right =0;


    public  void go (View view)
    {
        goButton = findViewById(view.getId());
        goButton.setVisibility(View.INVISIBLE);
        timer= findViewById(R.id.timer);
        timer.setVisibility(View.VISIBLE);
        score=findViewById(R.id.score);
        score.setVisibility(View.VISIBLE);
        problem=findViewById(R.id.questions);
        problem.setVisibility(View.VISIBLE);
        result=findViewById(R.id.result);
        answersView=findViewById(R.id.answersLayout);
        answersView.setVisibility(View.VISIBLE);
        Button plaAgainButton = findViewById(R.id.playAgainButton);
        plaAgainButton.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);

        if (goButton.getText().equals("Play Again"))
        {
            score.setText("0/0");
            question=0;
            rightAns=0;
            right=0;
            new CountDownTimer(30200, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    ((TextView)findViewById(R.id.timer)).setText(Long.toString(millisUntilFinished/1000)+ "s");


                }

                @Override
                public void onFinish() {
                    result.setText("Your Score "+right);
                    endGame();

                }
            }.start();

        }
       generateQuestion();




    }

    private void generateQuestion() {

        random= new Random();
        a= random.nextInt(21);
        b=random.nextInt(21);
        c=random.nextInt(4);



        switch (c)
        {
            case 0:
            {
                problem.setText(a + " + "+b);
                ans=a+b;


                break;

            }
            case 1:
            {
                problem.setText(a + " - "+b);
                ans=a-b;

                break;
            }
            case 2:
            {
                problem.setText(a + " * "+b);
                ans=a*b;
                break;
            }
            case 3:
            {
                problem.setText(a + " * "+b);
                ans=a*b;
                break;
            }

        }
        System.out.println("c" +c );
        System.out.println("ans "+ ans);

        Button[] buttons= {findViewById(R.id.ans1), findViewById(R.id.ans2),findViewById(R.id.ans3),findViewById(R.id.ans4)};

        rightAns= random.nextInt(3);

        System.out.println("righ ans " + rightAns);

        for (int i=0 ; i<=3;i++)
        {
            if (i!=rightAns)
            {

                if(ans>=0) {
                    buttons[i].setText(Integer.toString(random.nextInt(ans + 2)));
                }
                else
                {
                    buttons[i].setText(Integer.toString(random.nextInt(ans * -1 )));
                }
            }
            else
            {
                System.out.println(ans);
                buttons[i].setText(Integer.toString(ans));
            }

        }


    }

    public  void getAnswer(View view)
    {
        Button clickedButton = findViewById(view.getId());
        String answered = clickedButton.getText().toString();

        if (answered.equals(Integer.toString(ans)))
        {
            question++;
            right++;
            result.setText("CORRECT! :) ");
            result.setVisibility(View.VISIBLE);
        }
        else
        {
            question++;
            result.setVisibility(View.VISIBLE);
            result.setText("WRONG!!!");


        }

        score.setText(right+"/"+question);


        generateQuestion();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        new CountDownTimer(30200, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                ((TextView)findViewById(R.id.timer)).setText(Long.toString(millisUntilFinished/1000)+ "s");


            }

            @Override
            public void onFinish() {
                result.setText("Your Score "+right);
                endGame();

            }
        }.start();
    }

    private void endGame() {
        timer.setVisibility(View.INVISIBLE);

        score.setVisibility(View.INVISIBLE);
        problem.setVisibility(View.INVISIBLE);

        answersView=findViewById(R.id.answersLayout);
        answersView.setVisibility(View.INVISIBLE);
        Button button= findViewById(R.id.playAgainButton);
        button.setVisibility(View.VISIBLE);
    }
}
