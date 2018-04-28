package util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author ghf
 */
public class Md5 {
    /**
     * md5  md5加密方法
     * @param pass
     * @return
     */
    public static String addPass(String pass){
        String saltSource = "blog";
        String hashAlgorithmName = "MD5";
        Object salt = new Md5Hash(saltSource);
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, pass, salt, hashIterations);
        String password = result.toString();
        return password;
    }


}
