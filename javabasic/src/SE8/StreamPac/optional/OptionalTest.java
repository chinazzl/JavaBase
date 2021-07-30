package SE8.StreamPac.optional;

import java.util.Optional;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/27 21:34
 * @Modified By：
 */
public class OptionalTest {

    public static void main(String[] args) {
        //1. 创建 Optional 使用 empty创建一个空的Optional
        Optional<Insurance> emptyInsurance = Optional.<Insurance>empty();

        // 2. 使用Optional的of方法
        Optional<Insurance> optionalInsuranceWithOfMethod = Optional.of(new Insurance());
//        optionalInsuranceWithOfMethod.get();
//
//        //3. 使用 ofNullable 方法
//        Optional<Insurance> insuranceByNullable = Optional.ofNullable(null);
//            //orElseGet 传递一个Supplier
//        insuranceByNullable.orElseGet(Insurance::new);
//            // orElse 传递一个 Object
//        insuranceByNullable.orElse(new Insurance());
//            // orElseThrow 传递一个异常 Supplier
//        insuranceByNullable.orElseThrow(RuntimeException::new);

//        Insurance insurance = optionalInsuranceWithOfMethod.filter(i -> i.getName() == null).get();

        Optional<String> insuranceMap = optionalInsuranceWithOfMethod.map(i -> i.getName());
        String no_values = insuranceMap.orElse("No values");
        System.out.println(no_values);

        insuranceMap.ifPresent(System.out::println);

        System.out.println(getInsuranceByOption(new Insurance()));

    }

    /**
     * 根据传入的对象判断是否有值 如果没有则 返回默认值
     *
     * @param insurance
     * @return
     */
    private static String getInsuranceByOption(Insurance insurance) {

        return Optional.ofNullable(insurance).map(i -> i.getName()).orElse("unknow");
    }
}
