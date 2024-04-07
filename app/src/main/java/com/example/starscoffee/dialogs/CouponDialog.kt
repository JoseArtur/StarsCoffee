package com.example.starscoffee.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starscoffee.adapters.CouponsAdapter
import com.example.starscoffee.listeners.ClickListener
import com.example.starscoffee.models.Coupon
import com.example.starscoffee.databinding.DialogCouponsBinding

class CouponDialog(private val couponList: List<Coupon>, private val listener: ClickListener<Coupon>) : DialogFragment() {
    private var _binding: DialogCouponsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogCouponsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (couponList.isNotEmpty()) {
            binding.recyclerAvailableCoupon.visibility = View.VISIBLE
        }

        binding.recyclerAvailableCoupon.setHasFixedSize(true)
        binding.recyclerAvailableCoupon.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CouponsAdapter(requireContext(), couponList, couponClickListener)
        binding.recyclerAvailableCoupon.adapter = adapter
    }

    private val couponClickListener = object : ClickListener<Coupon> {
        override fun onClicked(data: Coupon) {
            listener.onClicked(data)
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            setCanceledOnTouchOutside(false)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}