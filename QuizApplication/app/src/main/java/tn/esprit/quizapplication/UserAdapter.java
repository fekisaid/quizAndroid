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

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import tn.esprit.quizapplication.entity.HistoriqueUser;
import tn.esprit.quizapplication.entity.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> users;
    Context context;

    public UserAdapter(List<User> lusers, Context context) {
        this.users = lusers;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.list_element,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u=users.get(position);

        String avatar="https://ui-avatars.com/api/?name="+u.getUsername()+"&background=E2DED0&color=647C90&rounded=true";
        Picasso.get().load(avatar).into(holder.iconImage);
        holder.level.setText("Username: "+u.getUsername());
        holder.correctAnswer.setText("Email: "+u.getEmail());
        holder.inCorrectAnswer.setText("");
        holder.date.setText("");


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends  RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView level;
        TextView correctAnswer;
        TextView inCorrectAnswer;
        TextView date;

        public UserViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconImageView);
            level=itemView.findViewById(R.id.txtLevel);
            correctAnswer=itemView.findViewById(R.id.txtCorrectAnswer);
            inCorrectAnswer=itemView.findViewById(R.id.txtInCorrectAnswer);
            date=itemView.findViewById(R.id.txtDate);

        }

    }
}
