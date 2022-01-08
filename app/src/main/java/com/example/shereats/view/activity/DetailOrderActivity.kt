package com.example.shereats.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shereats.R
import com.example.shereats.databinding.ActivityDetailOrderBinding
import com.example.shereats.model.entity.Order
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.TextUtil
import com.example.shereats.view.adapter.CartAdapter
import com.example.shereats.view.custom.TransitionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailOrderActivity : BaseActivityBar() {
    private lateinit var binding: ActivityDetailOrderBinding
    private lateinit var mOrder: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_order)
        initView()
    }

    private fun initView(){
        if(intent.extras != null){
            mOrder = intent.getSerializableExtra(ConstantUtil.ENTITY_ORDER) as Order
        }
        binding.rvActivityOrder.layoutManager = LinearLayoutManager(this)
        binding.rvActivityOrder.adapter = CartAdapter(mOrder.items!!, null, false)
        initTransitionButton()
        setPrice(mOrder.price!!.toDouble())
    }

    private fun initTransitionButton(){
        val button = binding.btnActivityOrderConfirm
        button.setOnClickListener {
            button.startAnimation()
            lifecycleScope.launch{
                delay(300)
                withContext(Dispatchers.Main){
                    button.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, object: TransitionButton.OnAnimationStopEndListener{
                        override fun onAnimationStopEnd() {
                            finish()
                        }
                    })
                }
            }

        }
    }

    private fun setPrice(originPrice: Double) {
        binding.tvShoppingOrderPrice.text = TextUtil.getItemPrice(originPrice)
        binding.tvShoppingOrderGst.text = TextUtil.getItemPrice(originPrice * 0.15f)
        binding.tvShoppingOrderTotalPrice.text = TextUtil.getItemPrice(originPrice * 1.15f)
    }
}