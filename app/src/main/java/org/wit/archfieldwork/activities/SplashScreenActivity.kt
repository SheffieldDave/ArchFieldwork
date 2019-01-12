package org.wit.archfieldwork.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import org.wit.archfieldwork.R

class SplashScreenActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val background = object : Thread(){
            override fun run() {
                try {
                    Thread.sleep(5000)

                    val intent = Intent(baseContext, SiteListActivity::class.java)
                    startActivity(intent)


                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}