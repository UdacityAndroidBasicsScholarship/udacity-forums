package com.example.android.udacityforum;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class ForumRecyclerViewAdapter extends RecyclerView.Adapter<ForumRecyclerViewAdapter.ViewHolder> {
    private ArrayList<QuestionFormat> quesList;
    private LayoutInflater inflater;
    private Context context;

    public ForumRecyclerViewAdapter(ArrayList<QuestionFormat> list, Context context) {
        this.quesList = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.app_icon);
        drawable.setBounds(0, 0, 70, 70);
        holder.contentTextView.setText(quesList.get(position).getQuesContent());
        holder.headingTextView.setText(quesList.get(position).getQuesHeading());
        holder.headingTextView.setCompoundDrawables(drawable, null, null, null);
        holder.headingTextView.setCompoundDrawablePadding(50);
    }

    @Override
    public int getItemCount() {
        return quesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView headingTextView;
        TextView contentTextView;
        CardView animated_card;

        public ViewHolder(View itemView) {
            super(itemView);
            headingTextView = itemView.findViewById(R.id.recycler_heading);
            contentTextView = itemView.findViewById(R.id.recycler_content_description);
            contentTextView.setVisibility(View.GONE);
            animated_card = itemView.findViewById(R.id.animated_card);
            animated_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (contentTextView.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(animated_card);
                        contentTextView.setVisibility(View.VISIBLE);
                    } else {
                        contentTextView.setVisibility(View.GONE);
                        TransitionManager.beginDelayedTransition(animated_card);
                    }
                }
            });

        }
    }
}
