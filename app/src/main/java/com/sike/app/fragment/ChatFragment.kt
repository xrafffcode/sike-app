package com.sike.app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.auth.User
import com.google.firebase.messaging.FirebaseMessagingService
import com.sike.app.R
import com.sike.app.databinding.FragmentChatBinding
import com.sike.app.databinding.FragmentHomeBinding


class ChatFragment : Fragment() {

    var userList = ArrayList<User>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentChatBinding.inflate(layoutInflater)


        return bind.root
    }


}