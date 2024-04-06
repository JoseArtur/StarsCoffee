package com.evanemran.quickfoods

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.evanemran.quickfoods.adapters.AddressAdapter
import com.evanemran.quickfoods.listeners.ClickListener
import com.evanemran.quickfoods.models.Address
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.home_grids.*


class AddressListActivity : AppCompatActivity() {
    var selectedAddress: Address? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)

        selectedAddress = intent.getSerializableExtra("address") as Address

        showAddresses()

        button_addAddress.setOnClickListener {
            startActivity(Intent(this, AddressAddActivity::class.java))
        }
    }

    private fun showAddresses() {
        val addresses: MutableList<Address> = mutableListOf()
        addresses.add(Address(0, "12th Floor - 50, Bay Tower, Mohakhali", "Work", 27.435539, 95.392441, false))
        addresses.add(Address(0, "17th Floor - Stark Tower, LA", "Home", 37.056519, -94.537453, true))
        addresses.add(Address(0, "House 112/A, GK Road, Noida", "Love", 27.435539, 95.392441, false))

        recycler_addresses.setHasFixedSize(true)
        recycler_addresses.isNestedScrollingEnabled = false
        recycler_addresses.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val addressAdapter = AddressAdapter(this,
            selectedAddress!!,
            addresses,
            addressClickListener)
        recycler_addresses.adapter = addressAdapter
    }


    private val addressClickListener: ClickListener<Address> = object :
        ClickListener<Address> {
        override fun onClicked(data: Address) {
            Toast.makeText(this@AddressListActivity, "Clicked " + data.addressName, Toast.LENGTH_SHORT).show()
            val intent = Intent()
            intent.putExtra("address", data)
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}