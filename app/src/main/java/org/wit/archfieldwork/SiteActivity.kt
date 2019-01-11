package org.wit.archfieldwork

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class SiteActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)
        info("Fieldwork Start..")
    }
}
