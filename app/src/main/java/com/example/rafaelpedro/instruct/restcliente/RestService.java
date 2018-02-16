package com.example.rafaelpedro.instruct.restcliente;

import com.example.rafaelpedro.instruct.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by RafaelPedro on 15/02/2018.
 */

public interface RestService {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("users")
    Call<List<User>> Users();
}
