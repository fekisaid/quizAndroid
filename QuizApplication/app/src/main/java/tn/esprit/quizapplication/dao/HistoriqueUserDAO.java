package tn.esprit.quizapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.quizapplication.entity.HistoriqueUser;

@Dao
public interface HistoriqueUserDAO {

    @Insert
    void insertHistorique(HistoriqueUser historiqueUser);

    @Query("select * from historiqueuser where id_user=:iduser")
    List<HistoriqueUser> getHistoriqueUser(String iduser);


    @Query("select * from historiqueuser where id_user=:iduser and topic LIKE :search")
    List<HistoriqueUser>getHistoriqueUserByTopic(String iduser,String search);

    @Query("select count(*) from historiqueuser where id_user=:iduser")
    int getTotalPartie(String iduser);




}
