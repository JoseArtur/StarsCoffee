package com.evanemran.quickfoods

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_address_add.*
import kotlinx.android.synthetic.main.activity_checkout.*

class AddressAddActivity : AppCompatActivity(), OnMapReadyCallback {
    var map: GoogleMap? = null
    private var client: FusedLocationProviderClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_add)

        client = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment: SupportMapFragment = full_map as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success: Boolean = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.mapstyledark
                )
            )
            if (!success) {
                Toast.makeText(this, "Couldn't Connect!", Toast.LENGTH_SHORT).show()
            } else if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            } else {
                client!!.lastLocation.addOnSuccessListener((this as Activity?)!!,
                    OnSuccessListener { location ->
                        if (location != null) {
                            if (ActivityCompat.checkSelfPermission(
                                    this,
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                    this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return@OnSuccessListener
                            }
                            googleMap.isMyLocationEnabled = false
                            val latLng = LatLng(location.latitude, location.longitude)
                            map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
                            val markerOptions = MarkerOptions()
                            markerOptions.position(latLng)
                            markerOptions.icon(
                                bitmapDescriptorFromVector(
                                    this,
                                    R.drawable.ic_location_marker
                                )
                            )
                            map!!.addMarker(markerOptions)

//                            textView_my_location.setText(
//                                getAddress(
//                                    location.latitude,
//                                    location.longitude
//                                )
//                            )
//                            textView_my_location.setSelected(true)
//                            map!!.setOnMapClickListener { latLng ->
//                                endPoint = latLng
//                                val markerOptions = MarkerOptions()
//                                markerOptions.position(latLng)
//                                markerOptions.icon(
//                                    bitmapDescriptorFromVector(
//                                        getContext(),
//                                        R.drawable.ic_marker
//                                    )
//                                )
//                                markerOptions.title(getAddress(latLng.latitude, latLng.longitude))
//                                map!!.clear()
//                                map!!.addMarker(markerOptions)
//                                map!!.setInfoWindowAdapter(MapInfoWindowAdapter(getContext()))
//                                map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
//                                textView_destination.setText(
//                                    getAddress(
//                                        latLng.latitude,
//                                        latLng.longitude
//                                    )
//                                )
//                            }
                        }
                    })
            }
        } catch (e: Resources.NotFoundException) {
            Toast.makeText(this, "Check your connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}