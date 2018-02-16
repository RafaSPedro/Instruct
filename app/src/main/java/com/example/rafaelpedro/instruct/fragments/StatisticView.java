package com.example.rafaelpedro.instruct.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafaelpedro.instruct.R;
import com.example.rafaelpedro.instruct.models.User;
import com.example.rafaelpedro.instruct.models.User_ListView;
import com.example.rafaelpedro.instruct.restcliente.RestService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticView extends Fragment {

    private TextView HemiSul;
    private TextView HemiNorte;

    public StatisticView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistic_view, container, false);

        HemiNorte = v.findViewById(R.id.textview_heminorte);
        HemiSul = v.findViewById(R.id.textview_hemisul);

        countUsers();

        return v;
    }

    private void countUsers() {
        RestService restcliente = getInterfaceService();
        Call<List<User>> call = restcliente.Users();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();

                int HNort=0;
                int HSul=0;

                for (User u : userList) {
                    if(Float.valueOf(u.getAddress().getGeo().getLat())>0){
                        HNort++;
                    } else {
                        HSul++;
                    }
                }
                HemiNorte.setText(String.valueOf(HNort));
                HemiSul.setText(String.valueOf(HSul));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getContext(),
                        "Servidor Indisponível ", Toast.LENGTH_LONG).show();
                Log.d("Erro: ", t.toString());
            }
        });

    }


    private RestService getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RestService mInterfaceService = retrofit.create(RestService.class);
        return mInterfaceService;
    }

}
