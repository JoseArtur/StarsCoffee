package com.evanemran.quickfoods;
import com.evanemran.quickfoods.models.Foods;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoffeeAPIService {
    @GET("menu_items.json")
    Call<List<Foods>> getMenuItems();
}
