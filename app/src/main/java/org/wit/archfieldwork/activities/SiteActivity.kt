package org.wit.archfieldwork.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.archfieldwork.R
import org.wit.archfieldwork.helpers.readImage
import org.wit.archfieldwork.helpers.readImageFromPath
import org.wit.archfieldwork.helpers.showImagePicker
import org.wit.archfieldwork.main.MainApp
import org.wit.archfieldwork.models.SiteModel


class SiteActivity : AppCompatActivity(), AnkoLogger {

    var site = SiteModel()
    lateinit var app : MainApp
    var edit = false
    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if(intent.hasExtra("site_edit")){
            edit = true
            site = intent.extras.getParcelable<SiteModel>("site_edit")
            siteName.setText(site.name)
            description.setText(site.description)
            btnAdd.setText(R.string.save_site)
            siteImage.setImageBitmap(readImageFromPath(this,site.image))
        }

        btnAdd.setOnClickListener() {
            site.name = siteName.text.toString()
            site.description = description.text.toString()
            if (site.name.isEmpty()) {
                //app.sites.add(site.copy())
                toast(R.string.enter_site_name)
            }else {
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

        chooseImage.setOnClickListener{
            info("Select image")
            showImagePicker(this,IMAGE_REQUEST)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_site,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.item_cancel ->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            IMAGE_REQUEST -> {
                if(data != null){
                    site.image = data.getData().toString()
                    siteImage.setImageBitmap(readImage(this, resultCode,data))      //show the Image
                }
            }
        }
    }
}
