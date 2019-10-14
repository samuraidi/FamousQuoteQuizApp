package com.example.famousquotequizapp.DbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.famousquotequizapp.Question;
import com.example.famousquotequizapp.QuestionContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionDbHelper extends QuizDbHelper {

    public QuestionDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Question> getAllQuestions() {

        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswer(c.getString(c.getColumnIndex(QuestionTable.COLUMN_ANSWER)));
                question.setBooleanAnswer(c.getString((c.getColumnIndex(QuestionTable.COLUMN_BOOLEAN_ANSWER))));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
