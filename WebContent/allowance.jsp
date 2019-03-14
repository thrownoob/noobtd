<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>表格制作</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
	
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/twitter-bootstrap/4.2.1/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bootbox.min.js"></script>
	  <script src="js/jquery-1.10.1.min.js"></script>
</head>
   <script>
    $(function(){
        $('.type-right').click(function(){
            $(this).prev('.type-left').toggleClass('showListType')
        });
        $('.type-left ul li').click(function(){
            $(this).addClass('active').siblings('li').removeClass('active')
        })
    })
</script>
<body>
 <div class="change-type">
        <div class="type-left" :class="showType == true ? 'showListType':''">
            <ul>
                <li class="active" ><a href=" allowance.jsp">余量查询</a><span></span></li>
                <li  ><a href="haocai.jsp">耗材出入库查询</a><span></span></li>
                <li><a></a><span></span></li>
                <li><a></a><span></span></li>
                <li><a></a><span></span></li>
                <li><a></a><span></span></li>
                <li><a></a><span></span></li>
            </ul>
        </div>
        <div class="type-right">
            <p>
                <i class="el-icon-menu"></i>
                余量查询
            </p>
        </div>
    </div>

<div class="box">
<form id="f1">
    <div class="title">余量查询</div>
    <div class="content">
        <!--搜索输入框及查询、重置按钮-->
        <div class="container content_width">
            <div class="person_search">
                <div class="search_input">
                    <div class="input-group mb-3">
                        <span>姓名：</span>
                        <input id="sname"  name="sname" type="text" class="form-control" placeholder="请输入姓名">
                    </div>
                </div>
              
                <div class="search_input">
                    <button class="btn btn-primary search_btn" type="button" id="search_btn1">查询</button>
                    <button class="btn btn-primary search_btn" type="button" id="back_btn">重置</button>
                </div>
            </div>
            <div class="line"></div>
        </div>
        </form>
       
        <table id="tb" class="table">
            <thead>
            <tr>
                <th>名称</th>
                <th>数量</th>
                <th>添加时间</th>

            </tr>
            </thead>
            
        </table>
    </div>
</div>

<script src="js/mejs.js"></script>
</body>

</html>