package org.wit.archfieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.archfieldwork.R
import org.wit.archfieldwork.main.MainApp
import org.wit.archfieldwork.models.SiteModel


class SiteActivity : AppCompatActivity(), AnkoLogger {

    var site = SiteModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        app = application as MainApp

        btnAdd.setOnClickListener() {
            site.name = siteName.text.toString()
            site.description = description.text.toString()
            if (site.name.isNotEmpty()) {
                app.sites.add(site.copy())
                info("add Button Pressed: $siteName")
                app.sites.forEach{info("add Button Pressed:${it}")}
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
}
