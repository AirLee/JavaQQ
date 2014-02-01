package com.xiaolanglang.javaqq.login.md5;

import com.xiaolanglang.javaqq.exception.QqRuntimeException;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * 生成加密后的密码
 * Created by 阳 on 14-1-30.
 */
public class Md5 {

    private String md5(String originalText) {
        String result;
        try {
            byte buf[] = originalText.getBytes("ISO-8859-1");
            StringBuilder hexString = new StringBuilder();
            String digit;


            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(buf);

            byte[] digest = algorithm.digest();

            for (byte aDigest : digest) {
                digit = Integer.toHexString(0xFF & aDigest);

                if (digit.length() == 1) {
                    digit = "0" + digit;
                }

                hexString.append(digit);
            }

            result = hexString.toString();
        } catch (Exception ex) {
            result = "";
        }

        return result.toUpperCase();
    }

    private String hexchar2bin(String md5str) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(
                md5str.length() / 2);

        for (int i = 0; i < md5str.length(); i = i + 2) {
            String hexstring = "0123456789ABCDEF";
            baos.write((hexstring.indexOf(md5str.charAt(i)) << 4 | hexstring
                    .indexOf(md5str.charAt(i + 1))));
        }
        try {
            return new String(baos.toByteArray(), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        throw new QqRuntimeException("UnsupportedEncodingException");
    }

    public String getPassword(String qq, String password, String captcha) {
        try {
            String P = hexchar2bin(md5(password));
            String U = md5(P + hexchar2bin(qq.replace("\\x", "").toUpperCase()));

            return md5(U + captcha.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
