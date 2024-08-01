package com.stone.quartz.service;

import java.io.UnsupportedEncodingException;
        import java.io.IOException;
        import java.security.KeyStore;
        import java.security.cert.CertificateException;
        import java.security.cert.X509Certificate;
        import java.util.*;
        import javax.net.ssl.SSLContext;

//        import com.alibaba.fastjson.JSON;
import com.stone.quartz.util.RSAUtil;
import com.alibaba.fastjson2.JSON;
import org.apache.http.HttpEntity;
        import org.apache.http.NameValuePair;
        import org.apache.http.StatusLine;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.config.RequestConfig;
        import org.apache.http.client.entity.GzipCompressingEntity;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.CloseableHttpResponse;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.client.utils.HttpClientUtils;
        import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
        import org.apache.http.entity.ByteArrayEntity;
        import org.apache.http.entity.StringEntity;
        import org.apache.http.impl.client.CloseableHttpClient;
        import org.apache.http.impl.client.HttpClients;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.ssl.SSLContextBuilder;
        import org.apache.http.ssl.TrustStrategy;
        import org.apache.http.util.EntityUtils;

public class Rong360Service {

    private final String appId;
    private final String privateKey;
    private final boolean test; // 是否联调

    public Rong360Service(String appId, String privateKey, boolean test) {
        this.appId = appId;
        this.privateKey = privateKey;
        this.test = test;
    }

    public String getorderlist(Object biz_data) {
        Map<String, String> postParameters = new HashMap<String, String>();
        postParameters.put("app_id", appId);
        postParameters.put("biz_data", JSON.toJSONString(biz_data));
        postParameters.put("format", "json");
        postParameters.put("method", "bd.api.common.order.getorderlist");
        postParameters.put("sign_type", "RSA");
        postParameters.put("timestamp", String.valueOf(System.currentTimeMillis()));
        postParameters.put("version", "1.0");
        postParameters.put("sign", RSAUtil.getSign(postParameters,privateKey));

        String url = "https://dopen.rong360.com/gateway";
        if (test) {
            url = "https://test-openapi-daikuan.rong360.com/gateway";
        }

        return receiveformFullResult(url, null, postParameters, null);

    }

    public String feedback(Object biz_data) {
        Map<String, String> postParameters = new HashMap<String, String>();
        postParameters.put("app_id", appId);
        postParameters.put("biz_data", JSON.toJSONString(biz_data));
        postParameters.put("format", "json");
        postParameters.put("method", "bd.api.common.order.orderfeedbackbeta");
        postParameters.put("sign_type", "RSA");
        postParameters.put("timestamp", String.valueOf(System.currentTimeMillis()));
        postParameters.put("version", "1.0");
        postParameters.put("sign", RSAUtil.getSign(postParameters,privateKey));

        String url = "https://openapi.rong360.com/gateway";
        if (test) {
            url = "https://test-openapi-daikuan.rong360.com/gateway";
        }

        return receiveformFullResult(url, null, postParameters, null);

    }


    public  String receiveformFullResult(String requestUri, String errMessage, Map<String, String> params, Map<String, String> headers) {
        String responseStr = doHttpPost(requestUri, errMessage, headers, params, "UTF-8");
        return responseStr;
    }


    public static String doHttpPost(String uri, String errMessage, Map<String, String> headers, Object param, String charset) {
        if(uri != null && !"".equals(uri)) {
            CloseableHttpClient httpClient = createHttpClient(uri);
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setConfig(RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(3000).setSocketTimeout(3000).build());
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
            if(Objects.nonNull(headers) && headers.size() > 0) {
                headers.forEach((key, value) -> {
                    httpPost.setHeader(key, value);
                });
            }

            HttpEntity requestEntity = null;
            if(param != null && !"".equals(param)) {
                requestEntity = getRequestEntity(param, charset);
            }

            httpPost.setEntity(requestEntity);
            CloseableHttpResponse response = null;
            HttpEntity responseEntity = null;

            Object var13;
            try {
                response = httpClient.execute(httpPost);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                String result;
                if(statusCode == 200) {
                    responseEntity = response.getEntity();
                    result = EntityUtils.toString(responseEntity, charset);
                    String var14 = result;
                    return var14;
                }

                result = null;
                var13 = result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    EntityUtils.consume(responseEntity);
                } catch (IOException var32) {
                }

                HttpClientUtils.closeQuietly(response);

                try {
                    EntityUtils.consume(requestEntity);
                } catch (IOException var31) {
                }

                HttpClientUtils.closeQuietly(httpClient);
            }

            return (String)var13;
        } else {
            return null;
        }
    }

    private static CloseableHttpClient createHttpClient(String uri) {
        if(uri != null && uri.indexOf("https") > -1) {
            try {
                SSLContext sslContext = (new SSLContextBuilder()).loadTrustMaterial((KeyStore)null, new TrustStrategy() {
                    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        return true;
                    }
                }).build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
                return HttpClients.custom().setSSLSocketFactory(sslsf).build();
            } catch (Exception var3) {
                return null;
            }
        }

        return HttpClients.createDefault();
    }

    private static HttpEntity getRequestEntity(Object param, String charset) {
        if(param == null) {
            return null;
        } else {
            int paramSize;
            if(param instanceof String) {
                String paramStr = (String)param;
                paramSize = paramStr.indexOf("?");
                if(paramSize > -1) {
                    paramStr = paramStr.substring(paramSize + 1);
                }

                StringEntity stringEntity = new StringEntity(paramStr, charset);
                stringEntity.setContentType("application/x-www-form-urlencoded;charset=" + charset);
                return stringEntity;
            } else if(param instanceof byte[]) {
                ByteArrayEntity entity = new ByteArrayEntity((byte[])((byte[])param));
                GzipCompressingEntity gzipCompressingEntity = new GzipCompressingEntity(entity);
                return gzipCompressingEntity;
            } else if(!(param instanceof Map)) {
                return null;
            } else {
                Map paramMap = (Map)param;
                paramSize = paramMap.size();
                if(paramSize == 0) {
                    return null;
                } else {
                    List<NameValuePair> list = new ArrayList(paramSize);
                    Iterator iterator = paramMap.keySet().iterator();

                    while(iterator.hasNext()) {
                        Object key = iterator.next();
                        Object value = paramMap.get(key);
                        NameValuePair nameValuePair = new BasicNameValuePair(key.toString(), value.toString());
                        list.add(nameValuePair);
                    }

                    UrlEncodedFormEntity urlEncodedFormEntity = null;

                    try {
                        urlEncodedFormEntity = new UrlEncodedFormEntity(list, charset);
                    } catch (UnsupportedEncodingException var9) {
                    }

                    return urlEncodedFormEntity;
                }
            }
        }
    }

}
