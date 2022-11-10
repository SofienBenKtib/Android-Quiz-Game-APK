package com.sofien.newquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestions;
    TextView question;
    Button answerA,answerB,answerC,answerD;
    Button submit;
    //TextView currentQuest;

    int score=0;
    int totalQuestionsNbr= QuestionAnswers.question.length;
    int currentQuestion=0;
    String selectedAnswer="";
    int uselessVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.splashScreen);
        setContentView(R.layout.activity_main);

        totalQuestions=findViewById(R.id.totalQuestions);
        question=findViewById(R.id.question);
        answerA=findViewById(R.id.answerA);
        answerB=findViewById(R.id.answerB);
        answerC=findViewById(R.id.answerC);
        answerD=findViewById(R.id.answerD);
        submit=findViewById(R.id.submit);
        //currentQuest=findViewById(R.id.currentQuest);
        //currentQuest.setText("Current question number : ");

        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestions.setText("Total questions : " + totalQuestionsNbr);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {

        answerA.setBackgroundColor(Color.WHITE);
        answerB.setBackgroundColor(Color.WHITE);
        answerC.setBackgroundColor(Color.WHITE);
        answerD.setBackgroundColor(Color.WHITE);

        Button clickedButton=(Button) view;
        if(clickedButton.getId()==R.id.submit){
            if(selectedAnswer.equals(QuestionAnswers.correctAnswer[currentQuestion])){
                score++;
            }
            currentQuestion++;
            loadNewQuestion();
        }else{
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.LTGRAY);
        }
    }

    void loadNewQuestion(){
        if(currentQuestion == totalQuestionsNbr){
            finishQuiz();
            return;
        }
        question.setText(QuestionAnswers.question[currentQuestion]);
        answerA.setText(QuestionAnswers.choices[currentQuestion][0]);
        answerB.setText(QuestionAnswers.choices[currentQuestion][1]);
        answerC.setText(QuestionAnswers.choices[currentQuestion][2]);
        answerD.setText(QuestionAnswers.choices[currentQuestion][3]);
    }

    void finishQuiz(){
        String passStatus="";
        if(score>=totalQuestionsNbr*0.5){
            passStatus="Passed !";
        }else{
            passStatus="Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your score is : " + score + " out of " + totalQuestionsNbr)
                .setPositiveButton("Restart",(dialogInterface,i)->restartQuiz())
                .setCancelable(false)
                .show();


    }

        void restartQuiz(){
            score=0;
            currentQuestion=0;
            loadNewQuestion();
        }
}