package api.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhangxian
 * @date 2018/11/24 10:39 AM
 */
public class SHAUtil {

    static char[] HEX_DIGIT = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String sha512(String strSrc) {
        return doSha(strSrc, "UTF-8", "SHA-512");
    }

    public static boolean verifySha512(String strSrc, String sign) {
        return sha512(strSrc).equalsIgnoreCase(sign);
    }

    private static String doSha(String data, String charsetName, String algorithm) {
        MessageDigest messageDigest;
        String strDst = null;

        try {
            byte[] bt = data.getBytes(charsetName);
            messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(bt);
            byte[] digestData = messageDigest.digest();
            strDst = binToHex(digestData);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strDst;
    }

    public static String binToHex(byte[] bin) {
        if (bin == null) {
            throw new IllegalArgumentException("Parameter bin shouldn't be null");
        }
        int len = bin.length;
        char[] str = new char[len * 2];
        int k = 0;
        // 移位 输出字符串
        for (byte byte0 : bin) {
            str[k++] = HEX_DIGIT[byte0 >>> 4 & 0x0f];
            str[k++] = HEX_DIGIT[byte0 & 0x0f];
        }
        return new String(str);
    }

    public static void main(String[] args) {
        String s = sha512(
            "nonce=8234567890123456789012345678901234567890123456789012345678901238&symbol=CNHJPY&timestamp=1542974873185&userId=300005"
                + "123qwe");

        System.out.println(s);
    }
}
