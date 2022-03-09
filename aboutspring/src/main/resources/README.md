<h3>Spring 事务实现方式</h3>
1. 编程式事务
    * 使用TransactionTemplate
    * 使用PlatformTransactionManager
2. 声明式事务
   * 每个Bean单独一个代理
   * 所有Bean共享一个代理
   * 拦截器 TransactionInterceptor
   * tx拦截器 
   * 全注解

<h3>嵌套事务</h3>
1. 外部无try..catch内部无try...catch： 外部有异常 ：全部回滚；内部有异常：全部回滚。
2. 外部有try..catch内部无try..catch：外部有异常：不回滚；内部有异常 有异常：全部回滚。
3. 外部无try..catch内部有try..catch：外部有异常：全部回滚；内部有异常：不回滚。
4. 外部有try..catch内部有try..catch：外部有异常：不会滚，事务执行一半，事务失效；内部有异常：事务不回滚。