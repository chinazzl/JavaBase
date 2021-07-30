package favor.CompositionBetterThanInheritance.composition;

import java.util.Collection;
import java.util.Set;

/**
 * 本身类，继承于转发类
 */
public class InstrumentSetClazz extends ForwardSetClazz{
    private int addCount = 0;

    public InstrumentSetClazz(Set s) {
        super(s);
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
        System.out.println("addCoumt ==>" + addCount);
        return addCount;
    }
}
