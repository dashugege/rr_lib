package com.common.rr_lib.download;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by muhanxi on 18/2/1.
 */

public class IResponseBody extends ResponseBody {


    private ResponseBody responseBody;
    private IDownloadListener iDownloadListener;
    private BufferedSource bufferedSource;


    public IResponseBody(IDownloadListener downloadListener, ResponseBody responseBody) {
        this.iDownloadListener = downloadListener;
        this.responseBody = responseBody;
    }


    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {

        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;


    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                iDownloadListener.onProgress((int)(totalBytesRead/responseBody.contentLength()) * 100);
                return bytesRead;
            }
        };
    }

}
