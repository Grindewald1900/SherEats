package com.grindewald1900.shereats.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import com.grindewald1900.shereats.R
import com.grindewald1900.shereats.view.fragment.ViewpagerTheme
import com.grindewald1900.shereats.databinding.ActivitySettingBinding
import com.grindewald1900.shereats.model.interfaces.DialogCheckResult
import com.grindewald1900.shereats.model.viewmodel.SettingViewModel
import com.grindewald1900.shereats.utils.ConstantUtil
import com.grindewald1900.shereats.view.fragment.DialogCheckFragment
import com.grindewald1900.shereats.view.transformer.ZoomOutPageTransformer

class SettingActivity : BaseActivityNoBar(), DialogCheckResult {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var viewModel: SettingViewModel
    private var tempBadgeId: Long = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        initView()
    }

    private fun initView(){
        viewModel = ViewModelProvider(this).get(SettingViewModel::class.java)
        viewModel.getBadgeSequence()
        binding.btnActivitySettingBack.setOnClickListener {
            onBackPressed()
        }
        initViewpager()
        setBadgeListener()
        setContributors()
    }

    private fun initViewpager(){
        val mPager = binding.viewpagerActivitySettingTheme
        val themes: MutableList<String> = mutableListOf("theme_1", "theme_2", "theme_3", "theme_4", "theme_5")
        val pagerAdapter = object: FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return themes.size
            }

            override fun createFragment(position: Int): Fragment {
                val id = resources.getIdentifier(themes[position], "drawable", packageName)
                return ViewpagerTheme(id, position + 1)
            }
        }
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(MarginPageTransformer(20))
        mPager.setPageTransformer(ZoomOutPageTransformer())
        mPager.offscreenPageLimit = 3

    }

    private fun setBadgeListener(){
        val grid = binding.badgeActivitySetting
        val size = grid.childCount
        for (i in 0 until size){
            val container = grid.getChildAt(i)
            container.setOnClickListener {
                tempBadgeId = i.toLong() + 1
                val mDialog = DialogCheckFragment(getString(R.string.hint_add_badge))
                mDialog.show(supportFragmentManager, ConstantUtil.TAG_DIALOG_CHECK)
            }
        }
    }

    private fun setContributors(){
        val intent = Intent(this, WebViewActivity::class.java)
        binding.ivActivitySettingYee.setOnClickListener {
            intent.putExtra(ConstantUtil.ENTITY_URL, "https://github.com/Grindewald1900")
            startActivity(intent)
        }
        binding.ivActivitySettingJo.setOnClickListener {
            intent.putExtra(ConstantUtil.ENTITY_URL, "https://www.linkedin.com/in/yuxuan-long-96a6b5180/")
            startActivity(intent)
        }
    }

    override fun onDialogCheckCallBack(isConfirm: Boolean) {
        if (tempBadgeId >= 0){
            if(isConfirm){
                viewModel.uploadBadge(tempBadgeId)
            }
        }
    }
}