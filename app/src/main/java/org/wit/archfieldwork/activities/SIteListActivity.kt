package org.wit.archfieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.wit.archfieldwork.R
import org.wit.archfieldwork.main.MainApp

class SiteListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_list)
        app = application as MainApp
    }
}