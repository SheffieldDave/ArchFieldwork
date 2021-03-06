package org.wit.archfieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_site_maps.*
import org.wit.archfieldwork.R

import kotlinx.android.synthetic.main.content_site_maps.*
import org.wit.archfieldwork.R.id.mapView
import org.wit.archfieldwork.R.id.toolbarMaps
import org.wit.archfieldwork.helpers.readImageFromPath
import org.wit.archfieldwork.main.MainApp


class SiteMapsActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener{

    lateinit var map: GoogleMap
    lateinit var app:MainApp

    fun configureMap() {
        map.uiSettings.setZoomControlsEnabled(true)
        app.sites.findAll().forEach {
            val loc = LatLng (it.lat, it.lng)
            val options = MarkerOptions().title(it.name).position(loc)
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,it.zoom))
            map.setOnMarkerClickListener(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_maps)
        setSupportActionBar(toolbarMaps)
        app = application as MainApp
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            map = it
            configureMap()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val tag = marker.tag as Long
        val site = app.sites.findById(tag)
        currentTitle.text = site!!.name
        currentDescription.text = site!!.description
        imageView.setImageBitmap(readImageFromPath(this@SiteMapsActivity, site.image))
        return true
    }
}


