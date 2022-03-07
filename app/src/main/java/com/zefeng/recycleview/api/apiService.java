package com.zefeng.recycleview.api;

import com.zefeng.recycleview.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiService {

    @GET("")
    Call<List<User>> getUsers();

}
