package com.evanemran.quickfoods.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.quickfoods.R
import com.evanemran.quickfoods.adapters.PayChannelAdapter
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.PaymentChannels
import kotlinx.android.synthetic.main.dialog_paymentchannel.*

class PaymentOptionDialog(private val pList: List<PaymentChannels>, private var selectedChannel: PaymentChannels, private val listener: ClickListener<PaymentChannels>) : DialogFragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_paymentchannel, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_payChannel.setHasFixedSize(true)
        recycler_payChannel.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = PayChannelAdapter(requireContext(), selectedChannel, pList, pClickListener)
        recycler_payChannel.adapter = adapter

        imageView_close.setOnClickListener {
        dismiss()
        }
        }

private val pClickListener = object : ClickListener<PaymentChannels> {
        override fun onClicked(data: PaymentChannels) {
        selectedChannel = data
        listener.onClicked(data)
        dismiss()
        }
        }

        override fun onStart() {
        super.onStart()
        dialog?.apply {
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setCanceledOnTouchOutside(false)
        }
        }
        }