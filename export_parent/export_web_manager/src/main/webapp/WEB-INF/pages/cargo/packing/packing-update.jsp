<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp" %>
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

<script>
    <%--移除报运单--%>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要移除此条报运单吗？")) {
                location.href="${ctx}/cargo/packing/deleteExport.do?id="+id;
                <%--location.href="${ctx}/cargo/packing/deleteExport.do?pid="+${coPackingList.coPackingListId}+"&id="+id;--%>
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    //添加报运单
    function submit() {
        var id = getCheckId()
        if(id) {
            location.href="${ctx}/cargo/contract/submit.do?id="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function view() {
        var id = getCheckId()
        if(id) {
            location.href="${ctx}/cargo/contract/toView.do?id="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }


</script>

<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            货运管理
            <small>新增出口报运单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">新增出口报运单</li>
        </ol>
    </section>
    <!-- 正文区域 -->
    <section class="content">
        <form action="/cargo/packing/edit.do" method="post">
            <div class="panel panel-default">
                <div class="panel-heading">修改装箱单【${coPackingList.packingListId}】</div>
                <input type="text" name="exportIds" value="${coPackingList.exportIds}">
                <input type="text" name="exportNos" value="${coPackingList.exportNos}">

                <div class="row data-type" style="margin: 0px">

                    <div class="col-md-2 title">卖方</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="卖方" name="seller"
                               value="${coPackingList.seller}"/>
                    </div>

                    <div class="col-md-2 title">买方</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="买方" name="buyer"
                               value="${coPackingList.buyer}"/>
                    </div>

                    <div class="col-md-2 title">发票号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="发票号" name="invoiceNo"
                               value="${coPackingList.invoiceNo}"/>
                    </div>

                    <div class="col-md-2 title">发票日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="发票日期" name="invoiceDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${coPackingList.invoiceDate}" pattern="yyyy-MM-dd"/>"
                                   id="invoiceDate">
                        </div>
                    </div>

                    <div class="col-md-2 title">唛头</div>
                    <div class="col-md-4 data">
                        <input type="text" name="marks" class="form-control" placeholder="唛头"
                               value="${coPackingList.marks}"/>
                    </div>

                    <div class="col-md-2 title">描述</div>
                    <div class="col-md-4 data">
                        <input type="text" name="descriptions" class="form-control" placeholder="描述"
                               value="${coPackingList.descriptions}"/>
                    </div>

                    <%--<div class="col-md-2 title">创建人</div>--%>
                    <%--<div class="col-md-4 data">--%>
                    <%--<input type="text" name="createBy" class="form-control" placeholder="创建人"--%>
                    <%--value="${coPackingList.createBy}">--%>
                    <%--</div>--%>

                    <%--<div class="col-md-2 title">创建部门</div>--%>
                    <%--<div class="col-md-4 data">--%>
                    <%--<input type="text" name="createDept" class="form-control" placeholder="创建部门"--%>
                    <%--value="${coPackingList.createDept}">--%>
                    <%--</div>--%>

                    <%--<div class="col-md-2 title">创建日期</div>--%>
                    <%--<div class="col-md-4 data">--%>
                    <%--<div class="input-group date">--%>
                    <%--<div class="input-group-addon">--%>
                    <%--<i class="fa fa-calendar"></i>--%>
                    <%--</div>--%>
                    <%--<input type="text" placeholder="创建日期"  name="createTime" class="form-control pull-right"--%>
                    <%--value="<fmt:formatDate value="${coPackingList.createTime}" pattern="yyyy-MM-dd"/>" id="createTime">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                </div>

            </div>
            <!--工具栏-->
            <div class="box-tools text-center">
                <button type="submit" class="btn bg-maroon">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>


            <!-- .box-body -->
            <div class="box box-primary">

                <div class="box-header with-border">
                    <h3 class="box-title">装箱单报运单列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <%--&lt;%&ndash;工具栏&ndash;%&gt;--%>
                        <%--<div class="pull-left">--%>
                            <%--<div class="form-group form-inline">--%>
                                <%--<div class="btn-group">--%>
                                    <%--<button type="button" class="btn btn-default" title="添加" onclick='location.href="${ctx}/cargo/contract/toAdd.do"'><i class="fa fa-file-o"></i> 添加</button>--%>
                                    <%--<button type="button" class="btn btn-default" title="查看" onclick='view()'><i class="fa  fa-eye-slash"></i> 查看</button>--%>
                                    <%--<button type="button" class="btn btn-default" id="btn1" title="移除" onclick="deleteById()"><i class="fa fa-trash-o"></i> 移除</button>--%>
                                    <%--<button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>--%>
                                    <%--<button type="button" class="btn btn-default" title="提交" onclick="submit()"><i class="fa fa-retweet"></i> 提交</button>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--报运单信息--%>
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                                <td width="33">序号</td>
                                <th class="sorting">报运号</th>
                                <th class="sorting">货物/附件</th>
                                <th class="sorting">信用证号</th>
                                <th class="sorting">收货地址</th>
                                <th class="sorting">装运港</th>
                                <th class="sorting">目的港</th>
                                <th class="sorting">运输方式</th>
                                <th class="sorting">价格条件</th>
                                <th class="sorting">状态</th>
                                <th class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--<form id="exportForm" action="/cargo/packing/toPacking.do" method="post">--%>
                            <c:forEach items="${exports}" var="o" varStatus="status">
                                <tr class="odd" onmouseover="this.className='highlight'"
                                    onmouseout="this.className='odd'">
                                    <td><input type="checkbox" name="id" value="${o.id}"/></td>
                                    <%--<input type="text" name="exportIds" value="${id}">--%>
                                    <%--<input type="text" name="exportNos" value="${nos}">--%>
                                    <%--<input type="hidden" name="exports[${status.index}].id" value="${o.id}"/>--%>
                                    <td>${status.index+1}</td>
                                    <td>${o.id}</td>
                                    <%--<td>--%>
                                        <%--<input style="width: 90px" name="exports[${status.index}].id" value="${o.id}">--%>
                                    <%--</td>--%>
                                    <td align="center">
                                            ${o.proNum}/${o.extNum}
                                    </td>
                                    <%--<td>--%>
                                        <%--<input style="width: 90px" name="exports[${status.index}].bbb" value="${o.proNum}/${o.extNum}">--%>
                                    <%--</td>--%>
                                    <td>${o.lcno}</td>
                                    <%--<td>--%>
                                        <%--<input style="width: 90px" name="exports[${status.index}].lcno" value="${o.lcno}">--%>
                                    <%--</td>--%>
                                    <td>${o.consignee}</td>
                                    <%--<td>--%>
                                        <%--<input style="width: 90px" name="exports[${status.index}].consignee" value="${o.consignee}">--%>
                                    <%--</td>--%>
                                    <td>${o.shipmentPort}</td>
                                    <%--<td>--%>
                                        <%--<input style="width: 90px" name="exports[${status.index}].shipmentPort" value="${o.shipmentPort}">--%>
                                    <%--</td>--%>
                                    <td>${o.destinationPort}</td>
                                    <%--<td>--%>
                                        <%--<input style="width: 90px" name="exports[${status.index}].destinationPort" value="${o.destinationPort}">--%>
                                    <%--</td>--%>
                                    <td>${o.transportMode}</td>
                                    <%--<td>--%>
                                        <%--<input style="width: 90px" name="exports[${status.index}].transportMode" value="${o.transportMode}">--%>
                                    <%--</td>--%>
                                    <td>${o.priceCondition}</td>
                                    <%--<td>--%>
                                        <%--<input style="width: 90px" name="exports[${status.index}].priceCondition" value="${o.priceCondition}">--%>
                                    <%--</td>--%>
                                    <td>
                                        <c:if test="${o.state==0}">草稿</c:if>
                                        <c:if test="${o.state==1}"><font color="green">已上报</font></c:if>
                                        <c:if test="${o.state==2}"><font color="red">已报运</font></c:if>
                                    </td>
                                    <td>
                                        <a href="${ctx }/cargo/export/toView.do?id=${o.id}">[查看]</a>
                                        <a href="${ctx }/cargo/export/toUpdate.do?id=${o.id}">[编辑]</a>
                                        <%--<c:if test="${o.state==2}">--%>
                                            <a href="${ctx }/cargo/packing/deleteExport.do?pid=${coPackingList.packingListId}&id=${o.id}">[移除]</a>
                                        <%--</c:if>--%>
                                    </td>
                                </tr>
                            </c:forEach>
                            <%--</form>--%>
                            </tbody>
                        </table>
                    </div>
                    <!-- 数据表格 /-->
                </div>
                <!-- /.box-body -->

            </div>

        </form>
        <!--工具栏/-->
    </section>
</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#invoiceDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });

    // $('#createTime').datepicker({
    //     autoclose: true,
    //     format: 'yyyy-mm-dd'
    // });
</script>
</html>