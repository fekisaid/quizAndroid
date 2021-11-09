package tn.esprit.quizapplication.entity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.QuestionList;

public class QuestionsBank {

    private static List<QuestionList> footballQuestions(){

        final List<QuestionList> questionLists= new ArrayList<>();

        final QuestionList question1=new QuestionList("Quel joueur détient le record de coupe d'Europe gagnées dans sa carrière ?",
                "Clarence Seedorf","Paco Gento","Paolo Maldini","Ahmed Sellami","Paco Gento","hard","Football","");

        final QuestionList question2=new QuestionList("En 2004/05 Chelsea a remporté la Premier League avec un total record de points. Combien en ont-ils obtenus au final ?",
                "87","95","91","99","95","hard","Football","");

        final QuestionList question3=new QuestionList("Qui est le seul joueur à avoir marqué un coup du chapeau en Ligue des Champions, en Premier League et en FA Cup ?",
                "Yossi Benayoun","Cristiano Ronaldo","Didier Drogba","Ahmed Sellami","Yossi Benayoun","hard","Football","");

        final QuestionList question4=new QuestionList("Qui a marqué le but vainqueur en finale de la Coupe du Monde 2010 ?",
                "David Villa","Xavi","Andres Iniesta","Ahmed Sellami","Andres Iniesta","hard","Football","");

        final QuestionList question5=new QuestionList("En 2011, l'Australie a enregistré le score le plus élevé dans un match de football international contre les Samoa américaines. Quel était le score ?",
                "15-1","31-0","27-1","12-0","31-0","hard","Football","");

        final QuestionList question6=new QuestionList("Quel joueur a raté le plus de penalties en Premier League ?",
                "Alan Shearer","Robert Pires","Wayne Rooney","Ahmed Sellami","Alan Shearer","hard","Football","");

        questionLists.add(question1);
        questionLists.add(question2);
        questionLists.add(question3);
        questionLists.add(question4);
        questionLists.add(question5);
        questionLists.add(question6);

        return questionLists;

    }

    public static List<QuestionList> getQuestions(String selectedTopicName){
        switch (selectedTopicName){
            case "football":
                return footballQuestions();
            case "volleyball":
                return footballQuestions();
            case "tennis":
                return footballQuestions();
            default:
                return footballQuestions();
        }
    }

    public static void injectQuestions(Context context){
        MyDataBase mydb= MyDataBase.getDataBase(context);
        List<QuestionList> res= footballQuestions();
        for (int i = 0; i < res.size(); i++) {
            mydb.questionListDAO().insertQuestion(res.get(i));
        }

    }

}
