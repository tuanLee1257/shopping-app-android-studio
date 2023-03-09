package com.example.shoppingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.models.Comment;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private Context context;
    private List<Comment> commentList;

    public CommentsAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_comment,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        String formattedDateTime = comment.getCommentDate().format(formatter);

        holder.username_text.setText(comment.getUserComment().getUsername());
        holder.comment_text.setText(comment.getComment());
        holder.commentDate_text.setText(comment.getCommentDate());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView username_text;
        TextView comment_text;
        TextView commentDate_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             username_text = itemView.findViewById(R.id.username);
             comment_text = itemView.findViewById(R.id.comment);
             commentDate_text = itemView.findViewById(R.id.datetime);
        }
    }
//    private Context context;
//    private List<Comment> commentList;
//
//    public CommentsAdapter(Context context, List<Comment> commentList) {
//        this.context = context;
//        this.commentList = commentList;
//    }
//
//    @Override
//    public int getCount() {
//        return commentList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return commentList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View commentView;
//        if (convertView == null){
//            commentView = View.inflate(parent.getContext(), R.layout.user_comment,null);
//        }
//        else commentView = convertView;
//        //
//        TextView username_text = commentView.findViewById(R.id.username);
//        TextView comment_text = commentView.findViewById(R.id.comment);
//        TextView commentDate_text = commentView.findViewById(R.id.datetime);
//
//        Comment comment = commentList.get(position);
//
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
////        String formattedDateTime = comment.getCommentDate().format(formatter);
//
//        username_text.setText(comment.getUserComment().getUsername());
//        comment_text.setText(comment.getComment());
//        commentDate_text.setText(comment.getCommentDate());
//
//
//        return commentView;
//    }
}
