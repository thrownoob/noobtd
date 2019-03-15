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
  <script src="laydate/laydate.js"></script> 
     <script >
     lay('#version').html('-v'+ laydate.v);

   //执行一个laydate实例
   laydate.render({
     elem: '#ttime',
     range: true
   });
     
     </script>
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
                <li><a href="allowance.jsp">余量查询</a><span></span></li>
                <li class="active"><a>耗材出入库查询</a><span></span></li>
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
                耗材出入库查询
            </p>
        </div>
    </div>

<div class="box">

   <form id="form1">
    <div class="title">耗材出入库查询</div>
    <div class="content">
        <!--搜索输入框及查询、重置按钮-->
        <div class="container content_width">
            <div class="person_search">
                <div class="search_input">
                    <div class="input-group mb-3">
                        <span>型号：</span>
                        <input id="name" name="name"  type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" class="form-control" placeholder="请输入型号">
                          <span>支行：</span>
                        <input id="tzhihang" name="tzhihang" onkeyup="this.value=this.value.replace(/\s+/g,'')" type="text" class="form-control" placeholder="请输入支行">
                         <span>日期：</span>
                        <input id="ttime" name="ttime"  type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" class="form-control" placeholder="请选择时间">
                        <span>申请人：</span>
                        <input id="cname" name="cname"  type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" class="form-control" placeholder="请输入姓名">
                         <span>办理人：</span>
                        <input id="tname" name="tname"  type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" class="form-control" placeholder="请输入姓名">
                    </div>
                     
                </div>
                <div class="search_input">
                    <div class="input-group mb-3" hidden>
                        <span>工号：</span>
                        <input id="job_num" type="text" class="form-control" placeholder="请输入工号">
                    </div>
                </div>
                <div class="search_input">
                    <button class="btn btn-primary search_btn" type="button" id="search_btn">查询</button>
                    <button class="btn btn-primary search_btn" type="button" id="back_btn">重置</button>
                </div>
            </div>
            <div class="line"></div>
        </div>
        </form>
        <!--添加按钮及bootstrap的模态框-->
        <form action="" id="form2">
        <div class="export">
            <button id="new_add" type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#renyuan">
                <img src="img/add_two.png"/>
                <span>添加</span>
            </button>
            <div class="modal fade" id="renyuan">
                <div class="modal-dialog modal-lg modal_position">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">添加</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <table id="xztb" class="table">
                                <!--新修改弹窗的样式-->
                                <tbody>
                                <tr>
                                    <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>名称/型号</label>
                                    </td>
                                    <td><input class="Name" id="aname" name="aname" type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入名称"/></td>
                                    <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>支行</label>
                                    </td>
                                    <td><input class="Zhi" type="text" id="atzhihang" name="atzhihang" onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入支行"/></td>
                                </tr>
                                <tr>
                                    <td class="tb_bg"><label for=""><font style="font-size: 14px; color: red;">*</font>数量</label>
                                    </td>
                                    <td><input class="Number" type="text" id="tnumber" name="tnumber" oninput = "value=value.replace(/[^\d]/g,'')" onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入数量"/></td>
                                    <td class="tb_bg"><label for="">操作员</label></td>
                                    <td><input class="Cname" type="text" name="cname" id="cname" onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入操作员姓名" > </td>
                                   
                                </tr>
                                <tr>
                                    <td class="tb_bg"><label for="">操作</label></td>
                                    <td>
                                        <select id="tip" name="tip" class="form-control" style="font-size: 13px; color: #666;">
                                            <option value="0">入库</option>
	                                        <option value="1">出库</option>
	                                         <option value="10">新增</option>
                                        </select>
                                    </td>
                                    <td class="tb_bg"><label for="">领取人</label></td>
                                    <td><input class="Tname" type="text" name="tname" id="tname" onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入操作员姓名" > </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                            <button id="add_btn" type="button" class="btn btn-secondary">确定</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </form>
        <!--表格列表-->
        <table id="tb" class="table">
            <thead>
            <tr>
                <th>姓名</th>
                <th>支行</th>
                <th>数量</th>
                <th>操作员</th>
                <th>领取人</th>
                <th>出库/入库</th>
                <th>余量</th>
                <th>日期</th>
             </tr>
            </thead>
            <tbody id="show_tbody">
             
            
            </tbody>
        </table>
    </div>
</div>

<script src="js/mejs.js"></script>
</body>
</html>