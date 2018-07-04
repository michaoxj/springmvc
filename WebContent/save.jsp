<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
  
</head>
<body>
   <form action="${pageContext.request.contextPath}/product/save.mvc" method="post">
      <!--  ctrl + atl + 下方向键 -->
           商品名:<input type="text" name="name" /><br/>
            价格:<input type="text" name="price" /><br/>
            备注:<input type="text" name="remark" /><br/>
       <input type="submit" value="提交" />
       <input type="hidden" name="type" value="insert" />
   </form>
</body>
</html>





