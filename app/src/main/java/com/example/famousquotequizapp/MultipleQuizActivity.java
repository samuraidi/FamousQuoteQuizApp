package com.example.famousquotequizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.famousquotequizapp.DbHelper.QuestionDbHelper;
import com.example.famousquotequizapp.DbHelper.QuizDbHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.List;

public class MultipleQuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private Button b1;
    private Button b2;
    private Button b3;

    public QuizDbHelper dbHelper;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCounterTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_quiz);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);

        b1 = findViewById(R.id.button_option1);
        b2 = findViewById(R.id.button_option2);
        b3 = findViewById(R.id.button_option3);

        QuestionDbHelper dbHelper = new QuestionDbHelper(this);
//        dbHelper.smo();
        questionList = dbHelper.getAllQuestions();
        questionCounterTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.test_24:
                        Intent intentTest = new Intent(MultipleQuizActivity.this, BinaryQuizActivity.class);
                        startActivity(intentTest);
                        break;
                    case R.id.settings:
                        Intent intentSettings = new Intent(MultipleQuizActivity.this, SettingsActivity.class);
                        startActivity(intentSettings);
                        break;
                    case R.id.profile:
                        Intent intentProfile = new Intent(MultipleQuizActivity.this, BinaryQuizActivity.class);
                        break;
                }
                return true;
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered) {
                        checkAnswer(b1.getText().toString());
//                        showNextQuestion();
                }
                /*else {
                    showNextQuestion();
                }*/
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered) {
                    checkAnswer(b2.getText().toString());
 //                   showNextQuestion();
                }
                /*else {
                    showNextQuestion();
                }*/
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered) {
                    checkAnswer(b3.getText().toString());
//                    showNextQuestion();
                }
                /*else {
                    showNextQuestion();
                }*/
            }
        });
    }

    private void checkAnswer(String answer){
        answered = true;

        if (answer.equals(currentQuestion.getAnswer())) {
            score++;
            textViewScore.setText("Score: " + score);
            Toast.makeText(MultipleQuizActivity.this, "Correct! The right answer is: " + currentQuestion.getAnswer(), Toast.LENGTH_SHORT).show();
            showNextQuestion();
        } else {
            Toast.makeText(MultipleQuizActivity.this, "Sorry, you are wrong!The right answer is: " + currentQuestion.getAnswer(), Toast.LENGTH_SHORT).show();
            showNextQuestion();
        }
    }

    private void showNextQuestion() {

        if(questionCounter < questionCounterTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            b1.setText(currentQuestion.getOption1());
            b2.setText(currentQuestion.getOption2());
            b3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCounterTotal);
            answered = false;
        } else {
//            finishQuiz();
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
