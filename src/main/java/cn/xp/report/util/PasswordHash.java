package cn.xp.report.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordHash {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final int SALT_BYTE_SIZE = 24;

    public static final int HASH_BYTE_SIZE = 24;

    public static final int PBKDF2_ITERATIONS = 1000;

    public static final int ITERATION_INDEX = 0;

    public static final int SALT_INDEX = 1;

    public static final int PBKDF2_INDEX = 2;

    public PasswordHash() {
    }

    /**
     * 生成密码hash
     */
    public static String createHash(String password) {
        if (password == null) {
            return password;
        }
        try {
            return createHash(password.toCharArray());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
            return password;
        }
    }

    /**
     * 生成密码hash
     */
    public static String createHash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[24];
        random.nextBytes(salt);
        byte[] hash = pbkdf2(password, salt, 1000, 24);
        return "1000:" + toHex(salt) + ":" + toHex(hash);
    }

    public static boolean validatePassword(String password, String correctHash) {
        if (password == null || password.length() < 3 || correctHash == null || correctHash.length() < 3) {
            return false;
        }
        return validatePassword(password.toCharArray(), correctHash);
    }

    /**
     * 验证密码是否与hash过的密码相同
     */
    public static boolean validatePassword(char[] password, String correctHash) {
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = null;
        try {
            testHash = pbkdf2(password, salt, iterations, hash.length);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte[] hash, byte[] testHash) {
        int diff = hash.length ^ testHash.length;

        for (int i = 0; i < hash.length && i < testHash.length; ++i) {
            diff |= hash[i] ^ testHash[i];
        }

        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return skf.generateSecret(spec).getEncoded();
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];

        for (int i = 0; i < binary.length; ++i) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = array.length * 2 - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", new Object[] { Integer.valueOf(0) }) + hex
                : hex;
    }

    public static void main(String[] args) {
        String pwd = null;
        System.out.println(createHash(pwd));
    }
}
