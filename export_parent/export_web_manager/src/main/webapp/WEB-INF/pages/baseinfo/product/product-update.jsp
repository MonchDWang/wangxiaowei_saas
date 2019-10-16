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
            产品管理
            <small>产品管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="/baseinfo/product/list.do">产品列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">产品信息</div>
            <form id="editForm" action="/baseinfo/product/edit.do" method="post">
                <input type="hidden" id="id" name="id" value="${product.id}">
                <input type="hidden" id="factoryName" name="factoryName" value="${user.factoryName}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">产品编号</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="产品编号" name="productNo" value="${product.productNo}">
                    </div>

                    <div class="col-md-2 title">厂家简称</div>
                    <div class="col-md-4 data">
                        <select class="form-control" onchange="document.getElementById('factoryName').value=this.options[this.selectedIndex].text" name="factoryId">
                            <option value="">请选择</option>
                            <c:forEach items="${factoryList}" var="item">
                                <option ${product.factoryId == item.id ?'selected':''} value="${item.id}">${item.factoryName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-2 title">市场价</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="市场价" name="price" value="${product.price}">
                    </div>

                    <div class="col-md-2 title">尺寸长</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="尺寸长" name="sizeLenght" value="${product.sizeLenght}">
                    </div>

                    <div class="col-md-2 title">尺寸宽</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="尺寸宽" name="sizeWidth" value="${product.sizeWidth}">
                    </div>
                    <div class="col-md-2 title">数量</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="数量" name="qty" value="${product.qty}">
                    </div>

                    <div class="col-md-2 title">描述</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="描述" name="description" value="${product.description}">
                    </div>
                    <div class="col-md-2 title">录入时间</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" disabled placeholder="录入时间"  name="inputTime" class="form-control pull-right"
                                   value="${product.inputTime}" id="datepicker">
                        </div>
                    </div>

                    <div class="col-md-2 title">备注</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="备注" name="remark" value="${product.remark}">
                    </div>


                    <div class="col-md-2 title">修改日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="修改日期" class="form-control pull-right" name="createTime"
                                   value="${product.createTime}" id="datepicker1">
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

</div>
<!-- 内容区域 /-->
</body>
<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="../../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="../../css/style.css">
<script>
    $('#datepicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#datepicker1').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>