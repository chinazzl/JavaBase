package favor.SE.forEach;

public class MyIEnumerator implements InEnumerator {

    private String[] strList;
    private int position;

    public MyIEnumerator(String[] strList) {
        this.strList = strList;
        position = -1;
    }

    @Override
    public Object Current() {
        return strList[position];
    }

    @Override
    public boolean moveNext() {
        position++;
        if(position < strList.length){
            return true;
        }
        return false;
    }

    @Override
    public void Reset() {
        position = -1;
    }
}
