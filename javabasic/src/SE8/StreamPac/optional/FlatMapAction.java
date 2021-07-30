package SE8.StreamPac.optional;

import java.util.Optional;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/28 0:11
 * @Modified By：
 */
public class FlatMapAction {

    public static void main(String[] args) {
        Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);
    }

    private static String getInsuranceNameByOptional(Person person) {
        // 因为使用Optional.map会二次封装一个Optional
//        Optional<Optional<Car>> car = Optional.ofNullable(person).map(Person::getCar);

        //需要使用 FlatMap
        return Optional.ofNullable(person).flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("UNKNOW");

    }
}
