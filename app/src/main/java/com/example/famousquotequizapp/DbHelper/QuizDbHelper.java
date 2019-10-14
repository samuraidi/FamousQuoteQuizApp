package com.example.famousquotequizapp.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.famousquotequizapp.LoginContract;
import com.example.famousquotequizapp.Question;
import com.example.famousquotequizapp.QuestionContract;

import java.util.ArrayList;
import java.util.List;

class QuizDbHelper extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "QuoteQuiz.db";
    protected static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE IF NOT EXISTS " +
                LoginContract.LoginTable.TABLE_NAME + " ( " +
                LoginContract.LoginTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LoginContract.LoginTable.COLUMN_FIRSTNAME + " TEXT, " +
                LoginContract.LoginTable.COLUMN_LASTNAME + " TEXT, " +
                LoginContract.LoginTable.COLUMN_USERNAME + " TEXT, " +
                LoginContract.LoginTable.COLUMN_PASSWORD + " TEXT" +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                QuestionContract.QuestionTable.TABLE_NAME + " ( " +
                QuestionContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionContract.QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionContract.QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionContract.QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionContract.QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionContract.QuestionTable.COLUMN_ANSWER + " TEXT, " +
                QuestionContract.QuestionTable.COLUMN_BOOLEAN_ANSWER + " TEXT" +
                ")";

                db.execSQL(SQL_CREATE_LOGIN_TABLE);
                db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
                fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + LoginContract.LoginTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + QuestionContract.QuestionTable.TABLE_NAME);
            onCreate(db);
        }
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Tell me and I forget. Teach me and I remember. Involve me and I learn.", "Benjamin Franklin", "B", "C", "Benjamin Franklin","true");
        addQuestion(q1);
        Question q2 = new Question("It is during our darkest moments that we must focus to see the light.", "A", "Aristotle", "C", "Aristotle", "false");
        addQuestion(q2);
        Question q3 = new Question("When you reach the end of your rope, tie a knot in it and hang on.", "A", "B", "Franklin D. Roosevelt", "Franklin D. Roosevelt", "true");
        addQuestion(q3);
        Question q4 = new Question("In the end, it's not the years in your life that count. It's the life in your years.", "A", "Abraham Lincoln", "C", "Abraham Lincoln", "true");
        addQuestion(q4);
        Question q5 = new Question("The greatest glory in living lies not in never falling, but in rising every time we fall.", "A", "B", "Nelson Mandela", "Nelson Mandela", "false");
        addQuestion(q5);
        Question q6 = new Question("The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt", "B", "C", "Eleanor Roosevelt", "false");
        addQuestion(q6);
        Question q7 = new Question("Life is really simple, but we insist on making it complicated.", "A", "Confucius", "C", "Confucius", "false");
        addQuestion(q7);
        Question q8 = new Question("Love the life you live. Live the life you love.", "A", "B", "Bob Marley", "Bob Marley", "true");
        addQuestion(q8);
        Question q9 = new Question("Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma -- which is living with the results of other people's thinking", "A", "Steve Jobs", "C", "Steve Jobs", "true");
        addQuestion(q9);
        Question q10 = new Question("Success is not final; failure is not fatal: It is the courage to continue that counts.", "Winston S. Churchill", "B", "C", "Winston S. Churchill", "false");
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(QuestionContract.QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionContract.QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionContract.QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionContract.QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionContract.QuestionTable.COLUMN_ANSWER, question.getAnswer());
        cv.put(QuestionContract.QuestionTable.COLUMN_BOOLEAN_ANSWER, question.getAnswer());
        db.insert(QuestionContract.QuestionTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {

        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionContract.QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionContract.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionContract.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionContract.QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionContract.QuestionTable.COLUMN_OPTION3)));
                question.setAnswer(c.getString(c.getColumnIndex(QuestionContract.QuestionTable.COLUMN_ANSWER)));
                question.setBooleanAnswer(c.getString((c.getColumnIndex(QuestionContract.QuestionTable.COLUMN_BOOLEAN_ANSWER))));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
