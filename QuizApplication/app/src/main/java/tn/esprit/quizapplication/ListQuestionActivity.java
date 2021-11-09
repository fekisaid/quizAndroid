package tn.esprit.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.QuestionList;
import tn.esprit.quizapplication.entity.User;

public class ListQuestionActivity extends AppCompatActivity {

    MyDataBase myDB;
    RecyclerView prefrv;
    QuestionAdapter prefAdapter;
    List<QuestionList> elements=new ArrayList<>();

    String[] topics =  {"Football","Volleyball","BasketBall","Tennis"};
    AutoCompleteTextView autoCompleteQ;
    ArrayAdapter<String> adapterItems;

    String topic="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);

        autoCompleteQ = findViewById(R.id.auto_complete_question);

        adapterItems = new ArrayAdapter<String>(ListQuestionActivity.this,R.layout.dropdown_item,topics);
        autoCompleteQ.setAdapter(adapterItems);


        autoCompleteQ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                topic= parent.getItemAtPosition(position).toString();
                filtre(topic);
                Toast.makeText(getApplicationContext(),"Topic: "+topic,Toast.LENGTH_SHORT).show();
            }
        });

        init();

    }

    public void init(){
        myDB=MyDataBase.getDataBase(this);
        elements=myDB.questionListDAO().getAllQestion();
        prefrv=findViewById(R.id.listQuestion);
        prefAdapter=new QuestionAdapter(elements,this);
        prefrv.setAdapter(prefAdapter);
        prefrv.setLayoutManager (new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

    }

    public void filtre(String topic){
        myDB=MyDataBase.getDataBase(this);
        elements=myDB.questionListDAO().getQestionByTopic(topic);
        prefrv=findViewById(R.id.listQuestion);
        prefAdapter=new QuestionAdapter(elements,this);
        prefrv.setAdapter(prefAdapter);
        prefrv.setLayoutManager (new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListQuestionActivity.this,AdminActivity.class));
        finish();
    }
}