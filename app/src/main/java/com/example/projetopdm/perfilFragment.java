package com.example.projetopdm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link perfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class perfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "user";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public perfilFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static perfilFragment newInstance(User user) {
        perfilFragment fragment = new perfilFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) user);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
}