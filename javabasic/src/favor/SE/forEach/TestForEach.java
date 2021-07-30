package favor.SE.forEach;

public class TestForEach {
    public static void main(String[] args) {
        String[] strList = {"1","2","3","4"};
        MyIEnumerable my = new MyIEnumerable(strList);
        InEnumerator tt = my.getEnumerator();

        while (tt.moveNext()){
            System.out.println(tt.Current());
        }



    }
}
