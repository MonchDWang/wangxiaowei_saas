<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="/baseinfo/factory/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        厂家信息
        <small>厂家信息</small>
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
            <h3 class="box-title">厂家列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="/baseinfo/factory/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
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
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">序号</th>
                        <th class="sorting">编号</th>
                        <th class="sorting">类型</th>
                        <th class="sorting">全称</th>
                        <th class="sorting">简称</th>
                        <th class="sorting">联系人</th>
                        <th class="sorting">电话</th>
                        <th class="sorting">手机</th>
                        <th class="sorting">地址</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="factory"  varStatus="st">
                        <tr>
                            <td><input type="checkbox" name="id" value="${factory.id }"/></td>
                            <td>${st.count }</td>
                            <td>${factory.id }</td>
                            <td>${factory.ctype}</td>
                            <td>${factory.fullName}</td>
                            <td>${factory.factoryName}</td>
                            <td>${factory.contacts}</td>
                            <td>${factory.phone}</td>
                            <td>${factory.mobile}</td>
                            <td>${factory.address}</td>
                            <td>
                                <a href="${ctx}/baseinfo/factory/toUpdate.do?id=${factory.id}">编辑</a>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="/baseinfo/factory/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>
</html>