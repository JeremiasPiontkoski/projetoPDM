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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserRepositoriesAdapter extends RecyclerView.Adapter<UserRepositoriesAdapter.ViewHolder> {

    ArrayList<Repository> repositories;
    Context context;

    public UserRepositoriesAdapter(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repositories_user, parent, false);

        this.context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repository repository = repositories.get(position);
            holder.txtName.setText("Nome: " + repository.getName());
            holder.txtLanguage.setText("Linguagem: " + repository.getLanguage());
            holder.txtDescription.setText("Descrição: " + repository.getDescription());

        holder.cvRepository.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                FragmentRepositoryDetailUser fragmentRepositoryDetailUser = FragmentRepositoryDetailUser.newInstance(repository.getId());
                fragmentTransaction.replace(R.id.fragmentContainerView, fragmentRepositoryDetailUser);
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
        final TextView txtDescription;
        final CardView cvRepository;
        public ViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtLanguage = (TextView) view.findViewById(R.id.txtLanguage);
            txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            cvRepository = (CardView) view.findViewById(R.id.cardRepository);
        }
    }
}
