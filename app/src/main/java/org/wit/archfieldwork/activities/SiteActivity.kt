package org.wit.archfieldwork.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.*
import org.wit.archfieldwork.R
import org.wit.archfieldwork.helpers.readImage
import org.wit.archfieldwork.helpers.readImageFromPath
import org.wit.archfieldwork.helpers.showImagePicker
import org.wit.archfieldwork.main.MainApp
import org.wit.archfieldwork.models.Location
import org.wit.archfieldwork.models.SiteModel


class SiteActivity : AppCompatActivity(), AnkoLogger {

    var site = SiteModel()
    lateinit var app: MainApp
    var edit = false
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    //var location = Location(49.002590, 12.097409, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("site_edit")) {
            edit = true
            site = intent.extras.getParcelable<SiteModel>("site_edit")
            siteName.setText(site.name)
            description.setText(site.description)
            btnAdd.setText(R.string.save_site)
            siteImage.setImageBitmap(readImageFromPath(this, site.image))
            if (site.image != null) {
                chooseImage.setText(R.string.change_site_image)
            }
        }

        btnAdd.setOnClickListener() {
            site.name = siteName.text.toString()
            site.description = description.text.toString()
            if (site.name.isEmpty()) {
                //app.sites.add(site.copy())
                toast(R.string.enter_site_name)
            } else {
                if (edit) {
                    app.sites.update(site.copy())
                } else {
                    app.sites.create(site.copy())
                }
            }
            info("add Button Pressed: $siteName")
            //app.sites.forEach{info("add Button Pressed:${it}")}
            //app.sites.findAll().forEach{info("add Button Pressed:${it}")}
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        chooseImage.setOnClickListener {
            info("Select image")
            showImagePicker(this, IMAGE_REQUEST)
        }

        siteLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (site.zoom != 0f) {
                location.lat =  site.lat
                location.lng = site.lng
                location.zoom = site.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_site, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.sites.delete(site)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    site.image = data.getData().toString()
                    siteImage.setImageBitmap(readImage(this, resultCode, data))      //show the Image
                    chooseImage.setText(R.string.change_site_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras.getParcelable<Location>("location")
                    site.lat = location.lat
                    site.lng = location.lng
                    site.zoom = location.zoom
                }
            }
        }
    }

}
