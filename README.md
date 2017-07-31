# JavaBase
### javabasic

1. 实现线程的两种方式
 * 继承Thread类
 * 实现Runnable接口， 因为Thread类也实现Runnable接口
2. 实现Runnable接口的好处
      1）：适合多个相同的程序代码的线程去处理同一个资源
      2）：可以避免java中的单继承的限制
      3）：增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
      4）：线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类
           
