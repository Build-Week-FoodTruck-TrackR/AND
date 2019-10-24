package com.example.foodtruck.ui.foodie.fragments


import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.example.foodtruck.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import kotlin.properties.Delegates


class Map : Fragment(), OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var map: GoogleMap
    private val FINE_ACCESS_REQUEST_CODE = 1
    private lateinit var currentLocation: Location

    private var currentSearchRadius by Delegates.observable(0.0){
        property, oldValue, newValue->
        getFurthestFoodtruckLocation()
    }

    val furthestFoodtruckLocation by Delegates.observable(LatLng(0.0,0.0)){
        property, oldValue, newValue->
        //adjust the amount of zoom the map to the maximum zoom needed to see the current user's location and the furthest food track possible
        if(oldValue != newValue){
            val latLngBounds = LatLngBounds(LatLng(currentLocation.latitude, currentLocation.longitude), newValue)
            map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0))
        }
    }

    private val listOfSearchRanges = doubleArrayOf(1.0, 3.0, 5.0)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //gets the current spinner search radius amount
        currentSearchRadius = listOfSearchRanges[0]

        spinner_search_range.setOnItemClickListener { adapterView, view, i, l ->
            //use i (position) to determine what the search radius is
            currentSearchRadius = listOfSearchRanges[i]
        }

        val mapFragment = map_frag as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        if(PermissionChecker.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PermissionChecker.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), FINE_ACCESS_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(requestCode == FINE_ACCESS_REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                setCurrentLocation()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setCurrentLocation(){

        LocationServices.getFusedLocationProviderClient(activity!!).lastLocation.addOnSuccessListener {
            currentLocation = it
            map.moveCamera(CameraUpdateFactory.newLatLng(LatLng(it.latitude, it.longitude)))

            //user can select how big range at which foodtrucks will be searched at, affects the amount of zoom currently
            getFurthestFoodtruckLocation()

            //adds marker for user's current location
            map.addMarker(MarkerOptions().position(LatLng(it.latitude, it.longitude)))
        }
    }

    private fun getFurthestFoodtruckLocation() {
        //Iterates over all the foodtrucks in the given search range, and finds the one furthest away
    }
}
