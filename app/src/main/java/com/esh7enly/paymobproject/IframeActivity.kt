package com.esh7enly.paymobproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import com.esh7enly.paymobproject.databinding.ActivityIframeBinding

private const val TAG = "IframeActivity"

class IframeActivity : AppCompatActivity()
{
    private var iframeLink = ""
    private val ui by lazy{
        ActivityIframeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

        Log.d(TAG, "diaa getFinal token: ${Constant.finalToken}")

        iframeLink = "https://accept.paymobsolutions.com/api/acceptance/iframes/675692?payment_token=${Constant.finalToken}"

        val newLink = "https://accept.paymob.com/api/acceptance/iframes/675693?payment_token=${Constant.finalToken}"
      //  val das = "accept.paymob.com/api/acceptance/iframe/675693?token=${Constant.finalToken}"
        Log.d(TAG, "diaa getLink: $newLink")
        ui.iframeView.webViewClient = WebViewClient()

        // this will load the url of the website
        ui.iframeView.loadUrl(newLink)

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        ui.iframeView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        ui.iframeView.settings.setSupportZoom(true)

    }
}