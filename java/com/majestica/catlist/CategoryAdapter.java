package com.majestica.catlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CardViewHolder>
{
    private ArrayList<Category> categoryList;
    private Context context;

    public CategoryAdapter(ArrayList<Category> categoryList, Context context)
    {
        if(categoryList!= null)
        {
            this.categoryList = categoryList;
        }
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View card = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design, parent, false);

        return new CardViewHolder(card);
    }

    @Override
    public void onBindViewHolder(final @NonNull CardViewHolder holder, int position)
    {

        Category category = categoryList.get(position);

        holder.textViewSplash.setText(category.getCategory());
        holder.imageViewSplash.setImageResource(context.getResources()
                .getIdentifier(category.getImage(), "drawable", context.getPackageName()));

        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent;
                switch(holder.getAdapterPosition())
                {
                    case 0:
                        FileHelper.setFileName("listinfo.dat");
                        intent = new Intent(context,ChoresActivity.class);
                        context.startActivity(intent);
                        break;
                    case 1:
                        FileHelper.setFileName("listinfo1.dat");
                        intent = new Intent(context,SocialActivity.class);
                        context.startActivity(intent);
                        break;
                    case 2:
                        FileHelper.setFileName("listinfo2.dat");
                        intent = new Intent(context,StudiesActivity.class);
                        context.startActivity(intent);
                        break;
                    case 3:
                        FileHelper.setFileName("listinfo3.dat");
                        intent = new Intent(context,WorkActivity.class);
                        context.startActivity(intent);
                        break;
                    default:
                        System.out.println("Nothing is chosen");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    //Create an innerClass => The Adapter and ViewHolder classes work together to define the layout.
    public class CardViewHolder extends RecyclerView.ViewHolder
    {
        //A holder for the views you want the recycler view to display => We want to display ImageView and TextView
        private ImageView imageViewSplash;
        private TextView  textViewSplash;
        private CardView  cardView;

        public CardViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.imageViewSplash = itemView.findViewById(R.id.imageViewSplash);
            this.textViewSplash  = itemView.findViewById(R.id.textViewSplash);
            this.cardView        = itemView.findViewById(R.id.cardView); //This is the cardView component in card_design.xml
        }
    }

}
