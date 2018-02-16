package com.example.rafaelpedro.instruct.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rafaelpedro.instruct.R;
import com.example.rafaelpedro.instruct.list_view.ListView_Adapter;
import com.example.rafaelpedro.instruct.models.User;
import com.example.rafaelpedro.instruct.models.User_ListView;
import com.example.rafaelpedro.instruct.restcliente.RestService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainView extends Fragment {

    private ListView listView;
    private ArrayList<User_ListView> list;
    private ArrayAdapter<User_ListView> adapter;


    public MainView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_view, container, false);
        list = new ArrayList<User_ListView>();
        //adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        adapter = new ListView_Adapter(getContext(), R.layout.layout_itemlistview, list);
        listView = v.findViewById(R.id.listview);
        listView.setAdapter(adapter);

        getUsers();

        return v;
    }

    private void getUsers(){
        RestService restcliente = getInterfaceService();
        Call<List<User>> call = restcliente.Users();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                String s = "Samantha";
                for(User u : userList){
                    Log.i("Info ", String.format("%s: %s", u.getId(), u.getEmail()));

                    User_ListView user = new User_ListView();
                    user.setUsername(u.getUsername());
                    user.setWebsite(u.getWebsite());

                    if(s.equals(u.getUsername())){
                        user.setEmail(u.getEmail());
                        Log.i("Info ", String.format(":", "True"));
                    }

                    list.add(user);
                }
                adapter.notifyDataSetChanged();
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
