package com.example.projetopdm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    ArrayList<Repository> repositories;
    Context context;

    public RepositoryAdapter(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_list_item, parent, false);

        this.context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        holder.txtName.setText(repository.getName());
//        holder.tvName.setText(repository.getName());

//        holder.tvWeight.setText( "" + repository.getWeight() );
//        holder.tvType1.setText( getType(repository.getTypess(), 0) );
//        há casos em que não possui mais de um tipo
//        if ( getType(pokemon.getTypess(), 1).equals("") ) {
//            holder.tvType2.setVisibility(View.INVISIBLE);
//        } else {
//            holder.tvType2.setText( getType(pokemon.getTypess(), 1) );
//        }

        //para pegar a propriedade front_default é necessário navegar no JSON usando os métodos
        //getAsJsonObject quando a prop for um objeto e getAsJsonPrimitive quando for o valor em si
//        String urlImage = repository.getSprites()
//                .getAsJsonObject("other")
//                .getAsJsonObject("official-artwork")
//                .getAsJsonPrimitive("front_default")
//                .getAsString();
//
//        Picasso.get().load(urlImage).into(holder.ivPokeImage);
//        changeColorCardView(holder, urlImage);
        //o clique é no CardView
//        holder.cvPokemon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //para instanciar o FragmentTransaction é necessário usar o método  getSupportFragmentManager
//                //deste modo, é preciso fazer o casting do context com ((FragmentActivity) context)
//                FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
//                PokemonDetail pokemonDetail = PokemonDetail.newInstance(pokemon.getId(), holder.cvPokemon.getCardBackgroundColor().getDefaultColor());
//                fragmentTransaction.replace(R.id.fragmentContainerView, pokemonDetail);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtName;
        public ViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
        }
    }

    //método para trocar a cor de fundo do CardView. Usados os seguintes exemplos
    //https://stackoverflow.com/questions/20181491/use-picasso-to-get-a-callback-with-a-bitmap
    //https://developer.android.com/training/material/palette-colors
//    private void changeColorCardView(ViewHolder holder, String urlImage) {
//        Picasso.get()
//                .load(urlImage)
//                .into(new Target() {
//                    @Override
//                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                        Palette palette = Palette.from(bitmap).generate();
//                        int color = palette.getDominantColor( context.getResources().getColor( R.color.purple_200 ) );
//                        holder.cardR .setCardBackgroundColor(color);
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });
//    }

    private String getName(JsonArray name, int position) {
        try {
            return name
                    .get(position)
                    .getAsJsonObject()
                    .getAsJsonObject("repository")
                    .getAsJsonPrimitive("name")
                    .getAsString();
        } catch (Exception e) {
            Log.d("Error", e.toString());
        }
        return "";
    }
}
