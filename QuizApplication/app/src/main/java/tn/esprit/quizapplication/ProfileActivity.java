package tn.esprit.quizapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.User;

public class ProfileActivity extends AppCompatActivity {

    TextView username;
    TextInputEditText editTextUsername;
    TextInputEditText editTextUserEmail;
    TextInputEditText editTextUserPass;
    BottomNavigationView navView;
    RelativeLayout logout;
    RelativeLayout historique;
    TextView score;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyDataBase myDB;
        ImageView prof;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username=findViewById(R.id.imgUsername);
        editTextUsername=findViewById(R.id.EditUsername);
        editTextUserEmail=findViewById(R.id.EditUserEmail);
        editTextUserPass=findViewById(R.id.EditUserPass);
        logout=findViewById(R.id.logout_layout);
        update=findViewById(R.id.btnUpdate);
        historique=findViewById(R.id.quiz_history);
        score=findViewById(R.id.quiz_history_label);

        String idConnectedUser=getIntent().getStringExtra("idConnectedUser");

        myDB= MyDataBase.getDataBase(this);
        User user=myDB.userDAO().getUserById(idConnectedUser);
        int count=myDB.historiqueUserDAO().getTotalPartie(idConnectedUser);

        score.setText(String.valueOf(count));
        username.setText(user.getUsername());
        editTextUsername.setText(user.getUsername());
        editTextUserEmail.setText(user.getEmail());
        editTextUserPass.setText(user.getPassword());
        prof=findViewById(R.id.profileImage);
        String avatar="https://ui-avatars.com/api/?name="+user.getUsername()+"&background=E2DED0&color=647C90&rounded=true";
        Picasso.get().load(avatar).into(prof);



        navView = findViewById(R.id.bottom_navigation);
        navView.setOnItemSelectedListener(item -> {
            if(item.getItemId()== R.id.navigation_play){
                Intent intent = new Intent(ProfileActivity.this, QuizInterface.class);
                intent.putExtra("idConnectedUser",idConnectedUser);
                this.startActivity(intent);
                return true;
            }
            if(item.getItemId()== R.id.navigation_account){
                Intent intent1 = new Intent(ProfileActivity.this, ProfileActivity.class);
                intent1.putExtra("idConnectedUser",idConnectedUser);
                this.startActivity(intent1);
                return true;
            }

            return true;
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent3);
            }
        });

        historique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HistoriqueActivity.class);
                intent.putExtra("idConnectedUser",idConnectedUser);
                startActivity(intent);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedUsername=editTextUsername.getText().toString();
                String updatedUserEmail=editTextUserEmail.getText().toString();
                String updatedUserPass=editTextUserPass.getText().toString();
                User updatedUser= new User(updatedUsername,updatedUserEmail,updatedUserPass);
                updatedUser.setId(Integer.parseInt(idConnectedUser));

                if(!user.equals(updatedUser)){
                    myDB.userDAO().updateUser(updatedUser);
                    Toast.makeText(ProfileActivity.this, "User updated", Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
                else{
                    Toast.makeText(ProfileActivity.this, "No modification has been made", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}