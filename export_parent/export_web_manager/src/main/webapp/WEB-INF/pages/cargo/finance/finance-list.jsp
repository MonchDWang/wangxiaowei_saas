<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen wfinanceIdth -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">

    <!-- 页面meta /-->
    <script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<script>
    function deleteByfinanceId() {
        var financeId = getCheckfinanceId()
        if(financeId) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/cargo/finance/delete.do?financeId="+financeId;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function submit() {
        var financeId = getCheckfinanceId()
        if(financeId) {
            location.href="${ctx}/cargo/finance/submit.do?financeId="+financeId;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function cancel() {
        var financeId = getCheckfinanceId()
        if(financeId) {
            location.href="${ctx}/cargo/finance/cancel.do?financeId="+financeId;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function CofinanceE() {
        var financeId = getCheckfinanceId()
        if(financeId) {
            location.href="${ctx}/cargo/finance/add.do?financeId="+financeId;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div financeId="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            财务报运单管理
            <small>财务报运单列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">财务报运单列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">

                    <!--工具栏-->
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/cargo/finance/add.do?companyId="'+companyId><i class="fa fa-file-o"></i> 新建</button>
                                <button type="button" class="btn btn-default" title="删除" onclick='deleteByfinanceId()'><i class="fa fa-trash-o"></i> 删除</button>
                                <button type="button" class="btn btn-default" title="提交" onclick='submit()'><i class="fa fa-file-o"></i> 提交</button>
                                <button type="button" class="btn btn-default" title="取消" onclick='cancel()'><i class="fa fa-file-o"></i> 取消</button>
                            </div>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <div class="has-feedback">
                            <input type="text" class="form-control input-sm" placeholder="搜索">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </div>
                    <!--工具栏/-->

                    <!--数据列表-->
                    <table financeId="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <td><input type="checkbox" name="selfinanceId" onclick="checkAll('financeId',this)"></td>
                            <th class="sorting">制单日期</th>
                            <th class="sorting">创建人</th>
                            <th class="sorting">状态</th>
                            <th class="sorting">制单人</th>
                            <th class="sorting">创建部门</th>
                            <th class="sorting">创建日期</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td><input type="checkbox" name="financeId" value="${o.financeId}"/></td>
                                <td>${o.financefinanceId}</td>

                                <td>${o.tradeTerms}</td>
                                <td>${o.createBy}</td>
                                <td>${o.state}</td>
                                <td>${o.inputBy}</td>
                                <td>${o.createDept}</td>
                                <td>${o.createTime}</td>

                                <td>
                                    <c:if test="${o.state==0}">草稿</c:if>
                                    <c:if test="${o.state==1}"><font color="green">已提交</font></c:if>
                                </td>
                                <td>
                                    <a href="${ctx }/cargo/finance/toView.do?financeId=${o.financeId}">[查看]</a>
                                    <a href="${ctx }/cargo/finance/toUpdate.do?financeId=${o.financeId}">[编辑]</a>
                                    <c:if test="${o.state==2}">
                                        <a href="/cargo/finance/financePdf.do?financeId=${o.financeId}">[下载]</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="cargo/finance/list.do" name="pageUrl"/>
                </jsp:include>
            </div>
            <!-- /.box-footer-->


        </div>

    </section>
</div>
</body>

</html>