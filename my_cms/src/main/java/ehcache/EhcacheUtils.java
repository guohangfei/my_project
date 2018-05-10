package ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 *缓存操作类
 *
 * @Author:          郭航飞
 * @CreateDate:   2018/5/9 17:37
**/
public class EhcacheUtils {
    /**
     *EHCache 的配置文件地址
     *
     * @Author:          郭航飞
     * @CreateDate:   2018/5/9 17:38
    **/
    private static final String path = "/ehcache.xml";

    private CacheManager manager;

    private static EhcacheUtils ehCache;

    private EhcacheUtils(String path) {
        manager = CacheManager.create(getClass().getResource(path));
    }

    public static EhcacheUtils getInstance() {
        if (ehCache == null) {
            ehCache = new EhcacheUtils(path);
        }
        return ehCache;
    }

    /**
     * 缓存一个对象
     *
     * @param cacheName
     *      缓存的名字
     * @param key
     *      缓存的KEY
     * @param value
     *      缓存的值
     */
    public void put(String cacheName, String key, Object value) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    /**
     * 获取一个缓存的对象，没有返回NULL
     *
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public Cache get(String cacheName) {
        return manager.getCache(cacheName);
    }

    public void remove(String cacheName, String key) {
        Cache cache = manager.getCache(cacheName);
        cache.remove(key);
    }
}
