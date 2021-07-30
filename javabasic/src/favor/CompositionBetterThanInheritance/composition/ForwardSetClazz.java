package favor.CompositionBetterThanInheritance.composition;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 转发类，包含所有的转发方法
 */
public class ForwardSetClazz implements Set{

    private final Set set;

    public ForwardSetClazz(Set set) {
        this.set = set;
    }


    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public boolean add(Object o) {
        return set.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return set.addAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        return set.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection c) {
        return set.retainAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return set.containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return set.toArray(a);
    }
}
