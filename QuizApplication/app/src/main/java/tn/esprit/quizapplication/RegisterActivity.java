package tn.esprit.quizapplication;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.User;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    TextView signIn;
    EditText inputUserName;
    EditText inputEmail;
    EditText inputPassword;
    MyDataBase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister=findViewById(R.id.btnRegister);
        signIn=findViewById(R.id.textViewSignIn);
        inputUserName=findViewById(R.id.inputUserName);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);

        myDB= MyDataBase.getDataBase(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Création du compte user
                User userVerifyEmail=myDB.userDAO().getUserByEmail(inputEmail.getText().toString());
                if(userVerifyEmail==null){
                    User user= new User(inputUserName.getText().toString(),inputEmail.getText().toString(),inputPassword.getText().toString());
//                    User admin= new User("admin","admin","admin");
//                    admin.setRole("admin");
                    myDB.userDAO().insertUser(user);
//                    myDB.userDAO().insertUser(admin);
                    Toast.makeText(RegisterActivity.this, "User added!", Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Email already used!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}