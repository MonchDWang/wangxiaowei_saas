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
            <li class="active">委托单添加及装箱详情</li>
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
                    $("#packingListId").val(value);
                });
            })

        </script>
        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">
                新增委托单

                    <span class="bg-red">${errorMessage}</span>

            </div>

            <form id="editForm" action="${ctx}/cargo/shipping/edit.do" method="post" >
                <input type="hidden" name="shippingOrderId" value="${coshippingorder.shippingOrderId}">
                <input type="hidden" id="packingListId" name="packingListId" >
                <div class="row data-type" style="margin: 0px">

                    <div class="col-md-2 title">货主</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="货主" name="shipper" value="${coshippingorder.shipper}">
                    </div>

                    <div class="col-md-2 title">提单抬头</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="提单抬头" name="consignee" value="${coshippingorder.consignee}">
                    </div>

                    <div class="col-md-2 title">正本通知人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="正本通知人" name="notifyParty" value="${coshippingorder.notifyParty}">
                    </div>

                    <div class="col-md-2 title">运输方式</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" <c:if test="${coshippingorder.orderType==1}">checked</c:if> name="orderType" value="1">海运</label></div>
                            <div class="radio"><label><input type="radio" <c:if test="${coshippingorder.orderType==2}">checked</c:if> name="orderType" value="2">空运</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">信用证</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="信用证" name="lcNo" value="${coshippingorder.lcNo}">
                    </div>

                    <div class="col-md-2 title">装运港</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="装运港" name="portOfLoading" value="${coshippingorder.portOfLoading}">
                    </div>

                    <div class="col-md-2 title">转船港</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="转船港" name="portOfTrans" value="${coshippingorder.portOfTrans}">
                    </div>
                    <div class="col-md-2 title">卸货港</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="卸货港" name="portOfDischarge" value="${coshippingorder.portOfDischarge}">
                    </div>
                    <div class="col-md-2 title">复核人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="复核人" name="checkBy" value="${coshippingorder.checkBy}">
                    </div>
                    <%--<div class="col-md-2 title">创建人</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="创建人" name="createBy" value="${coshippingorder.createBy}">
                    </div>--%>
                    <div class="col-md-2 title">是否分批</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" <c:if test="${coshippingorder.isBatch==1}">checked</c:if> name="isBatch" value="1">是</label></div>
                            <div class="radio"><label><input type="radio" <c:if test="${coshippingorder.isBatch==0}">checked</c:if> name="isBatch" value="0">否</label></div>
                        </div>
                    </div>
                    <div class="col-md-2 title">是否转船</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" <c:if test="${coshippingorder.isTrans==1}">checked</c:if> name="isTrans" value="1">是</label></div>
                            <div class="radio"><label><input type="radio" <c:if test="${coshippingorder.isTrans==0}">checked</c:if> name="isTrans" value="0">否</label></div>
                        </div>
                    </div>
                    <div class="col-md-2 title">份数</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="份数" name="copyNum" value="${coshippingorder.copyNum}">
                    </div>


                    <div class="col-md-2 title">装期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="装期"  name="loadingDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${coshippingorder.loadingDate}" pattern="yyyy-MM-dd"/>" id="loadingDate">
                        </div>
                    </div>

                    <div class="col-md-2 title">效期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="效期"  name="limitDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${coshippingorder.limitDate}" pattern="yyyy-MM-dd"/>" id="limitDate">
                        </div>
                    </div>



                    <div class="col-md-2 title rowHeight2x">运输要求</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="运输要求" name="specialCondition">${coshippingorder.specialCondition}</textarea>
                    </div>



                    <div class="col-md-2 title rowHeight2x">运费说明</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="运费说明" name="freight">${coshippingorder.freight}</textarea>
                    </div>

                    <div class="col-md-2 title rowHeight2x">扼要说明</div>
                    <div class="col-md-4 data  rowHeight2x">
                        <textarea class="form-control" rows="3" placeholder="扼要说明" name="remark">${coshippingorder.remark}</textarea>
                    </div>




                    <%--<input type="hidden" name="isBatch" value='document.getElementByName("radio").value'>--%>
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
                <h3 class="box-title">装箱列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <%----%>
                            <td class="tableHeader"></td>
                            <td class="tableHeader">序号</td>
                            <td class="tableHeader">买方</td>
                            <td class="tableHeader">卖方</td>
                            <td class="tableHeader">发票号</td>
                            <td class="tableHeader">发票日期</td>
                            <td class="tableHeader">状态</td>
                            <td class="tableHeader">唛头</td>
                            <td class="tableHeader">描述</td>
                            <%--<td class="tableHeader">创建人</td>
                            <td class="tableHeader">创建部门</td>
                            <td class="tableHeader">创建日期</td>--%>
                        </tr>
                        </thead>
                        <tbody class="tableBody" >
                        ${links}
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td><input type="radio" name="id" value="${o.packingListId}"/></td>
                                <td>${status.index+1}</td>
                                <td>${o.buyer}</td>
                                <td>${o.seller}</td>
                                <td>${o.invoiceNo}</td>
                                <td><fmt:formatDate value="${o.invoiceDate}" pattern="yyyy-MM-dd"/></td>
                                <td>
                                    <c:if test="${o.state==0}">草稿</c:if>
                                    <c:if test="${o.state==1}"><font color="green">已提交</font></c:if>
                                    <c:if test="${o.state==2}"><font color="green">已委托</font></c:if>
                                </td>
                                <td>${o.marks}</td>
                                <td>${o.descriptions}</td>
                                <%--<td>${o.createBy}</td>
                                <td>${o.createDept}</td>
                                <td><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd"/></td>--%>

                                <%--<td>--%>
                                    <%--<a href="${ctx}/cargo/coshippingorder/toUpdate.do?id=${o.packingListId}">[修改]</a>--%>
                                    <%--<a href="${ctx}/cargo/coshippingorder/delete.do?id=${o.id}&contractId=${o.contractId}">[删除]</a>--%>
                                    <%--<a href="${ctx}/cargo/extCproduct/list.do?contractId=${o.contractId}&coshippingorderId=${o.id}">[附件]</a>--%>
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

            <!-- .box-footer-->
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="/cargo/coshippingorder/list.do?shippingOrderId=${shippingOrderId}" name="pageUrl"/>
                </jsp:include>
            </div>
            <!-- /.box-footer-->
        </div>

    </section>

</div>
<!-- 内容区域 /-->
</body>

<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#loadingDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#limitDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>

</html>