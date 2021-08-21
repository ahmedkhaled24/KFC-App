package com.example.aaaaa.ui.offer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.aaaaa.R

class OfferFragment : Fragment() {

    private lateinit var slideshowViewModel: OfferViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProviders.of(this).get(OfferViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_offer, container, false)
        slideshowViewModel.text.observe(this, Observer {
        })
        return root
    }

}