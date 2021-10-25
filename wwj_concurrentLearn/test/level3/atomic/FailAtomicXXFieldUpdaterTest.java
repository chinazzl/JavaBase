package level3.atomic;

import level3.po.TestMe;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package level3.atomic
 * @Description:
 * @Date: 2021/10/18 17:06
 */
public class FailAtomicXXFieldUpdaterTest {

    @Test(expected = RuntimeException.class)
    public void failAtomicXXField_1() {
        AtomicIntegerFieldUpdater<TestMe> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "value");
        TestMe testMe = new TestMe();
        boolean b = atomicIntegerFieldUpdater.compareAndSet(testMe, 0, 1);
        System.out.println(b);
    }
}
