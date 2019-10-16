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
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            委托管理
            <small>委托单下装箱详情</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">财务报运单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <%--
            enctype="multipart/form-data"
        --%>

            <script>
                $(function () {
                    $('input:radio[name="id"]').click(function () {
                        var value = $('input[name="id"]:checked').val();
                        $("#shippingOrderId").val(value);
                    });
                })

            </script>
        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">新增财务报运单</div>
            <form id="editForm" action="${ctx}/cargo/finance/edit.do" method="post" >
                <input type="text" name="financeId" value="${Cofinance.financeId}">
                <div class="row data-type" style="margin: 0px">



                    <div class="col-md-2 title">制单人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="制单人" name="inputBy" value="${Cofinance.inputBy}">
                    </div>


                    <div class="col-md-2 title">创建人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建人" name="createBy" value="${Cofinance.createBy}">
                    </div>

                    <div class="col-md-2 title">创建部门</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建部门" name="createBy" value="${Cofinance.createDept}">
                    </div>




                    <div class="col-md-2 title">制单日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="制单日期"  name="inputDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${Cofinance.inputDate}" pattern="yyyy-MM-dd"/>" id="inputDate">
                        </div>
                    </div>

                    <div class="col-md-2 title">创建日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="创建日期"  name="createTime" class="form-control pull-right"
                                   value="<fmt:formatDate value="${Cofinance.createTime}" pattern="yyyy-MM-dd"/>" id="createTime">
                        </div>
                    </div>

                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">发票列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                            <td class="tableHeader">贸易条款</td>
                            <td class="tableHeader">状态</td>
                            <td class="tableHeader">发票号</td>
                            <td class="tableHeader">创建人</td>
                            <td class="tableHeader">创建部门</td>
                            <td class="tableHeader">创建日期</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody" >
                        ${links }
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td><input type="checkbox" name="id" value="${o.id}"/></td>
                                <td>${status.index+1}</td>
                                <td>${o.tradeTerms}</td>
                                <td>${o.state}</td>
                                <td>${o.scNo}</td>
                                <td>${o.createBy}</td>
                                <td>${o.createDept}</td>
                                <td>${o.createTime}</td>

                                <td>
                                    <a href="${ctx}/cargo/Cofinance/toUpdate.do?invoiceId=${o.invoiceId}">[修改]</a>
                                    <a href="${ctx}/cargo/Cofinance/delete.invoiceId?id=${o.invoiceId}&contractId=${o.contractId}">[删除]</a>
                                </td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                    <!--数据列表/-->
                    <!--工具栏/-->
                </div>
                <!-- 数据表格 /-->
            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="/cargo/Cofinance/list.do?financeId=${financeId}" name="pageUrl"/>
                </jsp:include>
            </div>
            <!-- /.box-footer-->
        </div>

    </section>

</div>
<!-- 内容区域 /-->
</body>

</html>