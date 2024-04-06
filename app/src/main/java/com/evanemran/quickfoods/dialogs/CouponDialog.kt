package com.evanemran.quickfoods.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.quickfoods.R
import com.evanemran.quickfoods.adapters.CouponsAdapter
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.Coupon
import kotlinx.android.synthetic.main.dialog_coupons.*

class CouponDialog(private val couponList: List<Coupon>, private val listener: ClickListener<Coupon>) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_coupons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (couponList.isNotEmpty()) {
            recycler_availableCoupon.visibility = View.VISIBLE
        }

        recycler_availableCoupon.setHasFixedSize(true)
        recycler_availableCoupon.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = CouponsAdapter(requireContext(), couponList, couponClickListener)
        recycler_availableCoupon.adapter = adapter
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
}