package com.majestica.catlist;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class UpdatedItem extends DialogFragment
{
    private EditText enterText;
    private Button okButton, cancelButton;
    private Listener myListener;

    public UpdatedItem()
    {
        // Required empty public constructor
    }
    interface Listener
    {
        void returnData(String data);
    }

    public void setListener(Listener listener)
    {
        myListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_updated_item, container, false);

        enterText    = view.findViewById(R.id.editedItem);
        okButton     = view.findViewById(R.id.OK_button);
        cancelButton = view.findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDialog().dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                String userEnter = enterText.getText().toString();

                myListener.returnData(userEnter);
                getDialog().dismiss();
            }
        });
        return view;
    }
}