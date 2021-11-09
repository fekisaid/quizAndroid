package tn.esprit.quizapplication.database;

import android.content.Context;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import tn.esprit.quizapplication.dao.HistoriqueUserDAO;
import tn.esprit.quizapplication.dao.QuestionListDAO;
import tn.esprit.quizapplication.dao.UserDAO;
import tn.esprit.quizapplication.entity.DateConverter;
import tn.esprit.quizapplication.entity.HistoriqueUser;
import tn.esprit.quizapplication.entity.QuestionList;
import tn.esprit.quizapplication.entity.User;

@Database(entities = {User.class, HistoriqueUser.class, QuestionList.class},version = 2,exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class MyDataBase extends RoomDatabase {

    private static MyDataBase insatance;

    public abstract UserDAO userDAO();
    public abstract HistoriqueUserDAO historiqueUserDAO();
    public abstract QuestionListDAO questionListDAO();


    public static MyDataBase getDataBase(Context context){
        if(insatance == null){
            insatance= Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,"quiz_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return insatance;
    }

}