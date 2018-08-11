package com.example.android.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Question[] questions = {
        new Question("10+25=45?",false),
        new Question("34+5=39?",true)
        new Question("24-5=19?",true),
        new Question("4*5=21?",false),
        new Question("14/2=7?",true)
    };
    int totalQuestions = questions.length;
    TextView question;
    TextView questionid;
    RelativeLayout finishView;
    Button trueButton;
    Button falseButton;
    TextView scoreView;
    int currentQuestion = 0;
    int score=0;
    Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView)findViewById(R.id.question);
        trueButton = findViewById(R.id.truebutton);
        falseButton = findViewById(R.id.falsebutton);
        scoreView = findViewById(R.id.score);
        questionid = findViewById(R.id.question_id);
        finishView = findViewById(R.id.finish);
        restartButton = findViewById(R.id.restart);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questions[currentQuestion].getAnswer()){
                    score++;
                    scoreView.setText("Score:"+String.valueOf(score));
                }
                nextQuestion();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!questions[currentQuestion].getAnswer()){
                    score++;
                    scoreView.setText("Score:"+String.valueOf(score));

                    //Toast.makeText(MainActivity.this,"You are correct!",Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(MainActivity.this,"You are wrong!",Toast.LENGTH_SHORT).show();

                }
                nextQuestion();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestion = 0;
                score = 0;
                finishView.setVisibility(View.GONE);
                question.setText(questions[0].getQuestion().toString());
                scoreView.setText("Score:"+String.valueOf(score));
                questionid.setText(currentQuestion+1+" of "+totalQuestions);

            }
        });
        question.setText(questions[0].getQuestion().toString());
        scoreView.setText("Score:"+String.valueOf(score));
        questionid.setText(currentQuestion+1+" of "+totalQuestions);
        finishView.setVisibility(View.GONE);

    }

    private void nextQuestion(){
        currentQuestion++;
        if(currentQuestion>=totalQuestions){
            //end of questions
            finishView.setVisibility(View.VISIBLE);
            return;
        }


        question.setText(questions[currentQuestion].getQuestion().toString());
        questionid.setText(currentQuestion+1+" of "+totalQuestions);
    }
}
