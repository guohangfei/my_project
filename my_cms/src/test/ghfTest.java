import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

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

    @Test
    public void PersonsList(){
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};

        // 1.1 使用匿名内部类根据 name 排序 players
        Arrays.sort(players, (s1, s2) -> (s1.compareTo(s2)));
        Arrays.asList(players).forEach((player)-> System.out.println("player = " + player));
        System.out.println();

    }
}
