package favor.testPackage;

public abstract class Pack {
    private int i = 0;
    String run(int i){
        int a = i++;
        System.out.println("abstract Pack ===>" + a);
        return String.valueOf(a);
    }

     protected abstract void tt();
}
