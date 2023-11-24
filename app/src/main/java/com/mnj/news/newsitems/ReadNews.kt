package com.mnj.news.newsitems

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ReadNews(url: String) {
    var backEnable by remember { mutableStateOf(false) }
    var webView: WebView? = null

    AndroidView(
        modifier = Modifier,
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                )
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        backEnable = view!!.canGoBack()
                    }
                }
                settings.javaScriptEnabled = true
                loadUrl(url)
                webView = this
            }
        }, update = {
            webView = it
        })
    BackHandler(enabled = backEnable) {
        webView?.goBack()
    }
}


