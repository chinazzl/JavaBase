[when][]is less than or equals to=>=
[when][]is less than=<
[when][]is greater than or equals to=>=
[when][]is greater than to=>
[when][]is equals to===
[when][] equals===
[when][]年龄=$s.getAge()
[when][]名字=$s.getName()
[when][]-{field:\w*}={field}
[when][]There is a Student with=$s:Student()
[then]公司赋予你{post}的荣誉称号=$s:setPost({post})
[then]公司赋予你=System.out.println("11");