package com.stv.smartwebview;

import android.os.Build;

class SmartWebViewPresenter implements SmartWebViewContract.Presenter {
    private SmartWebViewContract.View mView;
    private long mTimeStamp;
    private String mFileLocation;

    public  SmartWebViewPresenter()
    {
        mFileLocation = "/htmlModel.html";
        mTimeStamp = getTimeStampFromDisk();
    }

    private long getTimeStampFromDisk() {
        //TODO: Implementar leitura e gravação do TimeStamp (Em arquivo local ou cache)
        return Build.TIME;
    }

    @Override
    public void setView(SmartWebViewContract.View v) {
        this.mView = v;
    }

    /*
    * Busca o modelo da URL e salva o arquivo local
     */
    @Override
    public void fetchModelFromUrl(String url) {
        //TODO: Buscar da API!!
        mView.render(mFileLocation);
    }

    /*
     * Checa se o timestamp é válido (se precisa atualizar o pacote)
     * e chama o método fetchModelFromUrl(String) para buscar o modelo
     * apenas se necessário. No callback da requisição ele notifica a view
     * que deve renderizar a página na WebView
     */
    @Override
    public void fetchModelFromUrl(String fileUrl, String timestampCheckUrl)
    {
        //TODO: Validar Timestamp
        fetchModelFromUrl(fileUrl);

    }
}
