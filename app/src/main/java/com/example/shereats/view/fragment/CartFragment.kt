package com.example.shereats.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shereats.R
import com.example.shereats.databinding.CartFragmentBinding
import com.example.shereats.model.entity.SingletonUtil
import com.example.shereats.model.viewmodel.CartViewModel
import com.example.shereats.utils.ConstantUtil
import com.example.shereats.utils.LoginStatusUtil
import com.example.shereats.utils.TextUtil
import com.example.shereats.utils.ToastUtil
import com.example.shereats.view.activity.LoginActivity
import com.example.shereats.view.activity.ResultActivity
import com.example.shereats.view.adapter.CartAdapter
import com.example.shereats.view.custom.TransitionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CartFragment : Fragment() {
    private lateinit var binding: CartFragmentBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var mTransitionBtn: TransitionButton
    private var isClickedTransitionBtn = false

    companion object {
        fun newInstance() = CartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cart_fragment, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // Reset Animation only from OnBackPressed of new activity
        if(isClickedTransitionBtn){
            mTransitionBtn.resetAnimation(null, false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        viewModel.setOrderItems()
        viewModel.setTotalPrice()
        viewModel.getOrderItems().observe(viewLifecycleOwner) {
            binding.rvFragmentCart.adapter = CartAdapter(it, viewModel, true)
        }
        viewModel.getTotalPrice().observe(viewLifecycleOwner){
            setPrice(it)
        }
        viewModel.getIsUploadSuccess().observe(viewLifecycleOwner){
            if (it.result == ConstantUtil.STATE_SUCCESS){
                SingletonUtil.clearCurrentCart()
                viewModel.setOrderItems()
                viewModel.setTotalPrice()
            }else{
                ToastUtil.showShortMessage(getString(R.string.checkout_fail), requireContext())
            }
        }
        binding.rvFragmentCart.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentCart.itemAnimator = DefaultItemAnimator()
        initTransitionButton()
    }

    private fun initTransitionButton(){
        mTransitionBtn = binding.btnFragmentCartCheckout
        mTransitionBtn.setOnClickListener {
            if (SingletonUtil.isCurrentCartEmpty()){
                context?.let { it1 -> ToastUtil.showShortMessage(it1.getString(R.string.checkout_no_order), requireContext()) }
                return@setOnClickListener
            }

            mTransitionBtn.startAnimation()
            GlobalScope.launch {
                delay(ConstantUtil.ANIMATION_DELAY)
                val intent: Intent

                if (LoginStatusUtil.isLogin()){
                    intent = Intent(context, ResultActivity::class.java)
                    intent.putExtra(ConstantUtil.STRING_RESULT_ACTIVITY, ConstantUtil.RESULT_CORRECT)
                }else{
                    intent = Intent(context, LoginActivity::class.java)
                }
                mTransitionBtn.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, object: TransitionButton.OnAnimationStopEndListener{
                    override fun onAnimationStopEnd() {
                        isClickedTransitionBtn = true

                        viewModel.uploadOrderItems()
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }
                })
            }
        }
    }

    private fun setPrice(originPrice: Float){
        binding.tvShoppingCartPrice.text = TextUtil.getItemPrice(originPrice)
        binding.tvShoppingCartGst.text = TextUtil.getItemPrice(originPrice * 0.15f)
        binding.tvShoppingCartTotalPrice.text = TextUtil.getItemPrice(originPrice * 1.15f)
        if(originPrice <= 0f){
            binding.ivFragmentCartPlaceholder.visibility = View.VISIBLE
            binding.tvFragmentCartPlaceholder.visibility = View.VISIBLE
            binding.ivFragmentCartPlaceholder.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_iconmonstr_shopping_cart_23, null)
            binding.tvFragmentCartPlaceholder.text = resources.getString(R.string.checkout_no_order)
        }else{
            binding.ivFragmentCartPlaceholder.visibility = View.GONE
            binding.tvFragmentCartPlaceholder.visibility = View.GONE
        }
    }
}