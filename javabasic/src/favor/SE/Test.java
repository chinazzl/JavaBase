package favor.SE;


public class Test {
    private  static String a = "hello";

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = new NutritionFacts.Builder(240,80)
                .calories(1).carbohydrate(2).fat(3).sodium(4).build();
        System.out.println(nutritionFacts.getCaloris());

        System.out.println("==========");
        final String sq = "hello";
        String sq1 = "hello";
        String s1 = "hello1";
        String s2 = sq + 1;
        String s3 = sq1 + 1;
        String s4 = a + 1;
        System.out.println(s1 == s2); //true,final修饰的变量初始化后不能指向另一个对象
        System.out.println(s3 == s1);   //false
        System.out.println(s4 == s1 );  //false
    }


}
