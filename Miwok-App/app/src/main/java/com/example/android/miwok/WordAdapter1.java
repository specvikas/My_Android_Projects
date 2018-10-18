package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter1 extends ArrayAdapter<Word1>{

        private Context mContext;
        private ArrayList<Word1> trans;
        private int mColor;

        public WordAdapter1(Context context, ArrayList<Word1> words, int color)
        {
            super(context, 0, words);
            mContext = context;
            trans = words;
            mColor = color;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {

            View listItemView = convertView;

            if(listItemView == null)
            {
                listItemView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            }

            Word1 newWord = getItem(position);

            TextView miwokTextView = (TextView)listItemView.findViewById(R.id.miwok_text_view);
            miwokTextView.setText(newWord.getMiwokTranslation());

            TextView defaultTextView = (TextView)listItemView.findViewById(R.id.default_text_view);
            defaultTextView.setText(newWord.getDefaultTranslation());

            View toColor = listItemView.findViewById(R.id.phrases);
            int getColor = ContextCompat.getColor(getContext(),mColor);
            toColor.setBackgroundColor(getColor);
            return listItemView;
        }
    }


