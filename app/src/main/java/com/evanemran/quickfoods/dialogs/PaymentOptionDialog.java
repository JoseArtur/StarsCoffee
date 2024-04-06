package com.evanemran.quickfoods.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evanemran.quickfoods.R;
import com.evanemran.quickfoods.adapters.PayChannelAdapter;
import com.evanemran.quickfoods.listeners.ClickListener;
import com.evanemran.quickfoods.models.PaymentChannels;

import java.util.List;

public class PaymentOptionDialog extends DialogFragment {

    private ClickListener<PaymentChannels> listener;
    RecyclerView recycler_payChannel;
    ImageView imageView_close;
    List<PaymentChannels> pList;
    PaymentChannels selectedChannel;


    public PaymentOptionDialog(List<PaymentChannels> list, PaymentChannels selectedChannel, ClickListener<PaymentChannels> listener) {
        this.pList = list;
        this.listener = listener;
        this.selectedChannel = selectedChannel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_paymentchannel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler_payChannel = view.findViewById(R.id.recycler_payChannel);
        imageView_close = view.findViewById(R.id.imageView_close);

        recycler_payChannel.setHasFixedSize(true);
        recycler_payChannel.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        PayChannelAdapter adapter = new PayChannelAdapter(getContext(), selectedChannel, pList, pClickListener);
        recycler_payChannel.setAdapter(adapter);

        imageView_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    private final ClickListener<PaymentChannels> pClickListener = new ClickListener<PaymentChannels>() {
        @Override
        public void onClicked(PaymentChannels data) {
            selectedChannel = data;
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
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            d.getWindow().setLayout(width, height);
            d.setCanceledOnTouchOutside(false);
        }
    }

}
