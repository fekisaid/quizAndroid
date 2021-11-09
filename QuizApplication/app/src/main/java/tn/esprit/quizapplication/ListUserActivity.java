package tn.esprit.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.HistoriqueUser;
import tn.esprit.quizapplication.entity.User;

public class ListUserActivity extends AppCompatActivity {

    MyDataBase myDB;
    RecyclerView prefrv;
    UserAdapter prefAdapter;
    List<User> elements=new ArrayList<>();
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        search=findViewById(R.id.search_bar);
        myDB=MyDataBase.getDataBase(this);

        init();


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.equals("")){
                    init();
                }
                else{
                    String search=query+"%";
                    setRVItems(search);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")){
                    init();
                }
                else{
                    String search=newText+"%";
                    setRVItems(search);
                }
                return false;
            }
        });
    }

    public void init(){
        myDB=MyDataBase.getDataBase(this);
        elements=myDB.userDAO().getAllUserByRole();
        prefrv=findViewById(R.id.listHistorique);
        prefAdapter=new UserAdapter(elements,this);
        prefrv.setAdapter(prefAdapter);
        prefrv.setLayoutManager (new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

    }

    public void setRVItems(String query){
        elements=myDB.userDAO().getAllUserByRoleAndName(query);
        prefrv=findViewById(R.id.listHistorique);
        prefAdapter=new UserAdapter(elements,this);
        prefrv.setAdapter(prefAdapter);
        prefrv.setLayoutManager (new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
    }
}