package org.wit.archfieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import kotlinx.android.synthetic.main.activity_site_maps.*
import org.wit.archfieldwork.R

import kotlinx.android.synthetic.main.content_site_maps.*



class SiteMapsActivity : AppCompatActivity(){

    lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_maps)
        setSupportActionBar(toolbarMaps)
        mapView.onCreate(savedInstanceState)
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
}


