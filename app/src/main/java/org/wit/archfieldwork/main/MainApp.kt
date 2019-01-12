package org.wit.archfieldwork.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.archfieldwork.models.SiteJSONStore
import org.wit.archfieldwork.models.SiteMemStore
import org.wit.archfieldwork.models.SiteModel
import org.wit.archfieldwork.models.SiteStore

class MainApp : Application(), AnkoLogger {

    //val sites = ArrayList<SiteModel>()
    lateinit var sites : SiteStore


    override fun onCreate() {
        super.onCreate()
        //sites = SiteMemStore()
        sites = SiteJSONStore(applicationContext)
        info("ArchFieldwork started")
    }
}