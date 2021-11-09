package tn.esprit.quizapplication.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class HistoriqueUser {

    @PrimaryKey(autoGenerate = true)
    private  int id;
    @ColumnInfo
    private int id_user;
    @ColumnInfo
    @TypeConverters(DateConverter.class)
    private Date date;
    @ColumnInfo
    private String topic;
    @ColumnInfo
    private String level;
    @ColumnInfo
    private int correctAnswer;
    @ColumnInfo
    private int incorrectAnswer;


    public HistoriqueUser(int id_user, Date date, String topic, String level, int correctAnswer, int incorrectAnswer) {
        this.id_user = id_user;
        this.date = date;
        this.topic = topic;
        this.level = level;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getIncorrectAnswer() {
        return incorrectAnswer;
    }

    public void setIncorrectAnswer(int incorrectAnswer) {
        this.incorrectAnswer = incorrectAnswer;
    }

    @Override
    public String toString() {
        return "HistoriqueUser{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", date=" + date +
                ", topic='" + topic + '\'' +
                ", level='" + level + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", incorrectAnswer=" + incorrectAnswer +
                '}';
    }
}
