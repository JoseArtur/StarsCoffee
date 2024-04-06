package com.evanemran.quickfoods.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evanemran.quickfoods.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FoodDetailBottomSheet extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottomsheet_food_detail,
                container, false);

        Button addCart = v.findViewById(R.id.button_addToCart);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(),
                                "Added to cart!", Toast.LENGTH_SHORT)
                        .show();
                dismiss();
            }
        });

        return v;
    }
}
