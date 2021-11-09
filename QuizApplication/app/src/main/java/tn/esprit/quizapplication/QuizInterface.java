package tn.esprit.quizapplication;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.quizapplication.entity.QuestionsBank;


public class QuizInterface extends AppCompatActivity {

    private String selectedTopicName="";
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_interface);

        //QuestionsBank.injectQuestions(this);

        final LinearLayout football=findViewById(R.id.footballLayout);
        final LinearLayout basketball=findViewById(R.id.basketballLayout);
        final LinearLayout tennis=findViewById(R.id.tennisLayout);
        final LinearLayout volley=findViewById(R.id.volleyLayout);

        final Button startBtn= findViewById(R.id.BtnStartQuiz);

       String idConnectedUser=getIntent().getStringExtra("idConnectedUser");

        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName="Football";
                football.setBackgroundResource(R.drawable.round_back_white_stroke);
                basketball.setBackgroundResource(R.drawable.round_back_white_10);
                tennis.setBackgroundResource(R.drawable.round_back_white_10);
                volley.setBackgroundResource(R.drawable.round_back_white_10);
            }
        });


        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName="Basketball";
                basketball.setBackgroundResource(R.drawable.round_back_white_stroke);
                football.setBackgroundResource(R.drawable.round_back_white_10);
                tennis.setBackgroundResource(R.drawable.round_back_white_10);
                volley.setBackgroundResource(R.drawable.round_back_white_10);
            }
        });


        tennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName="Tennis";
                tennis.setBackgroundResource(R.drawable.round_back_white_stroke);
                basketball.setBackgroundResource(R.drawable.round_back_white_10);
                football.setBackgroundResource(R.drawable.round_back_white_10);
                volley.setBackgroundResource(R.drawable.round_back_white_10);
            }
        });


        volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTopicName="Volleyball";
                volley.setBackgroundResource(R.drawable.round_back_white_stroke);
                basketball.setBackgroundResource(R.drawable.round_back_white_10);
                tennis.setBackgroundResource(R.drawable.round_back_white_10);
                football.setBackgroundResource(R.drawable.round_back_white_10);
            }
        });


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTopicName.isEmpty())
                    Toast.makeText(QuizInterface.this, "Please select a category!", Toast.LENGTH_SHORT).show();
                else{
                    Intent intent= new Intent(QuizInterface.this,LevelActivity.class);
                    intent.putExtra("selectedTopicName",selectedTopicName);
                    intent.putExtra("idConnectedUser",idConnectedUser);
                    startActivity(intent);
                }
            }
        });

        navView = findViewById(R.id.bottom_navigation);
        navView.setOnItemSelectedListener(item -> {
            if(item.getItemId()== R.id.navigation_play){
                Intent intent = new Intent(this, QuizInterface.class);
                intent.putExtra("idConnectedUser",idConnectedUser);
                this.startActivity(intent);
                return true;
            }
            if(item.getItemId()== R.id.navigation_account){
                Intent intent1 = new Intent(this, ProfileActivity.class);
                intent1.putExtra("idConnectedUser",idConnectedUser);
                this.startActivity(intent1);
                return true;
            }

            return true;
        });
    }
}
