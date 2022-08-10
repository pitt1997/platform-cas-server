package org.apereo.cas.web.util.crypto;

import org.apereo.cas.web.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.AbstractPasswordEncoder;

import java.util.Objects;

/**
 * @author ljs
 * @date 2022-06-29
 * @description 自定义密码加密
 */
public class SelfPasswordEncoder extends AbstractPasswordEncoder {

    private Logger logger = LoggerFactory.getLogger(SelfPasswordEncoder.class);

    private final BytesKeyGenerator saltGenerator;

    public SelfPasswordEncoder() {
        logger.info("init - SelfPasswordEncoder");
        this.saltGenerator = KeyGenerators.secureRandom();
    }

    @Override
    protected byte[] encode(CharSequence charSequence, byte[] salt) {
        return this.encode(charSequence).getBytes();
    }

    @Override
    public String encode(CharSequence charSequence) {
        // charSequence为输入的用户密码
        return charSequence.toString();
    }

    /**
     * @param rawPassword     用户输入的密码
     * @param encodedPassword 数据库中的密码
     * @return 匹配结果
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        byte[] digested = Hex.decode(Objects.requireNonNull(EncryptUtil.getMd5String(rawPassword.toString())));
        byte[] actual = Hex.decode(encodedPassword);
        boolean matches = matches(digested, actual);
        logger.info("password matches result is {}", matches);
        return matches;
    }

    /**
     * Constant time comparison to prevent against timing attacks.
     */
    protected static boolean matches(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expected.length; i++) {
            result |= expected[i] ^ actual[i];
        }
        return result == 0;
    }

}
