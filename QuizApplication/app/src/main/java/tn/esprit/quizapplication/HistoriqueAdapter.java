package tn.esprit.quizapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import tn.esprit.quizapplication.entity.HistoriqueUser;

public class HistoriqueAdapter extends RecyclerView.Adapter<HistoriqueAdapter.HistoriqueViewHolder> {

    List<HistoriqueUser> historique;
    Context context;

    public HistoriqueAdapter(List<HistoriqueUser> his, Context context) {
        this.historique = his;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoriqueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.list_element,parent,false);
        return new HistoriqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriqueViewHolder holder, int position) {
        HistoriqueUser h=historique.get(position);

        if(h.getTopic().toLowerCase().equals("football")){
            holder.iconImage.setImageResource(R.drawable.football_icon);
        }
        if(h.getTopic().toLowerCase().equals("basketball")){
            holder.iconImage.setImageResource(R.drawable.basketball_icon_removebg_preview);
        }
        if(h.getTopic().toLowerCase().equals("tennis")){
            holder.iconImage.setImageResource(R.drawable.tennis_racket_icon_7);
        }
        if(h.getTopic().toLowerCase().equals("volleyball")){
            holder.iconImage.setImageResource(R.drawable.volleyball_icon);
        }


        holder.level.setText("Level:"+h.getLevel());
        holder.correctAnswer.setText("Correct answer:"+String.valueOf(h.getCorrectAnswer()));
        holder.inCorrectAnswer.setText("Incorrect answer:"+String.valueOf(h.getIncorrectAnswer()));
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(h.getDate());
        holder.date.setText(strDate);


    }

    @Override
    public int getItemCount() {
        return historique.size();
    }

    public class HistoriqueViewHolder extends  RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView level;
        TextView correctAnswer;
        TextView inCorrectAnswer;
        TextView date;

        public HistoriqueViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconImageView);
            level=itemView.findViewById(R.id.txtLevel);
            correctAnswer=itemView.findViewById(R.id.txtCorrectAnswer);
            inCorrectAnswer=itemView.findViewById(R.id.txtInCorrectAnswer);
            date=itemView.findViewById(R.id.txtDate);

        }

    }
}
