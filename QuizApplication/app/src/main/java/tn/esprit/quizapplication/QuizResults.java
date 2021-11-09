package tn.esprit.quizapplication;

import android.content.Intent;

import android.os.Bundle;
//import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
//import android.support.v7.widget.AppCompatButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Date;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.HistoriqueUser;


public class QuizResults extends AppCompatActivity {

    MyDataBase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);


        final AppCompatButton startNewBtn= findViewById(R.id.startNewQuizBtn);
        final TextView correctAnswer=findViewById(R.id.correctAnswer);
        final TextView incorrectAnswer=findViewById(R.id.incorrectAnswer);
        final int getCorrectAnswers=getIntent().getIntExtra("correct",0);
        final int getInCorrectAnswers=getIntent().getIntExtra("incorrect",0);
        final String getSelectTopicName = getIntent().getStringExtra("selectedTopicName");
        final String getSelectTopicLevel = getIntent().getStringExtra("selectedTopicLevel");
        final String idConnectedUser = getIntent().getStringExtra("idConnectedUser");
        Date newDate= new Date();

        myDB= MyDataBase.getDataBase(this);

        correctAnswer.setText("Correct answer: "+String.valueOf(getCorrectAnswers));
        incorrectAnswer.setText("Wrong answer: "+String.valueOf(getInCorrectAnswers));

        HistoriqueUser hs= new HistoriqueUser(Integer.parseInt(idConnectedUser),newDate,getSelectTopicName,getSelectTopicLevel,getCorrectAnswers,getInCorrectAnswers);
        myDB.historiqueUserDAO().insertHistorique(hs);

        startNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(QuizResults.this,QuizInterface.class);
                intent.putExtra("idConnectedUser",idConnectedUser);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed(){
        startActivity(new Intent(QuizResults.this,QuizInterface.class));
        finish();
    }
}