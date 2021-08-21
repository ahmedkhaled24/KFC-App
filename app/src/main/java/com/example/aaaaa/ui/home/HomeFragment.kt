package com.example.aaaaa.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aaaaa.*
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.annotations.RealmClass
import kotlinx.android.synthetic.main.alertimage.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(this, Observer {
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //configuration realm
        Realm.init(activity)

        fitchData()

        recycleHome.layoutManager = LinearLayoutManager(this.context,RecyclerView.VERTICAL,false)
        recycleHome.adapter = adapter_home()


        face_icon_id.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/kfcarabia")))
        }

        share_icon_id.setOnClickListener {
            val sent = Intent()
            sent.action = Intent.ACTION_SEND
            sent.putExtra(Intent.EXTRA_TEXT, "Download this apss : \n https://play.google.com/store/apps/com.example.aagfaaa")
            sent.type = "text/plain"
            startActivity(Intent.createChooser(sent, "Choose the app .."))
        }

        imageView9.setOnClickListener {
            runAlertImage()
        }


    }



    fun fitchData(){
        val url = "https://jsonplaceholder.typicode.com/"
        // configuration for retrofit library
        val retfrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
        // كدا عملت كونفجريشن للداتا بتاعتي

        // هعمل انترفيس اسمه اي بي اي

        //----------------

        val api: API = retfrofit.create(API::class.java)
        val call = api.getData()
        var item = call.enqueue(object : Callback<ArrayList<data>> {

            override fun onFailure(call: Call<ArrayList<data>>, t: Throwable)
            {
                Toast.makeText(activity,"غير قادر علي الوصول للسيرفر",Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
            }

            override fun onResponse(call: Call<ArrayList<data>>, response: Response<ArrayList<data>>)
            {

                // تعريف لل ريلم
                var config = RealmConfiguration.Builder().name("database").build()
                var realm = Realm.getInstance(config)

                // بيمسك كل البيانات جوا الاولداتا
                var allData = response.body()

                // دي علشان مايعملش كراش لما اجي افتحه للمرة التانية لانه بيضيف البيانات كلها تاني وبالتالي بيكرر الايديهات وبيطلع ايرور
                // يعني هنا بيعمل تشيك لو فاضية هيملاها بيانات ولو مش فاضية مش هيعمل حاجة
                if (realm.where(database::class.java).findAll().isEmpty()){
                    realm.executeTransaction{
                        for (i in allData!!){
                            val dataRealm = realm.createObject(database::class.java,i.id)
                            dataRealm.postId = i.postId
                            dataRealm.name = i.name
                            dataRealm.email = i.email
                            dataRealm.body = i.body
                        }
                    }
                }

                //update for new info
                if (realm.where(database::class.java).findAll().size != allData!!.size){
                    realm.executeTransaction{
                        realm.deleteAll()
                        for (i in allData!!){
                            val dataRealm = realm.createObject(database::class.java,i.id)
                            dataRealm.postId = i.postId
                            dataRealm.name = i.name
                            dataRealm.email = i.email
                            dataRealm.body = i.body
                        }
                    }
                }

                recycleHome.adapter = adapter_home()
                progressBar.visibility=View.GONE
            }

        })
    }


    fun runAlertImage(){
        var alertBuilder = AlertDialog.Builder(activity)
        var inflater = LayoutInflater.from(activity)
        var view = inflater.inflate(R.layout.alertimage,null)
        alertBuilder.setView(view)
        var alertDialog = alertBuilder.create()
        alertDialog.show()
    }
}