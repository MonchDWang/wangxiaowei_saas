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
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>新增财务报运单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">新增财务报运单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->
    <form id="editForm" action="${ctx}/cargo/finance/edit.do" method="post">
        <input type="hidden" name="contractIds" value="${finance.financeId}" >
        <input type="hidden" name="id" value="${finance.id}" >
        <input type="hidden" name="contractNo" value="${finance.customerContract}">
    <!-- 正文区域 -->
    <section class="content">
        <div class="panel panel-default">
            <div class="panel-heading">新增财务报运单</div>

                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">制单日期</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="制单日期" name="lcno" value="${finance.inputDate}"/>
                    </div>

                    <div class="col-md-2 title">制单人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="制单人" name="consignee" value="${finance.inputBy}"/>
                    </div>

                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="状态" name="marks" value="${finance.state}"/>
                    </div>

                    <div class="col-md-2 title">创建人</div>
                    <div class="col-md-4 data">
                        <input type="text" name="shipmentPort" class="form-control" placeholder="创建人"value="${finance.createBy}"/>
                    </div>

                    <div class="col-md-2 title">创建部门</div>
                    <div class="col-md-4 data">
                        <input type="text" name="destinationPort" class="form-control" placeholder="创建部门" value="${finance.createDept}"/>
                    </div>

                    <div class="col-md-2 title">创建日期</div>
                    <div class="col-md-4 data">
                        <input type="text" name="transportMode" class="form-control" placeholder="创建日期" value="${finance.createTime}">
                    </div>

                </div>

        </div>


        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">发票列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table class="table table-bordered table-striped table-hover dataTable" id="mRecordTable">
                        <thead>
                        <tr>
                            <td><input type="checkbox" name="selinvoiceId" onclick="checkAll('invoiceId',this)"></td>
                            <th class="sorting">贸易条款</th>
                            <th class="sorting">创建人</th>
                            <th class="sorting">创建部门</th>
                            <th class="sorting">创建日期</th>
                            <th class="sorting">状态</th>
                            <th class="sorting">操作</th>


                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td><input type="checkbox" name="invoiceId" value="${o.invoiceId}"/></td>

                                <td>${o.tradeTerms}</td>
                                <td>${o.createBy}</td>
                                <td>${o.createDept}</td>
                                <td>${o.createTime}</td>

                                <td>
                                    <c:if test="${o.state==0}">草稿</c:if>
                                    <c:if test="${o.state==1}"><font color="green">已提交</font></c:if>
                                </td>
                                <td>
                                    <a href="${ctx }/cargo/invoice/toView.do?invoiceId=${o.invoiceId}">[查看]</a>
                                    <%--<a href="${ctx }/cargo/invoice/toUpdate.do?invoiceId=${o.invoiceId}">[编辑]</a>--%>
                                    <c:if test="${o.state==2}">
                                        <a href="/cargo/invoice/invoicePdf.do?invoiceId=${o.invoiceId}">[下载]</a>
                                    </c:if>
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

        </div>

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="submit"  class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->
    </section>
    </form>
</div>
<!-- 内容区域 /-->
</body>

</html>