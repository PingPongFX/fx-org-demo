package api.demo;

import api.demo.util.RequestUtil;
import api.demo.util.UUIDGenerator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxian
 * @date 2018/11/24 10:39 AM
 */
public class NewOrder {

    public static void request() {

        Map<String, Object> param = new HashMap<>();

        // 订单类型
        param.put("orderType", "LOCK");

        // 用户ID
        param.put("userId", 66);

        // 客户订单号 请保证唯一 若重复 会返回第一次收到此ID的订单信息
        param.put("clientOrderId", "org20200421003");

        // 卖出货币
        param.put("sellCurrency", "USD");

        // 买入货币
        param.put("buyCurrency", "CNH");

        // 卖出货币数量 或者 买入货币数量 二选一
        param.put("sellAmount", "1000");
        param.put("buyAmount", null);

        // rateId 通过quote接口获取的汇率ID
        param.put("rateId", "1252441972168171521");

        // 64位随机数
        param.put("nonce", UUIDGenerator.generate64());

        // 客户端时间戳
        param.put("timestamp", System.currentTimeMillis());

        String s = RequestUtil.post(param, "285fff0a0e824a678e8cc9632daec4d2", "http://127.0.0.1:8061/fxapi/v1/trade/create");
        System.out.println(s);
    }

    public static void main(String[] args) {
        request();
    }
}
