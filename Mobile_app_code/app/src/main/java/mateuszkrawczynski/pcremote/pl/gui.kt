package mateuszkrawczynski.pcremote.pl

import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mateuszkrawczynski.pcremote.pl.databinding.ActivityGuiBinding

class gui : AppCompatActivity() {
    lateinit var binding: ActivityGuiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ip = intent.getStringExtra("ip")
        if (ip.isNullOrEmpty()) {
            Toast.makeText(this, "IP address not provided", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val baseUrl = "http://$ip:6482/"
        Log.d("WebViewDebug", "Base URL: $baseUrl")

        // WebView configuration
        val myWebView: WebView = binding.apibox
        myWebView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowContentAccess = true
            cacheMode = WebSettings.LOAD_NO_CACHE
        }
        myWebView.clearCache(true)
        myWebView.clearHistory()

        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                Log.e("WebViewError", "Error: $error, URL: ${request.url}")
            }
        }

        // Button actions
        binding.off.setOnClickListener { myWebView.loadUrl("$baseUrl/off", mapOf("Cache-Control" to "no-cache")) }
        binding.pause.setOnClickListener { myWebView.loadUrl("$baseUrl/pause", mapOf("Cache-Control" to "no-cache")) }
        binding.fullscreen.setOnClickListener { myWebView.loadUrl("$baseUrl/fullscreen", mapOf("Cache-Control" to "no-cache")) }
        binding.next.setOnClickListener { myWebView.loadUrl("$baseUrl/next", mapOf("Cache-Control" to "no-cache")) }
        binding.youtube.setOnClickListener { myWebView.loadUrl("$baseUrl/youtube", mapOf("Cache-Control" to "no-cache")) }
        binding.volup.setOnClickListener { myWebView.loadUrl("$baseUrl/volumeup", mapOf("Cache-Control" to "no-cache")) }
        binding.voldown.setOnClickListener { myWebView.loadUrl("$baseUrl/volumedown", mapOf("Cache-Control" to "no-cache")) }
        binding.left.setOnClickListener { myWebView.loadUrl("$baseUrl/left", mapOf("Cache-Control" to "no-cache")) }
        binding.right.setOnClickListener { myWebView.loadUrl("$baseUrl/right", mapOf("Cache-Control" to "no-cache")) }
    }

}