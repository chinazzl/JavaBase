package langer;

public class MutiArray {

    public static void main(String[] args) {

        mutiArrayTest();
    }

    /**
     * 多维数组
     */
    private static void mutiArrayTest() {
        String[][] strings = new String[3][4];
        String[] str1 = new String[3];
        //向str1中添加随机数
        for (int i=0;i<str1.length;i++){
            str1[i] = String.valueOf(Math.random());
        }
        //向二维数组中添加数组
        strings[0] = str1;
        //遍历二维数组
        for (int k = 0; k <strings.length ; k++) {
            System.out.println(strings[k]);
            String[] string0 = strings[k];
            for (int j = 0; j < string0.length; j++) {
                System.out.println(string0[j]);
            }
        }


    }
}
