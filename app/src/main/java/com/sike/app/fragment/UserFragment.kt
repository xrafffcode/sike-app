package com.sike.app.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.sike.app.R
import com.sike.app.SignIn
import com.sike.app.databinding.FragmentHomeBinding
import com.sike.app.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()


        val bind = FragmentUserBinding.inflate(layoutInflater)
        

        bind.logout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this.requireContext(), SignIn::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        return bind.root
    }

}