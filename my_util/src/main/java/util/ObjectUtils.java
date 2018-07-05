package util;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @Author:          郭航飞
 * @Description:： 判断一个对象为空
 * @CreateDate:   2018/4/25 15:10
 * @param            * @param null
 * @return
**/
public class ObjectUtils {
    public static boolean isNull(Object obj) {
                 return obj == null;
             }

             public static boolean isNotNull(Object obj) {
                 return !isNull(obj);
            }

             public static boolean isEmpty(Object obj) {
                 if (obj == null) return true;
                 else if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;
                 else if (obj instanceof Collection) return ((Collection) obj).isEmpty();
                 else if (obj instanceof Map) return ((Map) obj).isEmpty();
                 else if (obj.getClass().isArray()) return Array.getLength(obj) == 0;

                return false;
             }

             public static boolean isNotEmpty(Object obj) {
                 return !isEmpty(obj);
            }
}
