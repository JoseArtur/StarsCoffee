package com.evanemran.quickfoods;
import com.evanemran.quickfoods.models.Foods;
import com.evanemran.quickfoods.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CoffeeAPIService {
    @GET("menu_items.json")
    Call<List<Foods>> getMenuItems();
    @PUT("users/{id}.json")
    Call<Users> updateUserPoints(@Path("id") int userId, @Body Users updatedUser);
}
