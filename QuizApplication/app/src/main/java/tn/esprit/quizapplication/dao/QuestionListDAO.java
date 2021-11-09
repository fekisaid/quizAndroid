package tn.esprit.quizapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.quizapplication.entity.QuestionList;

@Dao
public interface QuestionListDAO {

    @Insert
    void insertQuestion(QuestionList question);

    @Update
    void updateQuestion(QuestionList question);

    @Query("select * from QuestionList")
    List<QuestionList> getAllQestion();

    @Query("select * from QuestionList where topic=:uTopic and level=:uLevel")
    List<QuestionList> getQestionByTopicAndLevel(String uTopic,String uLevel);

    @Query("select * from QuestionList where topic=:uTopic")
    List<QuestionList> getQestionByTopic(String uTopic);

    @Query("select count(*) from QuestionList")
    int getNombreQuestion();

    @Delete
    void deleteQuestion(QuestionList question);

    @Query("Select * from QuestionList where question=:quest and level=:lvl")
    QuestionList getQuestionByQAndLAndA(String quest,String lvl);

    @Query("select * from QuestionList where question=:q")
    QuestionList getQyestionByQ(String q);

}
