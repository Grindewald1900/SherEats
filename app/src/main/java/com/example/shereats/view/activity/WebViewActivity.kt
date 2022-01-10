package com.example.shereats.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.shereats.R
import com.example.shereats.utils.ConstantUtil

class WebViewActivity : AppCompatActivity() {
    private var url = "https://github.com/Grindewald1900/SherEats"
    private lateinit var webview: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        initView()
    }

    private fun initView(){
        if (null == intent.extras){
            return
        }
        url = intent.extras!!.getString(ConstantUtil.ENTITY_URL, "https://github.com/Grindewald1900/SherEats")
        webview = findViewById(R.id.webview_activity_webview)
        webview.apply {
            canGoBack()
            canGoForward()
        }
        webview.loadUrl(url)

    }
}