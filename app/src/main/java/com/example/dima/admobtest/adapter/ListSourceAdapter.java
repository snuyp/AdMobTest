package com.example.dima.admobtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dima.admobtest.ContentActivity;
import com.example.dima.admobtest.Interface.ItemClickListener;
import com.example.dima.admobtest.R;
import com.example.dima.admobtest.mvp.model.SourceNews;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



class ListSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ItemClickListener itemClickListener;
    TextView sourceTitle;
    CircleImageView sourceImage;

    public ListSourceViewHolder(View itemView) {
        super(itemView);

        sourceTitle = itemView.findViewById(R.id.source_name);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}


public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder> {
    private Context context;
    private List<SourceNews> sources;


    public ListSourceAdapter(Context context, List<SourceNews> sources) {
        this.context = context;
        this.sources = sources;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListSourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.source_card_layout,parent, false);
        return new ListSourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListSourceViewHolder holder, int position) {

        holder.sourceTitle.setText(sources.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("source", sources.get(position).getId());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return sources.size();
    }
}
