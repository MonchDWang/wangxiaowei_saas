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
            <small>修改发票</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">修改发票</li>
        </ol>
    </section>
    <!-- 内容头部 /-->
    <form id="editForm" action="${ctx}/cargo/invoice/edit.do" method="post">
        <input type="hidden" name="invoiceId" value="${invoice.invoiceId}" >
    <!-- 正文区域 -->
    <section class="content">
        <div class="panel panel-default">
            <div class="panel-heading">修改发票</div>

                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">贸易条款</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="贸易条款" name="tradeTerms" value="${invoice.tradeTerms}"/>
                    </div>

                    <div class="col-md-2 title">创建人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建人" name="createBy" value="${invoice.createBy}"/>
                    </div>

                    <div class="col-md-2 title">创建部门</div>
                    <div class="col-md-4 data">
                        <input type="text" name="createDept" class="form-control" placeholder="创建部门" value="${invoice.createDept}"/>
                    </div>

                    <div class="col-md-2 title">创建日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="date" placeholder="创建日期"  name="createTime" class="form-control pull-right"
                                   value="<fmt:formatDate value="${invoice.createTime}" pattern="yyyy-MM-dd"/>" id="loadingDate">
                        </div>
                    </div>


                </div>

        </div>


        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">委托单列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table class="table table-bordered table-striped table-hover dataTable" id="mRecordTable">
                        <thead>
                        <tr>
                            <td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>

                            <th class="sorting">海运/空运</th>
                            <th class="sorting">货主</th>
                            <th class="sorting">提单抬头</th>
                            <th class="sorting">正本通知人</th>
                            <th class="sorting">信用证</th>
                            <th class="sorting">装运港</th>
                            <th class="sorting">转船港</th>
                            <th class="sorting">卸货港</th>
                            <th class="sorting">装期</th>
                            <th class="sorting">效期</th>
                            <th class="sorting">是否分期</th>
                            <th class="sorting">是否转船</th>
                            <th class="sorting">份数</th>
                            <th class="text-center">状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td><input type="checkbox" name="id" value="${o.shippingOrderId}"/></td>
                                <td>${o.orderType}</td>
                                <td>${o.shipper}</td>
                                <td>${o.consignee}</td>
                                <td>${o.notifyParty}</td>
                                <td>${o.lcNo}</td>
                                <td>${o.portOfLoading}</td>
                                <td>${o.portOfTrans}</td>
                                <td>${o.portOfDischarge}</td>
                                <td>${o.loadingDate}</td>
                                <td>${o.limitDate}</td>
                                <td>${o.isBatch}</td>
                                <td>${o.isTrans}</td>
                                <td>${o.copyNum}</td>
                                <td>
                                    <c:if test="${o.state==0}">草稿</c:if>
                                    <c:if test="${o.state==1}"><font color="green">已上报</font></c:if>
                                    <c:if test="${o.state==2}"><font color="green">已开票</font></c:if>
                                </td>
                                <%--<td>--%>
                                    <%--<a href="${ctx }/cargo/shipping/toView.do?id=${o.shippingOrderId}">[查看]</a>--%>
                                    <%--<a href="${ctx }/cargo/shipping/toUpdate.do?id=${o.shippingOrderId}">[编辑]</a>--%>
                                    <%--<c:if test="${o.state==2}">--%>
                                        <%--<a href="/cargo/shipping/Pdf.do?id=${o.shippingOrderId}">[下载]</a>--%>
                                    <%--</c:if>--%>
                                <%--</td>--%>
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