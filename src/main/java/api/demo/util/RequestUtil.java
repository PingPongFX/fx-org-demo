package api.demo.util;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangxian
 * @date 2018/11/24 10:37 AM
 */
public class RequestUtil {

    public static String post(Map<String, Object> param, String secret, String url) {
        OkHttpClient client = new OkHttpClient();

        String content = JSON.toJSONString(param);
        RequestBody requestBody = RequestBody.create(content, MediaType.parse("application/json;charset=utf-8"));

        String sign = "";
        if (StringUtils.isNotBlank(secret)) {
            sign = SHAUtil.sha512(content + secret);
        }

        Request post = new Request.Builder()
            .url(url)
            .addHeader("sign", sign)
            .post(requestBody)
            .build();

        Call call = client.newCall(post);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get(Map<String, Object> param, String secret, String url) {
        OkHttpClient client = new OkHttpClient();
        String paramStr = toSortedQueryStr(param);
        String sign = "";
        if (StringUtils.isNotBlank(secret)) {
            sign = SHAUtil.sha512(paramStr + secret);
        }

        Request get = new Request.Builder()
            .url(url + "?" + paramStr)
            .addHeader("sign", sign)
            .get()
            .build();

        Call call = client.newCall(get);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String toSortedQueryStr(Map<String, Object> param) {

        StringBuilder sb = new StringBuilder();

        // 对键进行排序
        List<String> keys = new ArrayList<>(param.keySet());
        Collections.sort(keys);

        // 构建查询字符串
        for (String key : keys) {
            Object value = param.get(key);
            if (Objects.isNull(value)) {
                continue;
            }
            sb.append(key).append("=").append(value).append("&");
        }

        int end = sb.length();
        if (sb.length() > 0) {
            end -= 1; // 去除最后一个&
        }

        return sb.substring(0, end);
    }
}
