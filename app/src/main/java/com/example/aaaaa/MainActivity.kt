package com.example.aaaaa

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    lateinit var mAdView : AdView

    private lateinit var appBarConfiguration: AppBarConfiguration

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_about,R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navView.menu.findItem(R.id.nav_share).setCheckable(false)
        navView.menu.findItem(R.id.nav_share).setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.nav_share ->{
                    drawer_layout.closeDrawer(GravityCompat.START)
                    val sent = Intent()
                    sent.action = Intent.ACTION_SEND
                    sent.putExtra(Intent.EXTRA_TEXT, "Download this apss : \n https://play.google.com/store/apps/com.example.aaaaa")
                    sent.type = "text/plain"
                    startActivity(Intent.createChooser(sent, "Choose the app .."))

                    true
                }
                else -> false
            }
        }


        navView.menu.findItem(R.id.nav_send).setCheckable(false)
        navView.menu.findItem(R.id.nav_send).setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.nav_send ->{
                    drawer_layout.closeDrawer(GravityCompat.START)
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "ahmedhamtary@gmail.com" + "?subject=" + "رسالة من التطبيق")))

                    true
                }
                else -> false
            }
        }



    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
