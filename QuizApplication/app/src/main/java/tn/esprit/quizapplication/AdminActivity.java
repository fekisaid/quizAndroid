package tn.esprit.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tn.esprit.quizapplication.database.MyDataBase;

public class AdminActivity extends AppCompatActivity {

    RelativeLayout listUsers;
    RelativeLayout addQ;
    RelativeLayout listQ;
    Button logout;
    TextView nbrUser;
    TextView nbrQuest;
    MyDataBase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listUsers=findViewById(R.id.userList);
        logout=findViewById(R.id.btn_logout);
        addQ=findViewById(R.id.addQuestion);
        listQ=findViewById(R.id.questionList);
        nbrUser=findViewById(R.id.nbrUsers);
        nbrQuest=findViewById(R.id.nombreQuest);

        myDB=MyDataBase.getDataBase(this);

        nbrUser.setText("Total users: "+myDB.userDAO().getNombreUser());
        nbrQuest.setText("Total questions: "+myDB.questionListDAO().getNombreQuestion());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        listUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminActivity.this,ListUserActivity.class);
                startActivity(intent);
            }
        });

        addQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminActivity.this,AddQuestionActivity.class);
                startActivity(intent);
            }
        });

        listQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminActivity.this,ListQuestionActivity.class);
                startActivity(intent);
            }
        });
    }
}