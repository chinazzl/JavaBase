Channel:
> 通道 代表数据到硬件设备、文件、网络套接字的连接。
> 通道可以处于打开或者关闭两种状态

JDK 1.8 Channel 接口具有11个子接口
1. AsynchronousChannel：使通道支持异步I/O操作
2. AsynchronousByteChannel：使通道支持异步I/O操作，操作单位字节
3. ReadableByteChannel：使通道 允许对字节进行 读操作
4. ScatteringByteChannel：从通道中读取字节到多个缓冲区中
5. WritableByteChannel：使通道允许对字节进行操作
6. GatheringByteChannel：可以将多个缓冲区中的数据写入到通道中
7. ByteChannel：ReadableByteChannel 和 WritableByteChannel 的 统一规范
8. SeekableByteChannel：在字节通道中维护position（位置），以及允许position发生改变
9. NetworkChannel：使通道与socket进行关联，使通道中的数据能在Socket进行传输。
10. MulticastChannel：主要作用是使通道支持Internet Protocol（IP）多播。
11. InterruptibleChannel：作用是使通道能以异步的方式进行关闭与中断。实现了Asynchronously 和 closeable 特性的同时还实现了
    interruptible特性，如果一个线程在一个能被中断的通道上出现了阻塞状态，当其他线程调用这个阻塞线程的interrupt()方法后，通道将被
    关闭，这个阻塞的下称将接收到ClosedByInterruptException异常，这个阻塞线程的状态一直 使中断状态。


        