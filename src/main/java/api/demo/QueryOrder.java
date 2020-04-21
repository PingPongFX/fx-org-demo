package api.demo;

import api.demo.util.RequestUtil;
import api.demo.util.UUIDGenerator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxian
 * @date 2018/12/3 2:20 PM
 */
public class QueryOrder {

    public static void request() {

        Map<String, Object> param = new HashMap<>();

        // 用户ID
        param.put("userId", 66);

        // 客户订单号
        param.put("clientOrderId", "org20200421002");

        // 平台订单号
        param.put("orderId", "15873693963797029");

        // 64位随机数
        param.put("nonce", UUIDGenerator.generate64());

        // 客户端时间戳
        param.put("timestamp", System.currentTimeMillis());

        String s = RequestUtil.get(param, "285fff0a0e824a678e8cc9632daec4d2", "http://127.0.0.1:8061/fxapi/v1/trade/query");
        System.out.println(s);
    }

    public static void main(String[] args) {
        request();
    }
}
