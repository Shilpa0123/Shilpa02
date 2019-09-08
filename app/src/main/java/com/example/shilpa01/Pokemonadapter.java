package com.example.shilpa01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Pokemonadapter extends RecyclerView.Adapter<Pokemonadapter.ViewHolder> {


    private ArrayList<Pokemon> arraypro;
    private Context mcontext;
    private View.OnClickListener catlogitemListener;


    public Pokemonadapter(ArrayList<Pokemon> arraypro, Context mcontext) {
        this.arraypro = arraypro;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(mcontext).asBitmap().load(arraypro.get(position).getImage()).into(holder.pokeImage);

        holder.pokename.setText(arraypro.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return arraypro.size();
    }



    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        catlogitemListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView pokeImage;
        TextView pokename;

        public ViewHolder(View itemView) {
            super(itemView);

            pokeImage =itemView.findViewById(R.id.img_pokeimg);
            pokename = itemView.findViewById(R.id.txt_pokename);

            itemView.setTag(this);
            itemView.setOnClickListener(catlogitemListener);
        }
    }

}
