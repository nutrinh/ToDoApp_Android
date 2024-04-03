package com.majestica.catlist;

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter implements ListAdapter, UpdatedItem.Listener
{
    private ArrayList<String> list;
    private Context context;
    Integer selected_position =-1;

    public ItemAdapter(ArrayList<String> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    //You can pass data by attaching a callback into your adapter:
    // Create an interface

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        //return list.get(pos).getId();
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent)
    {
        View view = convertView;
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_design, null);
        }


        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));


        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        Button addBtn = (Button)view.findViewById(R.id.add_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete this item?");
                alert.setCancelable(false);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        list.remove(position);
                        notifyDataSetChanged();
                        FileHelper.writeData(list, context.getApplicationContext());
                    }
                });
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateItem(v, position);
            }
        });
        return view;
    }

    private void updateItem(final View v, final int pos)
    {

        FragmentActivity activity = (FragmentActivity)(context);
        FragmentManager fm = activity.getSupportFragmentManager();
        UpdatedItem updatedItemFragment = new UpdatedItem();
        selected_position = pos;

        updatedItemFragment.setListener(ItemAdapter.this);
        updatedItemFragment.show(fm, "updatedItemFragment");
    }
    @Override
    public void returnData(final String data)
    {
        list.set(selected_position, data);
        notifyDataSetChanged();
        FileHelper.writeData(list, context.getApplicationContext());
    }
}