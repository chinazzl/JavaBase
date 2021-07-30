Reflect 反射

1. 在运行状态下对于任意一个类都知道这个类所有的属性和方法，并且对于任意对象都能调用它的任意方法


Class:
 - 获取类的属性/方法
 - Class获取的3中方式
    1. Person p = new Person(); Class clazz = p.getClass();
    2. Class clazz = Persion.class;
    3. Class clazz = Class.forName("类的全路径“);
 
Field:
 - 表示类的成员变量可以获取和设置类之中的属性
 
 Method：
  - 表示类的方法，可以获取类中的方法信息或者执行方法
  
  获取对象的两种方法
  1. 使用Class对象的newInstance()方法来创建该Class对象对应类的实例，这种方法要求该Class的类有
      对应的空构造器
  2. 先使用Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance方法，来创建Class
      对象对应类的实例，通过这种方法可以选定构造方法创建实例
  
  
  
  
  