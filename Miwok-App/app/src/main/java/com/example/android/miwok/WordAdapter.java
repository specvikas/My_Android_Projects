package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>
{
    private Context mContext;
    private ArrayList<Word> trans;
    private int mColor;

    public WordAdapter(Context context, ArrayList<Word> words, int color)
    {
        super(context, 0, words);
        mContext = context;
        trans = words;
        mColor = color ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

       View listItemView = convertView;

       if(listItemView == null)
       {
           listItemView = LayoutInflater.from(mContext).inflate(R.layout.list_item_1, parent, false);
       }

       Word newWord = getItem(position);

        TextView miwokTextView = (TextView)listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(newWord.getMiwokTranslation());

        TextView defaultTextView = (TextView)listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(newWord.getDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(newWord.getImage());

        View textContainer = listItemView.findViewById(R.id.layout_2);
        int getColor = ContextCompat.getColor(getContext(),mColor);
        textContainer.setBackgroundColor(getColor);


        return listItemView;
    }
}
