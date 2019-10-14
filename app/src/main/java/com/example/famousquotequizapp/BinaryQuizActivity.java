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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.List;

public class BinaryQuizActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView proposition;
    private Button b1;
    private Button b2;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCounterTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;
    private boolean answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_quiz);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        proposition = findViewById(R.id.proposition);

        b1 = findViewById(R.id.button_true);
        b2 = findViewById(R.id.button_false);

        QuestionDbHelper dbHelper = new QuestionDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCounterTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.test_24:
                        Intent intentTest = new Intent(BinaryQuizActivity.this, MultipleQuizActivity.class);
                        startActivity(intentTest);
                        break;
                    case R.id.settings:
                        Intent intentSettings = new Intent(BinaryQuizActivity.this, SettingsActivity.class);
                        startActivity(intentSettings);
                        break;
                    case R.id.profile:
                        Intent intentProfile = new Intent(BinaryQuizActivity.this, UserActivity.class);
                        break;
                }
                return true;
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if(answer == true) {
                        score++;
                        textViewScore.setText("Score: " + score);
                        Toast.makeText(BinaryQuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        showNextQuestion();
                    } else {
                        Toast.makeText(BinaryQuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                        showNextQuestion();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if(answer == false) {
                        score++;
                        textViewScore.setText("Score: " + score);
                        Toast.makeText(BinaryQuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        showNextQuestion();
                    } else {
                        Toast.makeText(BinaryQuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                        showNextQuestion();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {

        if (questionCounter < questionCounterTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            proposition.setText(currentQuestion.getOption2());
            answer = Boolean.parseBoolean(currentQuestion.getBooleanAnswer());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCounterTotal);
            answered = false;
        } else {
  //          finishQuiz();
        }
    }

    private void finishQuiz() {
        finish();
    }
}
