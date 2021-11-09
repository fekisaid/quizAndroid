package tn.esprit.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Random;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.User;

public class MainActivity extends AppCompatActivity {

    Button loginBtn;
    TextView register;
    MyDataBase myDB;
    EditText inputEmail;
    EditText inputPassword;
    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        loginBtn= findViewById(R.id.btnLogin);
        register=findViewById(R.id.textViewRegister);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        forgot=findViewById(R.id.forgotPassword);


        myDB= MyDataBase.getDataBase(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=myDB.userDAO().getUserByEmail(inputEmail.getText().toString());
                if(user!=null){
                    if(user.getEmail().equals(inputEmail.getText().toString()) && user.getPassword().equals(inputPassword.getText().toString())){
                        if(user.getRole().equals("user")) {
                            Intent intent = new Intent(MainActivity.this, QuizInterface.class);
                            String idUser = "" + user.getId();
                            intent.putExtra("idConnectedUser", idUser);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Email or Password is invalid", Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(MainActivity.this, "Email does not exist", Toast.LENGTH_LONG).show();
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent i= new Intent(MainActivity.this,PopUpActivity.class);
             startActivity(i);

            }
        });

    }


}