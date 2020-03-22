package com.hackuniversity.partymaker.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hackuniversity.partymaker.Person;
import com.hackuniversity.partymaker.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

import it.sephiroth.android.library.picasso.Picasso;

public class RecyclerViewPeopleAdaper extends RecyclerView.Adapter<RecyclerViewPeopleAdaper.PeopleViewHolder> {

    private ArrayList<Person> people;

    public RecyclerViewPeopleAdaper(ArrayList<Person> personArrayList) {
        people = personArrayList;
    }



    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_person, parent, false);
        return new PeopleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }



    class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
        }


        public void onBind(int pos) {
            itemView.setTag(pos);
            ImageView photo = itemView.findViewById(R.id.image_cardPerson);
            TextView textView = itemView.findViewById(R.id.textView_name_cardPerson);
            ImageView imageView = itemView.findViewById(R.id.btnAdd_cardPerson);

            if (!people.get(pos).getPhoto().isEmpty() && people.get(pos).getPhoto() != null) {
                Picasso.with(itemView.getContext())
                        .load(people.get(pos).getPhoto())
                        .into(photo);
            }
            textView.setText(people.get(pos).getName());
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
