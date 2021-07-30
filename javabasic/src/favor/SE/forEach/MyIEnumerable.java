package favor.SE.forEach;

public class MyIEnumerable implements InEnumerable {

    private String[] strList;

    public MyIEnumerable(String[] strList) {
        this.strList = strList;
    }

    @Override
    public InEnumerator getEnumerator() {
        return new MyIEnumerator(strList);
    }
}
