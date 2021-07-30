package favor.insetence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂模式
 */
public class Services {
    //Constructors
    public Services() {
    }

    //Maps service names to services,ConcurrentHashMap默认初始化长度16
    private static final Map<String,Provider> providers = new ConcurrentHashMap<String,Provider>();

    public static final String DEFAULT_PROVIDER_NAME = "<def>";

    //Provider registration API
    public static void registerDefaultProvider(Provider p){
        registerProvider(DEFAULT_PROVIDER_NAME,p);
    }

    public static void registerProvider(String name, Provider p) {
        providers.put(name,p);
    }

    //Service access API
    public static Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    private static Service newInstance(String name) {
        Provider p = providers.get(name);
        if(p == null){
            throw new IllegalArgumentException("没有服务提供者" + name);
        }
        return p.newService();
    }
}
