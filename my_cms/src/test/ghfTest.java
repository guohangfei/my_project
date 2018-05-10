import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class ghfTest {


    public void subString(){
        String href="https://www.freexs.org/novel/106/106911/index";
        System.out.println(href.substring(0,href.length()-10));

    }

    @Test
    public void md5(){
        String hashAlgorithmName = "MD5";
        String credentials = "123456";
        int hashIterations = 1024;
//        ByteSource credentialsSalt = ByteSource.Util.bytes("test");
        ByteSource credentialsSalt = null;
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
