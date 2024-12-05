package sortAlgorithmic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 当然,我很乐意帮助你理解递归算法。让我们一步步来探索这个概念。
 *
 * 递归是一种解决问题的方法,其中函数调用自身来解决更小的子问题,直到达到一个基本情况。理解递归的关键在于:
 *
 * 1. 基本情况(Base case): 最简单的情况,可以直接解决而不需要进一步递归。
 * 2. 递归情况(Recursive case): 将问题分解成更小的子问题,并调用自身。
 *
 * 让我们用一个经典的例子来说明: 计算阶乘。
 *
 * 阶乘定义: n! = n * (n-1) * (n-2) * ... * 2 * 1
 *
 * 例如: 5! = 5 * 4 * 3 * 2 * 1 = 120
 *
 * 这里是用Java实现的递归阶乘函数:
 *
 * ```java
 * public class Factorial {
 *     public static int factorial(int n) {
 *         // 基本情况: 0! = 1 和 1! = 1
 *         if (n == 0 || n == 1) {
 *             return 1;
 *         }
 *         // 递归情况: n! = n * (n-1)!
 *         else {
 *             return n * factorial(n - 1);
 *         }
 *     }
 *
 *     public static void main(String[] args) {
 *         System.out.println("5! = " + factorial(5));  // 输出: 5! = 120
 *     }
 * }
 * ```
 *
 * 让我们一步步分析这个递归过程:
 *
 * 1. 调用 `factorial(5)`
 * 2. 5 不是基本情况,所以进入递归情况: 返回 5 * factorial(4)
 * 3. 调用 `factorial(4)`, 返回 4 * factorial(3)
 * 4. 调用 `factorial(3)`, 返回 3 * factorial(2)
 * 5. 调用 `factorial(2)`, 返回 2 * factorial(1)
 * 6. 调用 `factorial(1)`, 这是基本情况,返回 1
 * 7. 现在我们可以回溯并计算结果:
 *    - factorial(2) = 2 * 1 = 2
 *    - factorial(3) = 3 * 2 = 6
 *    - factorial(4) = 4 * 6 = 24
 *    - factorial(5) = 5 * 24 = 120
 *
 * 递归的优点是可以使代码更简洁,更容易理解复杂的问题。但是,它也可能导致栈溢出(如果递归层次太深)和重复计算(如果不进行优化)。
 *
 * 理解递归的关键是:
 * 1. 确定基本情况
 * 2. 确保每次递归调用都朝着基本情况前进
 * 3. 相信递归会正确工作(不要试图在头脑中跟踪所有的递归调用)
 *
 * 你想尝试另一个递归的例子吗?或者你对这个解释有任何疑问?
 */
public class Digui {
    public static void main(String[] args) {
        System.out.println("吓得我抱起了");
        digui(2);
    }

    private static void digui(int i){
       if(i <= 0){
           System.out.print("我的小鲤鱼");
       }else{
           digui(--i);

       }
        System.out.print("的我" + i);
    }
}
