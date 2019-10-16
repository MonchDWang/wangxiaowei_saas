<%--
  Created by IntelliJ IDEA.
  User: muzimoo
  Date: 2019-7-24
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <div class="pull-left">
            <div class="form-group form-inline">
                总共${page.pages}页，共${page.total}条数据。 每页
                <select class="form-control">
                    <option ${page.pageSize==2?"selected":""}>2</option>
                    <option ${page.pageSize==4?"selected":""}>4</option>
                    <option ${page.pageSize==8?"selected":""}>8</option>
                </select> 条
            </div>
        </div>

        <div class="box-tools pull-right">
            <ul class="pagination">
                <li>
                    <a href="javascript:toPage('1')" aria-label="Previous">首页</a>
                </li>
                <li>
                    <a href="javascript:toPage(${page.pageNum-1})">上一页</a>
                </li>

                <%--页码--%>
                <c:forEach begin="${page.navigateFirstPage}" end="${page.navigateLastPage}" step="1" var="pg">
                    <li><a href="javascript:toPage(${pg})">${pg}</a></li>
                </c:forEach>


                <li>
                    <a href="javascript:toPage(${page.pageNum+1})">下一页</a>
                </li>
                <li>
                    <a href="javascript:toPage(${page.pages})" aria-label="Next">尾页</a>
                </li>
            </ul>
        </div>

        <form id="formId" action="${param.pageUrl}" method="post">
            <input type="text" name="pageNum" id="pageNum">
        </form>
        <script>
            function toPage(pageNum){
                // 要给表单中的表单项赋值
                $("#pageNum").val(pageNum);   //document.getElementById("pageNum").value=pageNum;
                // 将表单提交
                $("#formId").submit();
            }
        </script>
</body>
</html>
