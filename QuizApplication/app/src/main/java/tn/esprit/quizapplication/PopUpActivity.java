package tn.esprit.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import tn.esprit.quizapplication.database.MyDataBase;

public class PopUpActivity extends AppCompatActivity {

    EditText email;
    Button reset;
    MyDataBase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        myDB=MyDataBase.getDataBase(this);

        email=findViewById(R.id.inputEmail);
        reset=findViewById(R.id.btnRESET);

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width= dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.5));

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        params.flags=WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount=0.5f;
        getWindow().setAttributes(params);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail=email.getText().toString();
                if (strEmail.isEmpty()){
                    Toast.makeText(PopUpActivity.this,"Email is empty!",Toast.LENGTH_LONG).show();
                }
                else {
                    String password=generateRandomPassword();
                    myDB.userDAO().updatePasswordUserByEmail(strEmail,password);
                    sendMail(strEmail,password);
                }

            }
        });
    }




    private void sendMail(String mail,String password) {

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,"RESET YOUR PASSWORD!","Here is your new password: "+password);
        javaMailAPI.execute();

    }

    public static String generateRandomPassword() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
                +"jklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

}