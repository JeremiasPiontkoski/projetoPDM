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
        holder.txtName.setText("Nome: " + repository.getName());
        holder.txtLanguage.setText("Linguagem: " + repository.getLanguage());

        try{
            String userPhoto = repository.getUser()
                    .getAsJsonPrimitive("photo").getAsString();
            Picasso.get().load("http://10.0.2.2:80/trabalho-pwii/" +  userPhoto).into(holder.ivPhoto);
        }catch (ClassCastException e){
            holder.ivPhoto.setImageResource(R.drawable.image_perfil);
        }

//        if(userPhoto != null) {
//            Log.d("PHOTO", "SIM");
//            Picasso.get().load("http://10.0.2.2:80/trabalho-pwii/" +  userPhoto).into(holder.ivPhoto);
//        }
//        else {
//            Log.d("PHOTO", "NAO");
//            holder.ivPhoto.setImageResource(R.drawable.image_perfil);
//        }

        holder.cvRepository.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                RepositoryDetail repositoryDetail = RepositoryDetail.newInstance(repository.getId());
                fragmentTransaction.replace(R.id.fragmentContainerView, repositoryDetail);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtName;
        final TextView txtLanguage;
        final ImageView ivPhoto;
        final CardView cvRepository;
        public ViewHolder(View view) {
            super(view);

            txtName = (TextView) view.findViewById(R.id.txtName);
            txtLanguage = (TextView) view.findViewById(R.id.txtLanguage);
            ivPhoto = (ImageView) view.findViewById(R.id.ivPerfilRepository);
            cvRepository = (CardView) view.findViewById(R.id.cardRepository);
        }
    }
}
