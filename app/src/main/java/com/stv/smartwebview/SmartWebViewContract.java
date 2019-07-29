package com.stv.smartwebview;

interface SmartWebViewContract {
    interface Presenter {
        void setView(View v);

        void fetchModelFromUrl(String url);

        void fetchModelFromUrl(String fileUrl, String timestampCheckUrl);
    }

    interface View {

        /*
        * A WebView deve acessar apenas arquivos locais !!
         */
        void render(String url);
    }
}
