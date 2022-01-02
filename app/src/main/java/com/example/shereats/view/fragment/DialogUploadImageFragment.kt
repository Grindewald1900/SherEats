package com.example.shereats.view.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.shereats.R
import com.example.shereats.databinding.FragmentDialogUploadImageBinding
import com.example.shereats.utils.ImageUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.ToastUtil
import com.example.shereats.utils.firebase.StorageUtil


/**
 * Created by Yee on 2021-12-26.
 * Github: Grindewald1900
 * Email: grindewald1504@gmail.com
 */
class DialogUploadImageFragment: DialogFragment(){
    private lateinit var binding: FragmentDialogUploadImageBinding
    private lateinit var mRefreshImage: OnRefreshImage
    private var imageUri: Uri? = null
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            imageUri = it.data?.data
            binding.ivFragmentDialogUpload.setImageURI(imageUri)
        }
    }

    /**
     * The interface is implemented by {@see UserInfoActivity},
     * to refresh the profile image after the dialog is dismissed
     */
    interface OnRefreshImage{
        fun refreshImage()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mRefreshImage = activity as OnRefreshImage
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_upload_image, container, false)
        initView()
        return binding.root
    }

    private fun initView(){
        binding.btnFragmentDialogUploadConfirm.setOnClickListener {
            upLoadImage()
        }
        binding.btnFragmentDialogUploadCancel.setOnClickListener {
            dismissDialog()
        }
        binding.ivFragmentDialogUploadClose.setOnClickListener {
            dismissDialog()
        }
        binding.ivFragmentDialogUpload.setOnClickListener {
            selectImage()
        }
    }

    /**
     * Choose image from photo gallery
     */
    private fun selectImage(){
        var intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        intent = Intent.createChooser(intent, "Select Image")

        resultLauncher.launch(intent)
    }


    /**
     *Upload image(local file) to Firebase
     */
    private fun upLoadImage(){
        if (null == imageUri){
            context?.let { ToastUtil.showShortMessage(getString(R.string.hint_no_image), it) }
            return
        }
        val format = ImageUtil.getImageFormat(imageUri!!, context).split("/").last()
        // Only jpg format is allowed
        if (format != "jpg" && format != "jpeg"){
            context?.let { ToastUtil.showShortMessage(getString(R.string.hint_no_jpg), it) }
            return
        }
        val name = LoginStatusUtil.getUser().user_name
        val childPath = "user/$name.jpg"
        val imageReference = StorageUtil.reference.child(childPath)
        val upLoadTask = imageReference.putFile(imageUri!!)
        upLoadTask.addOnFailureListener{
            it.stackTrace
        }.addOnSuccessListener {
            hideProgressBar()
            context?.let { ToastUtil.showShortMessage(getString(R.string.upload_success), it) }
            dismissDialog()
        }.addOnProgressListener {
            val progress = (100 * it.bytesTransferred) / it.totalByteCount
            showProgressBar(progress)
        }
    }

    private fun dismissDialog(){
        dialog?.dismiss()
        mRefreshImage.refreshImage()
    }

    @SuppressLint("SetTextI18n")
    private fun showProgressBar(progress: Long){
        binding.ivFragmentDialogUploadLoadingLeft.visibility = View.VISIBLE
        binding.ivFragmentDialogUploadLoadingRight.visibility = View.VISIBLE
        Glide.with(this).load(R.drawable.animation_loading_2_purple).into(binding.ivFragmentDialogUploadLoadingLeft)
        Glide.with(this).load(R.drawable.animation_loading_2_purple).into(binding.ivFragmentDialogUploadLoadingRight)
        binding.tvFragmentDialogUploadLoadingMiddle.text = "% ${progress.toInt()}"
    }

    private fun hideProgressBar(){
        binding.ivFragmentDialogUploadLoadingLeft.visibility = View.GONE
        binding.ivFragmentDialogUploadLoadingRight.visibility = View.GONE
        binding.tvFragmentDialogUploadLoadingMiddle.visibility = View.INVISIBLE
    }
}