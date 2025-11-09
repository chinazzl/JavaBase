package utils;


import cn.hutool.core.bean.BeanUtil;
import com.google.common.base.Function;

import java.lang.reflect.ParameterizedType;

/**********************************
 * @author zhang zhao lin
 * @date 2024年12月05日 16:20
 * @Description:
 **********************************/
public class BaseConvertFunction<F,T> implements Function<F,T> {

    private Class<T> entityClass;

    @Override
    public T apply(F input) {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass())
                    .getActualTypeArguments()[1];
        }

        return BeanUtil.copyProperties(input, entityClass);
    }
}
