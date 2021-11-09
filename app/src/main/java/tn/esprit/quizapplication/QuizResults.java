package tn.esprit.quizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

public class QuizResults extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        final AppCompatButton startNewBtn= findViewById(R.id.startNewQuizBtn);
        final TextView correctAnswer=findViewById(R.id.correctAnswer);
        final TextView incorrectAnswer=findViewById(R.id.incorrectAnswer);
        final int getCorrectAnswers=getIntent().getIntExtra("correct",0);
        final int getInCorrectAnswers=getIntent().getIntExtra("incorrect",0);


        correctAnswer.setText("Correct answer: "+String.valueOf(getCorrectAnswers));
        incorrectAnswer.setText("Wrong answer: "+String.valueOf(getInCorrectAnswers));

        startNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResults.this,QuizInterface.class));
                finish();
            }
        });
    }

    public void onBackPressed(){
        startActivity(new Intent(QuizResults.this,QuizInterface.class));
        finish();
    }
}