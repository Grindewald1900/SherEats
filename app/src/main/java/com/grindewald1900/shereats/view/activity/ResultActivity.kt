package com.grindewald1900.shereats.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.databinding.ActivityResultBinding
import com.grindewald1900.shereats.utils.ConstantUtil

class ResultActivity : BaseActivityNoBar() {
    private lateinit var binding: ActivityResultBinding
    private var state = ConstantUtil.RESULT_DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        initView()
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