package tn.esprit.quizapplication;

import android.content.Intent;
import android.graphics.Color;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.AppCompatButton;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.QuestionList;
import tn.esprit.quizapplication.entity.QuestionsBank;

public class QuizActivity extends AppCompatActivity {

    private Timer quizTimer;
    private TextView questions;
    private TextView question;
    private TextView topicName;
    private AppCompatButton option1,option2,option3,option4;
    private AppCompatButton nextBtn;
    private int totalTimeInMins = 1;
    private int seconds=0;
    private List<QuestionList> questionsList;
    private int currentQuestionPosition=0;
    private String selectedOptionByUser="";
    String idConnectedUser="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final String getSelectTopicName = getIntent().getStringExtra("selectedTopicName");
        final String getSelectTopicLevel = getIntent().getStringExtra("selectedTopicLevel");
        idConnectedUser = getIntent().getStringExtra("idConnectedUser");

        topicName=findViewById(R.id.topicName);
        topicName.setText(getSelectTopicName);

        final ImageView backBtn= findViewById(R.id.backBtn);

        final TextView timer= findViewById(R.id.timer);
        final TextView topicName= findViewById(R.id.topicName);

        questions=findViewById(R.id.questions);
        question=findViewById(R.id.question);

        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);
        nextBtn=findViewById(R.id.nextBtn);

        MyDataBase myDB=MyDataBase.getDataBase(this);
        questionsList= myDB.questionListDAO().getQestionByTopicAndLevel(getSelectTopicName,getSelectTopicLevel);
        startTimer(timer,idConnectedUser,getSelectTopicName,getSelectTopicLevel);

        questions.setText(currentQuestionPosition+1+"/"+questionsList.size());
        question.setText(questionsList.get(0).getQuestion());
        option1.setText(questionsList.get(0).getOption1());
        option2.setText(questionsList.get(0).getOption2());
        option3.setText(questionsList.get(0).getOption3());
        option4.setText(questionsList.get(0).getOption4());

        if((currentQuestionPosition+1)==questionsList.size()){
            nextBtn.setText("Submit Quiz");
        }


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser=option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser=option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });


        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser=option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });


        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser=option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    option4.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
                else{
                    changeNextQuestion(idConnectedUser,getSelectTopicName,getSelectTopicLevel);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(QuizActivity.this,LevelActivity.class);
                intent.putExtra("selectedTopicName", getSelectTopicName);
                intent.putExtra("idConnectedUser",idConnectedUser);
                startActivity(intent);
                finish();
            }
        });

    }

    private void changeNextQuestion(String idConnectedUser,String getSelectTopicName, String getSelectTopicLevel){
        currentQuestionPosition++;

        if((currentQuestionPosition+1)==questionsList.size()){
            nextBtn.setText("Submit Quiz");
        }
        if(currentQuestionPosition<questionsList.size()){
            selectedOptionByUser="";

            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#1F6BB8"));

            questions.setText(currentQuestionPosition+1+"/"+questionsList.size());
            question.setText(questionsList.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsList.get(currentQuestionPosition).getOption1());
            option2.setText(questionsList.get(currentQuestionPosition).getOption2());
            option3.setText(questionsList.get(currentQuestionPosition).getOption3());
            option4.setText(questionsList.get(currentQuestionPosition).getOption4());
        }
        else{
            Intent intent= new Intent(QuizActivity.this,QuizResults.class);
            intent.putExtra("correct",getCorrectAnswers());
            intent.putExtra("incorrect",getInCorrectAnswers());
            intent.putExtra("selectedTopicName", getSelectTopicName);
            intent.putExtra("selectedTopicLevel", getSelectTopicLevel);
            intent.putExtra("idConnectedUser",idConnectedUser);
            startActivity(intent);
            finish();
        }
    }

    private void startTimer(TextView timerTextView,String idConnectedUser,String getSelectTopicName, String getSelectTopicLevel){
        long duration= TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration= String.format(Locale.ENGLISH,"%02d:%02d"
                        ,TimeUnit.MILLISECONDS.toMinutes(l)
                        ,TimeUnit.MILLISECONDS.toSeconds(l)-
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                timerTextView.setText(sDuration);
            }

            @Override
            public void onFinish() {
                timerTextView.setVisibility(View.GONE);
                Toast.makeText(QuizActivity.this, "Time Over", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(QuizActivity.this,QuizResults.class);
                    intent.putExtra("correct",getCorrectAnswers());
                    intent.putExtra("incorrect",getInCorrectAnswers());
                    intent.putExtra("selectedTopicName", getSelectTopicName);
                    intent.putExtra("selectedTopicLevel", getSelectTopicLevel);
                    intent.putExtra("idConnectedUser",idConnectedUser);
                    startActivity(intent);
                    finish();
            }
        }.start();
    }

    private int getCorrectAnswers(){
        int correctAnswer =0;
        for (int i=0;i<questionsList.size();i++){
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer= questionsList.get(i).getAnswer();

            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAnswer++;
            }
        }
        return correctAnswer;
    }

    private int getInCorrectAnswers(){
        int correctAnswer =0;
        for (int i=0;i<questionsList.size();i++){
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer= questionsList.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswer++;
            }
        }
        return correctAnswer;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QuizActivity.this, QuizInterface.class);
        intent.putExtra("idConnectedUser",idConnectedUser);
        startActivity(intent);
        finish();
    }

    private void revealAnswer(){
        final String getAnswer=questionsList.get(currentQuestionPosition).getAnswer();
        if(option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        }
        else if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        }
        else if(option4.getText().toString().equals(getAnswer)){
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }
    }
}