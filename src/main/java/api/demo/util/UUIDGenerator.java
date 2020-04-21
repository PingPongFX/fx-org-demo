package api.demo.util;

import java.util.UUID;

/**
 * UUID 生成器
 *
 * @author zhangxian
 */
public class UUIDGenerator {

    public static String generate64() {
        return generate() + generate();
    }

    public static String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
