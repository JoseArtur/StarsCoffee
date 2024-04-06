package com.evanemran.quickfoods.dialogs;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evanemran.quickfoods.R;
import com.evanemran.quickfoods.adapters.PayChannelAdapter;
import com.evanemran.quickfoods.listeners.ClickListener;
import com.evanemran.quickfoods.models.PaymentChannels;

import java.util.List;

public class PermissionDialog extends DialogFragment {
    private ClickListener<Boolean> listener;
    Button button_allowPermission, button_addLocation;

    public PermissionDialog(ClickListener<Boolean> listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_permission, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button_allowPermission = view.findViewById(R.id.button_allowPermission);
        button_addLocation = view.findViewById(R.id.button_addLocation);

        button_allowPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{ACCESS_FINE_LOCATION}, 1);
                dismiss();
            }
        });
    }

    private final ClickListener<Boolean> pClickListener = new ClickListener<Boolean>() {
        @Override
        public void onClicked(Boolean data) {
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
