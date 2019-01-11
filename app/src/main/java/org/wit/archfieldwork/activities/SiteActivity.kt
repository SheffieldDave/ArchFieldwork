package org.wit.archfieldwork.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_site.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.archfieldwork.R
import org.wit.archfieldwork.models.SiteModel


class SiteActivity : AppCompatActivity(), AnkoLogger {

    var site = SiteModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)


        btnAdd.setOnClickListener() {
            site.name = siteName.text.toString()

            if (site.name.isNotEmpty()) {
                info("add Button Pressed: $siteName")
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
}
