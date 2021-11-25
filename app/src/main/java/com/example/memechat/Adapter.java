package com.example.memechat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {

   Context context;
   ArrayList<Model> modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.items,parent,false);
       return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
  String url = modelArrayList.get(position).getUrl();
  holder.setImage(url);
  holder.shareBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent sharing = new Intent(Intent.ACTION_SEND);
          sharing.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          sharing.setType("text/plain");
          String subject = " Hey man Download this cool meme by clicking on this link " + url;
          sharing.putExtra(Intent.EXTRA_TEXT,subject);
          context.startActivity(Intent.createChooser(sharing,"Sharing using"));
      }
  });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class holder extends RecyclerView.ViewHolder{

        ImageView memeImage;
        Button shareBtn;

        public holder(@NonNull View itemView) {
            super(itemView);
            memeImage = itemView.findViewById(R.id.memeImage);
            shareBtn = itemView.findViewById(R.id.shareBtn);
        }
        void setImage(String link){
            Glide.with(context).load(link).into(memeImage);
        }

    }

}
