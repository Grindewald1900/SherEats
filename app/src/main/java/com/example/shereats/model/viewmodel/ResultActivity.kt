package com.example.shereats.model.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.shereats.R
import com.example.shereats.databinding.ActivityResultBinding
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.view.activity.MainActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var state = ConstantUtil.RESULT_DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        initView()
    }

    override fun onResume() {
        super.onResume()
//        Thread.sleep(ConstantUtil.RESULT_ACTIVITY_TIME)
//        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun initView(){
        state = intent.getIntExtra(ConstantUtil.STRING_RESULT_ACTIVITY, ConstantUtil.RESULT_DEFAULT)
        when(state){
            ConstantUtil.RESULT_CORRECT -> {
                binding.ivActivitySuccessImg.background = getDrawable(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
                binding.tvActivityResultText.text = getText(R.string.all_done)
            }

            ConstantUtil.RESULT_INCORRECT ->{
                binding.ivActivitySuccessImg.background = getDrawable(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)
                binding.tvActivityResultText.text = getText(R.string.try_again)
            }

            else -> {
                binding.ivActivitySuccessImg.background = getDrawable(R.drawable.ic_baseline_sentiment_neutral_24)
                binding.tvActivityResultText.text = getText(R.string.request_upload)
            }
        }
        binding.btnActivityResultBack.setOnClickListener {
            onBackPressed()
            finish()
        }
    }
}