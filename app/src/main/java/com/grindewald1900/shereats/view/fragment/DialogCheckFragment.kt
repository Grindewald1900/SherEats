package com.grindewald1900.shereats.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.databinding.FragmentDialogCheckBinding
import com.grindewald1900.shereats.model.interfaces.DialogCheckResult

class DialogCheckFragment(val text: String): DialogFragment() {
    private lateinit var binding: FragmentDialogCheckBinding
    private lateinit var mCheckResult: DialogCheckResult

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_check, container, false)
        initView()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCheckResult = activity as DialogCheckResult
    }

    private fun initView(){
        binding.tvFragmentDialogCheck.text = text
        binding.btnFragmentDialogCheckConfirm.setOnClickListener {
            mCheckResult.onDialogCheckCallBack(true)
            dismiss()
        }
        binding.btnFragmentDialogCheckCancel.setOnClickListener {
            mCheckResult.onDialogCheckCallBack(false)
            dismiss()
        }
    }

}