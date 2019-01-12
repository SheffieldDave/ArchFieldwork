package org.wit.archfieldwork.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.archfieldwork.helpers.exists
import org.wit.archfieldwork.helpers.read
import org.wit.archfieldwork.helpers.write
import java.util.ArrayList
import java.util.*

val JSON_FILE = "sites.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<SiteModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class SiteJSONStore : SiteStore, AnkoLogger {

    val context: Context
    var sites = mutableListOf<SiteModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<SiteModel> {
        return sites
    }

    override fun create(site: SiteModel) {
        site.id = generateRandomId()
        sites.add(site)
        serialize()
    }


    override fun update(site: SiteModel) {
        val sitesList = findAll() as ArrayList<SiteModel>
        var foundPlacemark: SiteModel? = sitesList.find { p -> p.id == site.id }
        if (foundPlacemark != null) {
            foundPlacemark.name = site.name
            foundPlacemark.description = site.description
            foundPlacemark.image = site.image
            foundPlacemark.lat = site.lat
            foundPlacemark.lng = site.lng
            foundPlacemark.zoom = site.zoom
        }
        serialize()
    }


    private fun serialize() {
        val jsonString = gsonBuilder.toJson(sites, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
       sites = Gson().fromJson(jsonString, listType)
    }
}