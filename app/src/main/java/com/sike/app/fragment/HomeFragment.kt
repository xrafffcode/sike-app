package com.sike.app.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.sike.app.DetailRsActivity
import com.sike.app.R
import com.sike.app.RumahSakit
import com.sike.app.SignIn
import com.sike.app.adapter.RsAdapter
import com.sike.app.databinding.FragmentHomeBinding
import com.sike.app.databinding.FragmentUserBinding


class HomeFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()


        val bind = FragmentHomeBinding.inflate(layoutInflater)

        val user = auth.currentUser

        bind.username.setText(user?.displayName)

        val RsList = listOf<RumahSakit>(
            RumahSakit(
                R.drawable.margono,
                nameRs = "Rumah Sakit Margono",
                locRs = "Purwokerto",
                descRs  = "RSU Santa Elisabeth Purwokerto adalah Rumah Sakit Umum yang berada di Purwokerto, tepatnya di jalan dr Angka no 40 Purwokerto. Rumah Sakit ini dikelola oleh Yayasan Santo Dominikus yang terdiri dari suster-suster Ordo Pewarta atau OP."
            ),

            RumahSakit(
                R.drawable.elisabet,
                nameRs = "Rumah Sakit Elizabeth",
                locRs = "Purwokerto",
                descRs = "RSU Santa Elisabeth Purwokerto adalah Rumah Sakit Umum yang berada di Purwokerto, tepatnya di jalan dr Angka no 40 Purwokerto. Rumah Sakit ini dikelola oleh Yayasan Santo Dominikus yang terdiri dari suster-suster Ordo Pewarta atau OP.",
            ),

            RumahSakit(
                R.drawable.wijaya,
                nameRs = "Rs Wijaya Kusuma",
                locRs = "Purwokerto",
                descRs  = "RSU Santa Elisabeth Purwokerto adalah Rumah Sakit Umum yang berada di Purwokerto, tepatnya di jalan dr Angka no 40 Purwokerto. Rumah Sakit ini dikelola oleh Yayasan Santo Dominikus yang terdiri dari suster-suster Ordo Pewarta atau OP."
            )





        )

        bind.rvClass.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        bind.rvClass.setHasFixedSize(true)
        bind.rvClass.adapter = RsAdapter(requireActivity(), RsList){
            val intent = Intent(this.requireContext(), DetailRsActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)

        }

        return bind.root

    }






}