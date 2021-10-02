package com.ahmed.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";


    private TextView mTxtQuestion;
    private Button btnTrue;
    private Button btnWrong;

    private int mQuestionIndex;
    private int mQuizQuestion;

    private ProgressBar mProgressBar;
    private TextView mQuizStatsTextView;

    private int mUserScore;

   private QuizModel[] questionCollection = new QuizModel[] {

            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, false),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, false),

    };

    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTrue = findViewById(R.id.btnTrue);

        View.OnClickListener myClickListener = v -> Log.i("Listener", "My button is tapped");
        btnTrue.setOnClickListener(myClickListener);

        if (savedInstanceState != null) {

            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex = savedInstanceState.getInt(INDEX_KEY);


        } else {

            mUserScore = 0;
            mQuestionIndex = 0;


        }

        // first lifecycle method
        Toast.makeText(getApplicationContext(), "OnCreate method is called", Toast.LENGTH_SHORT).show();

        mTxtQuestion = findViewById(R.id.txtQuestion);
//
        QuizModel q1 = questionCollection[mQuestionIndex];

        mQuizQuestion = q1.getQuestion();

        mTxtQuestion.setText(mQuizQuestion);

        mProgressBar = findViewById(R.id.quizPB);
        mQuizStatsTextView = findViewById(R.id.txtQuizStats);
        mQuizStatsTextView.setText(mUserScore + "");


        btnTrue = findViewById(R.id.btnTrue);


        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUsersAnswer(true);
                changeQuestionOnButtonClick();

            }
        });

        btnWrong = findViewById(R.id.btnWrong);

        //  btnWrong.setOnClickListener(myClickListener);

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUsersAnswer(false);
                changeQuestionOnButtonClick();

            }
        });









        // End of on create method
    }


    private void changeQuestionOnButtonClick() {

        mQuestionIndex = (mQuestionIndex + 1) % 10 ;

        if (mQuestionIndex == 0) {

            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The quiz is finished");
            quizAlert.setMessage("Your score is " + mUserScore);
            quizAlert.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    finish();

                }
            });
            quizAlert.show();

        }

        mQuizQuestion = questionCollection[mQuestionIndex].getQuestion();

        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizStatsTextView.setText(mUserScore + "");


    }



    private void evaluateUsersAnswer(boolean userGuess) {

        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].isAnswer();

        if (currentQuestionAnswer == userGuess) {

            Toast.makeText(getApplicationContext(), R.string.correct_toast_message, Toast.LENGTH_SHORT).show();

            mUserScore = mUserScore + 1;

        } else {

            Toast.makeText(getApplicationContext(), R.string.incorrect_toast_message, Toast.LENGTH_SHORT).show();


        }

    }


    // Lifecycle methods


    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(), "OnStart method is called", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "OnResume method is called", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "OnPause method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(), "OnStop method is called", Toast.LENGTH_SHORT).show();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(), "OnDestroy method is called", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // computer - a robot
        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);

    }
}