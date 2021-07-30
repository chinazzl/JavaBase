package favor.CompositionBetterThanInheritance;

import java.util.Collection;
import java.util.HashSet;

/**
 * @describe复合优先于继承
 *
 * 普通继承HashSet类
 */
public class InstrumentHashSet extends HashSet{

    private int addCount = 0;

    public InstrumentHashSet(){

    }

    public InstrumentHashSet(int addCount) {
        this.addCount = addCount;
    }

    public InstrumentHashSet(Collection c, int addCount) {
        super(c);
        this.addCount = addCount;
    }

    public InstrumentHashSet(int initialCapacity, float loadFactor, int addCount) {
        super(initialCapacity, loadFactor);
        this.addCount = addCount;
    }

    public InstrumentHashSet(int initialCapacity, int addCount) {
        super(initialCapacity);
        this.addCount = addCount;
    }

    @Override
    public boolean add(Object o) {
        addCount++;
        return super.add(o);
    }

    @Override
    public boolean addAll(Collection c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
