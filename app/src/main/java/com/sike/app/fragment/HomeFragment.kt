package com.sike.app.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.sike.app.R
import com.sike.app.SignIn
import com.sike.app.databinding.FragmentHomeBinding
import com.sike.app.databinding.FragmentUserBinding


class HomeFragment : Fragment() {

    private lateinit var auth : FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()


        val bind = FragmentHomeBinding.inflate(layoutInflater)

        val user = auth.currentUser

        bind.username.setText(user?.displayName)


        return bind.root

    }




}