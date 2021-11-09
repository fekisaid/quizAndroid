package tn.esprit.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.QuestionList;

public class EditQuestionAvtivity extends AppCompatActivity {

    String[] levels =  {"easy","intermediate","hard"};
    AutoCompleteTextView autoCompleteLevel;
    ArrayAdapter<String> adapterItems;

    String[] topics =  {"Football","Volleyball","Tennis","Basketball"};
    AutoCompleteTextView autoCompleteTopic;

    EditText question;
    String answer;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText option4;
    String level="";
    String topic="";

    SwitchCompat switchOption1;
    SwitchCompat switchOption2;
    SwitchCompat switchOption3;
    SwitchCompat switchOption4;

    Button submit;

    boolean bool1=false;
    boolean bool2=false;
    boolean bool3=false;
    boolean bool4=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question_avtivity);

        String quest = getIntent().getStringExtra("question");



        MyDataBase myDB=MyDataBase.getDataBase(this);

        QuestionList q= myDB.questionListDAO().getQyestionByQ(quest);

        autoCompleteLevel = findViewById(R.id.auto_complete_level);
        autoCompleteTopic = findViewById(R.id.auto_complete_topic);

        adapterItems = new ArrayAdapter<String>(EditQuestionAvtivity.this,R.layout.dropdown_item,levels);
        autoCompleteLevel.setAdapter(adapterItems);

        adapterItems = new ArrayAdapter<String>(EditQuestionAvtivity.this,R.layout.dropdown_item,topics);
        autoCompleteTopic.setAdapter(adapterItems);

        autoCompleteLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                level= parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Level: "+level,Toast.LENGTH_SHORT).show();
            }
        });


        autoCompleteTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                topic = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Topic: "+topic,Toast.LENGTH_SHORT).show();
            }
        });

        question=findViewById(R.id.inputQuestion);
        option1=findViewById(R.id.inputOption1);
        option2=findViewById(R.id.inputOption2);
        option3=findViewById(R.id.inputOption3);
        option4=findViewById(R.id.inputOption4);
        switchOption1=findViewById(R.id.switchOption1);
        switchOption2=findViewById(R.id.switchOption2);
        switchOption3=findViewById(R.id.switchOption3);
        switchOption4=findViewById(R.id.switchOption4);

        question.setText(q.getQuestion());
        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        option3.setText(q.getOption3());
        option4.setText(q.getOption4());

        if(q.getAnswer().equals(q.getOption1()))
            switchOption1.setChecked(true);
        if(q.getAnswer().equals(q.getOption2()))
            switchOption2.setChecked(true);
        if(q.getAnswer().equals(q.getOption3()))
            switchOption3.setChecked(true);
        if(q.getAnswer().equals(q.getOption4()))
            switchOption4.setChecked(true);

        autoCompleteTopic.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterItems = new ArrayAdapter<String>(EditQuestionAvtivity.this,R.layout.dropdown_item,topics);
                autoCompleteTopic.setAdapter(adapterItems);
                autoCompleteTopic.setHint(q.getTopic());
                topic=q.getTopic();
            }
        }, 10);

        autoCompleteLevel.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterItems = new ArrayAdapter<String>(EditQuestionAvtivity.this,R.layout.dropdown_item,levels);
                autoCompleteLevel.setAdapter(adapterItems);
                autoCompleteLevel.setHint(q.getLevel());
                level=q.getLevel();
            }
        }, 10);

        submit=findViewById(R.id.btnSubmitQ);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(question.getText().toString().equals("") || option1.getText().toString().equals("") || option2.getText().toString().equals("")
                        || option3.getText().toString().equals("") || option4.getText().toString().equals("")|| level.equals("")
                        || topic.equals("")){
                    Toast.makeText(getApplicationContext(),"No fields must be empty!",Toast.LENGTH_SHORT).show();
                }else
                {
                    bool1=switchOption1.isChecked();
                    bool2=switchOption2.isChecked();
                    bool3=switchOption3.isChecked();
                    bool4=switchOption4.isChecked();
                    int countTrue=0;
                    if(bool1)
                        countTrue=countTrue+1;
                    if(bool2)
                        countTrue=countTrue+1;
                    if(bool3)
                        countTrue=countTrue+1;
                    if(bool4)
                        countTrue=countTrue+1;

                    if(countTrue>1 || countTrue<1)
                        Toast.makeText(getApplicationContext(),"only one correct answer is accepted",Toast.LENGTH_SHORT).show();
                    else{
                        if(bool1)
                            answer=option1.getText().toString();
                        if(bool2)
                            answer=option2.getText().toString();
                        if(bool3)
                            answer=option3.getText().toString();
                        if(bool4)
                            answer=option4.getText().toString();
                    }

                    QuestionList newQ= new QuestionList(question.getText().toString(),option1.getText().toString(),option2.getText().toString()
                            ,option3.getText().toString(),option4.getText().toString(),answer,level,topic,"");
                    newQ.setId(q.getId());
                    myDB.questionListDAO().updateQuestion(newQ);
                    Toast.makeText(getApplicationContext(),"Question updated!",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(EditQuestionAvtivity.this,ListQuestionActivity.class);
                    startActivity(intent);
                }

            }
        });



    }
}