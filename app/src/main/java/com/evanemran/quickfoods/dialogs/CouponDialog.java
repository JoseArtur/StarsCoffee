package com.evanemran.quickfoods.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evanemran.quickfoods.R;
import com.evanemran.quickfoods.adapters.CouponsAdapter;
import com.evanemran.quickfoods.adapters.PayChannelAdapter;
import com.evanemran.quickfoods.listeners.ClickListener;
import com.evanemran.quickfoods.models.Coupon;
import com.evanemran.quickfoods.models.PaymentChannels;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CouponDialog extends DialogFragment {

    private ClickListener<Coupon> listener;
    RecyclerView recycler_availableCoupon;
    ImageView imageView_close;
    TextInputEditText editTextCoupon;
    Button button_applyCoupon;
    List<Coupon> couponList;


    public CouponDialog(List<Coupon> list, ClickListener<Coupon> listener) {
        this.couponList = list;
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_coupons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler_availableCoupon = view.findViewById(R.id.recycler_availableCoupon);
        imageView_close = view.findViewById(R.id.imageView_close);
        editTextCoupon = view.findViewById(R.id.editTextCoupon);
        button_applyCoupon = view.findViewById(R.id.button_applyCoupon);

        if (!couponList.isEmpty()){
            recycler_availableCoupon.setVisibility(View.VISIBLE);
        }

        recycler_availableCoupon.setHasFixedSize(true);
        recycler_availableCoupon.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        CouponsAdapter adapter = new CouponsAdapter(getContext(), couponList, couponClickListener);
        recycler_availableCoupon.setAdapter(adapter);

    }

    private final ClickListener<Coupon> couponClickListener = new ClickListener<Coupon>() {
        @Override
        public void onClicked(Coupon data) {
            listener.onClicked(data);
            dismiss();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
            d.setCanceledOnTouchOutside(false);
        }
    }

}
