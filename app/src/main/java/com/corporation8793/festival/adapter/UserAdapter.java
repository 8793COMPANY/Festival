package com.corporation8793.festival.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.corporation8793.festival.R;
import com.corporation8793.festival.room.User;
import com.corporation8793.festival.activity.UpdateActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    List<User> userList;
    Context context;

    public UserAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recyclerview_user, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int mPosition = holder.getAdapterPosition();

        holder.nameText.setText(userList.get(mPosition).userName);
        holder.idText.setText(userList.get(mPosition).userId);
        holder.pwText.setText(userList.get(mPosition).userPw);
        holder.pwQuestionText.setText(userList.get(mPosition).userPwQuestion);
        holder.pwAnswerText.setText(userList.get(mPosition).userPwAnswer);
        holder.emailText.setText(userList.get(mPosition).userEmail);
        holder.phoneNumberText.setText(userList.get(mPosition).userPhoneNumber);
        holder.areaText.setText(userList.get(mPosition).userArea);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("uid", userList.get(mPosition).uid);
                intent.putExtra("userName", userList.get(mPosition).userName);
                intent.putExtra("userId", userList.get(mPosition).userId);
                intent.putExtra("userPw", userList.get(mPosition).userPw);
                intent.putExtra("userPwQuestion", userList.get(mPosition).userPwQuestion);
                intent.putExtra("userPwAnswer", userList.get(mPosition).userPwAnswer);
                intent.putExtra("userEmail", userList.get(mPosition).userEmail);
                intent.putExtra("userPhoneNumber", userList.get(mPosition).userPhoneNumber);
                intent.putExtra("userArea", userList.get(mPosition).userArea);
                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    //리스트 저장
    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    //사용자 삭제
    public void deleteUser(int position) {
        this.userList.remove(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, idText, pwText, pwQuestionText, pwAnswerText, emailText, phoneNumberText, areaText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.nameText);
            idText = itemView.findViewById(R.id.idText);
            pwText = itemView.findViewById(R.id.pwText);
            pwQuestionText = itemView.findViewById(R.id.pwQuestionText);
            pwAnswerText = itemView.findViewById(R.id.pwAnswerText);
            emailText = itemView.findViewById(R.id.emailText);
            phoneNumberText = itemView.findViewById(R.id.phoneNumberText);
            areaText = itemView.findViewById(R.id.areaText);

        }
    }
}
