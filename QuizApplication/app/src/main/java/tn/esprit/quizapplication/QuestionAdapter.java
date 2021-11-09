package tn.esprit.quizapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.quizapplication.database.MyDataBase;
import tn.esprit.quizapplication.entity.QuestionList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    List<QuestionList> questionLists;
    Context context;

    public QuestionAdapter(List<QuestionList> ql, Context context) {
        this.questionLists = ql;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.list_question_element,parent,false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        QuestionList q=questionLists.get(position);

        if(q.getTopic().toLowerCase().equals("football")){
            holder.iconImage.setImageResource(R.drawable.football_icon);
        }
        if(q.getTopic().toLowerCase().equals("basketball")){
            holder.iconImage.setImageResource(R.drawable.basketball_icon_removebg_preview);
        }
        if(q.getTopic().toLowerCase().equals("tennis")){
            holder.iconImage.setImageResource(R.drawable.tennis_racket_icon_7);
        }
        if(q.getTopic().toLowerCase().equals("volleyball")){
            holder.iconImage.setImageResource(R.drawable.volleyball_icon);
        }
        holder.question.setText(q.getQuestion());
        holder.level.setText("Level: "+q.getLevel());
        holder.correctAnswer.setText("Answer: "+q.getAnswer());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase myDB=MyDataBase.getDataBase(context);
                myDB.questionListDAO().deleteQuestion(q);
                Toast.makeText(context, "Question deleted!", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(context,ListQuestionActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionLists.size();
    }

    public class QuestionViewHolder extends  RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView level;
        TextView correctAnswer;
        TextView question;
        Button delete;


        public QuestionViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconImageView);
            question=itemView.findViewById(R.id.txtQuest);
            level=itemView.findViewById(R.id.txtLevel);
            correctAnswer=itemView.findViewById(R.id.txtCorrectAnswer);
            delete= itemView.findViewById(R.id.btnDeletQ);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Intent i = new Intent(context,EditQuestionAvtivity.class);
                    i.putExtra("question",question.getText().toString());
                    context.startActivity(i);
                    return false;
                }
            });


        }

    }
}
