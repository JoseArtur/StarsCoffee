package com.example.starscoffee.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.quickfoods.models.Voucher
import com.example.starscoffee.adapters.VoucherListAdapter
import com.example.starscoffee.databinding.DialogVouchersBinding
import com.example.starscoffee.listeners.ClickListener

class VoucherDialog(
    private val voucherList: List<Voucher>,
    private val listener: ClickListener<Voucher>
) : DialogFragment() {
    private var _binding: DialogVouchersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogVouchersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (voucherList.isNotEmpty()) {
            binding.recyclerAvailableVoucher.visibility = View.VISIBLE
        }

        binding.imageViewClose.setOnClickListener {
            dismiss() // Close the dialog when the ImageView is clicked
        }

        binding.recyclerAvailableVoucher.setHasFixedSize(true)
        binding.recyclerAvailableVoucher.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = VoucherListAdapter(requireContext(), voucherList, voucherClickListener)
        binding.recyclerAvailableVoucher.adapter = adapter
    }

    private val voucherClickListener = object : ClickListener<Voucher> {
        override fun onClicked(data: Voucher) {
            listener.onClicked(data)
            //dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            setCanceledOnTouchOutside(false)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}