package org.wit.archfieldwork.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_site_list.*
import kotlinx.android.synthetic.main.card_site.view.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult

import org.wit.archfieldwork.R
import org.wit.archfieldwork.main.MainApp
import org.wit.archfieldwork.models.SiteModel

class SiteListActivity : AppCompatActivity(), SiteListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_list)
        app = application as MainApp

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        //recyclerView.adapter = SiteAdapter(app.sites)
        //recyclerView.adapter = SiteAdapter(app.sites.findAll(),this)
        loadSites()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.item_add -> startActivityForResult<SiteActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSiteClick(site: SiteModel) {
        startActivityForResult(intentFor<SiteActivity>().putExtra("site_edit", site),0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView.adapter?.notifyDataSetChanged()
        loadSites()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadSites(){
        showSites(app.sites.findAll())
    }

    fun showSites(sites:List<SiteModel>){
        recyclerView.adapter = SiteAdapter (sites, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}

