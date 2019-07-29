package com.stv.smartwebview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SmartWebViewFragment extends Fragment implements SmartWebViewContract.View {

    private WebView mWebView;
    private WebViewClient mWebViewClient;
    private WebChromeClient mWebChromeClient;
    private SmartWebViewPresenter mPresenter;
    private WebViewJavaScriptInterface mJSInterface;
    WebSettings mWebSettings;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.smart_web_view_fragment, container, false);
        mWebView = v.findViewById(R.id.web_view);
        configWebView();
        setPresenter(new SmartWebViewPresenter());
        mPresenter.setView(this);

        //Testes

        render("https://smart-webview-85bdc.web.app/");
        return v;
    }

    private void setPresenter(SmartWebViewPresenter smartWebViewPresenter) {
        mPresenter = smartWebViewPresenter;
    }

    /*
    * Para modificar as configurações da WebView, os objetos acessados abaixo devem ser manipulados nesse
    * método. Os métodos de interceptação de eventos devem sem implementados nos objetos de WebViewClient
    * e de WebChromeClient
     */
    private void configWebView() {
        mWebViewClient = new WebViewClient();
        mWebChromeClient = new WebChromeClient();
        mWebSettings = mWebView.getSettings();
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setAppCacheEnabled(false);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mJSInterface = new WebViewJavaScriptInterface(getContext());
        mWebView.addJavascriptInterface(mJSInterface, "JSInterface");
    }

    /*
    * A WebView deve acessar apenas arquivos locais !!
     */
    @Override
    public void render(String url)
    {
        //mWebView.loadUrl("file://" + url);
        //Testando
        mWebView.loadUrl(url);

    }
}