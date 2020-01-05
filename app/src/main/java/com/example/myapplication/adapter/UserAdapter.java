package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> mUsers = new ArrayList<>();
    private OnUserListener mOnUserListener;

    public UserAdapter(ArrayList<User> users, OnUserListener onUserListener) {
        this.mOnUserListener = onUserListener;
        this.mUsers = users;
    }


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent,false);
        return new UserHolder(itemView, mOnUserListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {

        try {
            User currentUser = mUsers.get(position);
            holder.usersFirstName.setText(currentUser.getFirstName());
            holder.usersLastName.setText(currentUser.getLastName());
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView usersFirstName, usersLastName;
        OnUserListener onUserListener;

        private UserHolder(@NonNull View itemView, OnUserListener onUserListener) {
            super(itemView);
            usersFirstName = itemView.findViewById(R.id.userFirstName);
            usersLastName = itemView.findViewById(R.id.userLastName);
            this.onUserListener = onUserListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onUserListener.onUserClick(getAdapterPosition());
        }
    }

    public interface OnUserListener{
        void onUserClick(int position);
    }
}
