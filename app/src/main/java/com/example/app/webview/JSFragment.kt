package com.example.app.webview

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import com.example.app.R
import kotlinx.android.synthetic.main.fragment_j_s.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 *
 * 1、setJavaScriptEnabled，设置为true，就其名就知道是为了使能js的功能了。
  2、addJavascriptInterface，设置一个js调用webview的一个接口。这里实现的接口是JsInterface类，然后名字是control。其中JsInterface类里面有一个helloJs方法，并且标注了@JavascriptInterface。这样js端代码调用过来就会被知道了。
  3、setWebChromeClient：主要处理解析，渲染网页等浏览器做的事情，这里就用了系统默认的WebChromeClient，WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等，当然这个可以自定义，由于addJavascriptInterface方法是有漏洞的，很多开发者实现了jsbridge，就是自定义了WebChromeClient，然后封装了自己的一套协议，这个之后再分析分析。
  4、setWebViewClient：就是帮助WebView处理各种通知、请求事件的，这里主要就实现了一个onPageFinished方法，主要是当页面加载完了之后再调用到js的代码。
 *
 *
 */
class JSFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_j_s, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    fun init() {
        web_view.settings.apply {
            //设置js交互权限
            javaScriptEnabled = true

            //允许js弹框
            javaScriptCanOpenWindowsAutomatically = true
        }
        web_view.apply {
            //加载本地h5界面  file:///android_asset/
            loadUrl("file:android_asset/html/test.html")
            /**
             * @param object 这个就是管理java 与 js 方法的类，js吊起的时候就直接去这个地方找就完了
             * @param name js 中使用这个name当作当前类文件的标示，用于吊起java方法
             */
            addJavascriptInterface(
                WebIntentCallBack(this@JSFragment),
                WebIntentCallBack.WEB_FLAG
            )
        }

        web_view.webViewClient = object : WebViewClient() {
            //  设置打开网页，但不离开app
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
        //不添加 无法弹框
        web_view.webChromeClient = WebChromeClient()

        //点击 js显示弹框
        clickActionJs.setOnClickListener {
            //如果这里需要对4.4一下版本进行适配
            //格式为 html文件：方法（参数）
//            val method = "test:jsClick()"
            val method1 = "javascript:jsClickWithParams(\"" + "param1" + "\")"
            val method2 = "javascript:jsClick()"
            //为true执行的只能支持4.4以上的版本，优点为 效率高，
            if (Build.VERSION.SDK_INT > 18) {
                web_view.evaluateJavascript(method1, ValueCallback {
                    //js回调
                    Log.e("", "result :" + it)
                })
            } else {
                //没有版本限制，但是可以看到这个为我们初始化webView的方法，意味着我们可能需要重新加载页面，可以一目了然。
                web_view.loadUrl(method1)
            }
        }
    }

    class WebIntentCallBack(val fragment: JSFragment) {
        @JavascriptInterface
        fun jsActionCallBack(content: String) {
            fragment.title.apply {
                post { text = content }

            }
        }

        companion object {
            const val WEB_FLAG = "WebIntentCallBack"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        web_view.destroy()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JSFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}