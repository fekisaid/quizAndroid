package tn.esprit.quizapplication;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.QuestionList;


public class LevelActivity extends AppCompatActivity {



    String level="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Button btnEasy = findViewById(R.id.easy);
        Button btnIntermediate = findViewById(R.id.intermediate);
        Button btnHard = findViewById(R.id.hard);
        Button btnStartQuiz = findViewById(R.id.startQuiz);
        ImageView backBtn=findViewById(R.id.backBtn);

        String selectedTopicName = getIntent().getStringExtra("selectedTopicName");
        String idConnectedUser = getIntent().getStringExtra("idConnectedUser");


        btnEasy.setOnClickListener(view -> {
            level="easy";
            Toast.makeText(LevelActivity.this, "You have selected level Easy!", Toast.LENGTH_SHORT).show();
        });

        btnIntermediate.setOnClickListener(view -> {
            level="intermediate";
            Toast.makeText(LevelActivity.this, "You have selected level Intermediate!", Toast.LENGTH_SHORT).show();
        });

        btnHard.setOnClickListener(view -> {
            level="hard";
            Toast.makeText(LevelActivity.this, "You have selected level Hard!", Toast.LENGTH_SHORT).show();
        });

        btnStartQuiz.setOnClickListener(view -> {
            if(level.isEmpty())
                Toast.makeText(LevelActivity.this, "Please select a Level!", Toast.LENGTH_SHORT).show();
            else {
                MyDataBase myDB=MyDataBase.getDataBase(this);
                int nombreQ=myDB.questionListDAO().getQestionByTopicAndLevel(selectedTopicName,level).size();
                if(nombreQ==0){
                    Toast.makeText(LevelActivity.this, "No questions are available at the moment", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(LevelActivity.this, QuizActivity.class);
                    intent.putExtra("selectedTopicName", selectedTopicName);
                    intent.putExtra("selectedTopicLevel", level);
                    intent.putExtra("idConnectedUser",idConnectedUser);
                    startActivity(intent);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelActivity.this, QuizInterface.class);
                intent.putExtra("idConnectedUser",idConnectedUser);
                startActivity(intent);
                finish();
            }
        });

    }

}