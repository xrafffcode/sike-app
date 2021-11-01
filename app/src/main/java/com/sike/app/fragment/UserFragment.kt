package com.sike.app.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.sike.app.R
import com.sike.app.SignIn
import com.sike.app.databinding.FragmentUserBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.delay
import java.io.ByteArrayOutputStream




class UserFragment : Fragment() {

    companion object{
        const val REQUEST_CAMERA = 100
    }

    private lateinit var auth : FirebaseAuth

    private lateinit var imageUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()

        val bind = FragmentUserBinding.inflate(layoutInflater)

        bind.swipeToRefreshLayout.setOnRefreshListener {
            Toast.makeText(activity, "Page Refreshed", Toast.LENGTH_SHORT).show()

            bind.swipeToRefreshLayout.isRefreshing = false
            val intent = Intent(activity, UserFragment::class.java)
            startActivity(intent)
        }

        bind.btnLogout.setOnClickListener{

            auth.signOut()
            val intent = Intent(this.requireContext(), SignIn::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        bind.ivProfile.setOnClickListener {
            intentCamera()
        }

        val container = bind.progressBar

        container.startShimmerAnimation()

        val user = auth.currentUser


        if (user != null){
            if (user.photoUrl != null){
                Picasso.get().load(user.photoUrl).into(bind.ivProfile)
            }else{
                Picasso.get().load("https://i.picsum.photos/id/1025/4951/33 Q 01.jpg?hmac=_aGh5AtoOChip_iaMo8ZvvytfEojcgqbCH7dzaz-H8Y").into(bind.ivProfile)
            }

            bind.etName.setText(user.displayName)
        }



        bind.btnUpdate.setOnClickListener {

            val loading = bind.progressbarPic
            loading.visibility = View.VISIBLE;

            val image = when{
                ::imageUri.isInitialized -> imageUri
                user?.photoUrl == null -> Uri.parse("https://i.picsum.photos/id/1025/4951/3301.jpg?hmac=_aGh5AtoOChip_iaMo8ZvvytfEojcgqbCH7dzaz-H8Y")
                else -> user.photoUrl
            }

            val name  = bind.etName.text.toString().trim()

            if (name.isEmpty()){
                bind.etName.error = "Nama Harus Diisi"
                bind.etName.requestFocus()
                return@setOnClickListener
            }

            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(activity, "Profil Updated", Toast.LENGTH_SHORT).show()
                            loading.visibility = View.INVISIBLE;
                        }else{
                            Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }




        return bind.root
    }


    private fun intentCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            activity?.packageManager?.let {
                intent.resolveActivity(it).also {
                    startActivityForResult(intent, REQUEST_CAMERA)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }
    }

    private fun uploadImage(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference
            .child("img/${FirebaseAuth.getInstance().currentUser?.uid}")

        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()

        val bind = FragmentUserBinding.inflate(layoutInflater)

        ref.putBytes(image)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    ref.downloadUrl.addOnCompleteListener{
                        it.result?.let {
                            imageUri = it
                            bind.ivProfile.setImageBitmap(imgBitmap)
                        }
                    }
                }
            }
    }


}