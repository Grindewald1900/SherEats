package com.example.shereats.view.fragment

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.shereats.R
import com.example.shereats.utils.ConstantUtil
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
    private lateinit var mView: View
    private lateinit var mImageView: ImageView
    private lateinit var mIvClose: ImageView
    private lateinit var mBtnConfirm: Button
    private lateinit var mBtnCancel: Button
    private var imageUri: Uri? = null
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            imageUri = it.data?.data
            mImageView.setImageURI(imageUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_dialog_upload_image, container, false)
        initView()
        return mView
    }

    private fun initView(){
        mImageView = mView.findViewById(R.id.iv_fragment_dialog_upload)
        mBtnConfirm = mView.findViewById(R.id.btn_fragment_dialog_upload_confirm)
        mBtnCancel = mView.findViewById(R.id.btn_fragment_dialog_upload_cancel)
        mIvClose = mView.findViewById(R.id.iv_fragment_dialog_upload_close)

        mBtnConfirm.setOnClickListener {
            upLoadImage()
        }
        mBtnCancel.setOnClickListener {
            dialog?.dismiss()
        }
        mIvClose.setOnClickListener {
            dialog?.dismiss()
        }
        mImageView.setOnClickListener {
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


    private fun upLoadImage(){
        if (null == imageUri){
            context?.let { ToastUtil.showShortMessage(getString(R.string.hint_no_image), it) }
            return
        }
        val format = ImageUtil.getImageFormat(imageUri!!, context).split("/").last()
        if (format != "jpg" && format != "jpeg"){
            context?.let { ToastUtil.showShortMessage(getString(R.string.hint_no_jpg), it) }
            return
        }
        val name = LoginStatusUtil.getUser().user_name
        val childPath = "user/$name.jpg"
        val imageReference = StorageUtil.reference.child(childPath)
        var upLoadTask = imageReference.putFile(imageUri!!)
        upLoadTask.addOnFailureListener{
            it.stackTrace
        }.addOnSuccessListener {
            context?.let { ToastUtil.showShortMessage(getString(R.string.upload_success), it) }
        }
    }
}