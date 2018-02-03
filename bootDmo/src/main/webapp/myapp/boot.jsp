<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/1/8
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.bootcss.com/react/15.4.2/react.min.js"></script>
    <script src="https://cdn.bootcss.com/react/15.4.2/react-dom.min.js"></script>
    <script src="https://cdn.bootcss.com/babel-standalone/6.22.1/babel.min.js"></script>
</head>
<body>
    <div id="first">
        <script type="text/babel">
            var Message = React.createClass({
                render:function(){
                    return <div>Hello World</div>
                }
            })

            ReactDom.render(
                <Message/>,
                document.getElementById("first")
            )
        </script>
    </div>
</body>
</html>
