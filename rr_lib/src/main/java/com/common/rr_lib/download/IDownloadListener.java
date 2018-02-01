package com.common.rr_lib.download;

/**
 * Created by muhanxi on 18/2/1.
 *
 */
public interface IDownloadListener {

    void onStartDownload();

    void onProgress(int progress);

    void onFinishDownload();

    void onFail(String errorInfo);

}
