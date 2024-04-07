package com.example.starscoffee.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starscoffee.adapters.PayChannelAdapter
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.PaymentChannels
import com.example.starscoffee.databinding.DialogPaymentchannelBinding

class PaymentOptionDialog(private val pList: List<PaymentChannels>, private var selectedChannel: PaymentChannels, private val listener: ClickListener<PaymentChannels>) : DialogFragment() {
        private var _binding: DialogPaymentchannelBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
                _binding = DialogPaymentchannelBinding.inflate(inflater, container, false)
                return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)

                binding.recyclerPayChannel.setHasFixedSize(true)
                binding.recyclerPayChannel.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                val adapter = PayChannelAdapter(requireContext(), selectedChannel, pList, pClickListener)
                binding.recyclerPayChannel.adapter = adapter

                binding.imageViewClose.setOnClickListener {
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

        override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
        }
}