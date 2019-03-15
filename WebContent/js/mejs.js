$(function () {

    $('#add_btn').click(function () {//出入库按钮
        methods.addHandle()
    })

    $('#show_tbody').on('click','.edit', function () {
        trIndex = $('.edit', '#show_tbody').index($(this));
        addEnter = false;
        $(this).parents('tr').addClass('has_case');
        methods.editHandle(trIndex);
    })

    $('#search_btn').click(function () {//记录查询按钮
    	 
    	$("#tb  tr:not(:first)").html("");
        methods.seachName();
    })
    $('#search_btn1').click(function () {//余量查询
    	$("#tb  tr:not(:first)").html("");
   methods.seachName1();
    })
    
    $('#back_btn').click(function () {
        $('.form-control').val(' ');
       
        window.location.reload();
        methods.resectList();
    })

    $('.del').click(function () {
        $(this).parents('tr').remove();
    })

    $('#renyuan').on('hide.bs.modal',function() {
        addEnter = true;
        $('#show_tbody tr').removeClass('has_case');
        $('#xztb input').val(' ');
        $('#xztb select').find('option:first').prop('selected', true)
    });

})

var addEnter = true,
    noRepeat = true,
    jobArr = [],
    phoneArr = [],
    tdStr = '',
    trIndex,
    hasNullMes = false,
    tarInp = $('#xztb input'),
    tarSel = $('#xztb select');

var methods = {

    addHandle: function (the_index) {//出入库
    	
    	 
    	 
    if( methods.checkMustMes()== true){//非空判断为真
    		
    	$.ajax({
    		
    		type:"post",
    		url:"dexservlet",
    	    dataType:"",
    	    data: $('#form2').serialize(),
    	    success : function(data){
//    	    	if(data=='ok'){
//    	    		 bootbox.alert({
//    	 	                title: "@gua",
//    	 	                message: "操作成功",
//    	 	               callback: function(){window.location.href="haocai.jsp"}
//    	 	            })
//    	 	            
//    	    	}else{
//    	    		if(data== 'nokey' ){ //库内有此数据
//    	    			 bootbox.alert({
//     	 	                title: "@gua",
//     	 	                message: "库内已有请进行出入库操作",
//     	 	                callback: function(){window.location.href="haocai.jsp"}
//     	 	            })
//    	    		}else{
//    	    	 bootbox.alert({
// 	                title: "@gua",
// 	                message: "无此耗材请新增",
// 	            })
//    	    		}
//    	    	}
    	    	if(data=='ok'){
    	    		bootbox.alert({
	 	                title: "@gua",
	 	                message: "操作成功",
	 	               callback: function(){window.location.href="haocai.jsp"}
	 	            })
    	    	}
    	    	if(data== 'nokey'){
    	    		 bootbox.alert({
  	 	                title: "@gua",
  	 	                message: "库内已有请进行出入库操作",
  	 	            
    	    	})
    	    	}
    	    	if(data== 'numer'){
   	    		 bootbox.alert({
 	 	                title: "@gua",
 	 	                message: "余量不足",
 	 	                
   	    	})
   	    	}
    	    	if(data== 'fail'){
      	    		 bootbox.alert({
    	 	                title: "@gua",
    	 	                message: "无此耗材请新增",
    	 	                
      	    	})
      	    	}
    	    },
    	error : function() {
               bootbox.alert({
	                title: "@gua",
	                message: "错误",
	            })
            return false;
        }
    	})
    }
    	
//        hasNullMes = false;
//        methods.checkMustMes();
//        if (hasNullMes) {
//            return;
//        }
//        if (addEnter) {
//            methods.checkRepeat();
//            if (noRepeat) {
//                methods.setStr();
//                $('#show_tbody').append('<tr>' + tdStr + '</tr>');
//                $('#renyuan').modal('hide');
//            }
//        }else{
//            methods.setStr();
//            $('#show_tbody tr').eq(trIndex).empty().append(tdStr);
//            $('#renyuan').modal('hide');
//        }
    },
    editHandle: function (the_index) {

        var tar = $('#show_tbody tr').eq(the_index);
        var nowConArr = [];
        for (var i=0; i<tar.find('td').length-1;i++) {
            var a = tar.children('td').eq(i).html();
            nowConArr.push(a);
        }

        $('#renyuan').modal('show');

        for (var j=0;j<tarInp.length;j++) {
            tarInp.eq(j).val(nowConArr[j])
        }
        for (var p=0;p<tarSel.length;p++) {
            var the_p = p+tarInp.length;
            tarSel.eq(p).val(nowConArr[the_p]);
        }

    },
    setStr: function () {

        tdStr = '';
        for (var a=0; a<tarInp.length; a++) {
            tdStr+= '<td>' + tarInp.eq(a).val() + '</td>'
        }
        for (var b=0; b<tarSel.length; b++) {
            tdStr+= '<td>' + tarSel.eq(b).val() + '</td>'
        }
        tdStr+= '<td><a href="#" class="edit">编辑</a> <a href="#" class="del">删除</a></td>';

    },
    seachName1:function(){
    $.ajax({
    	type:"post",
    	url:"Allowan",
    	dataType:"json",
    	data:$('#f1').serialize(),
       
    	success : function(data){
    		$.each(data,function(index,item){
    			if(item.name== "null"){
    				bootbox.alert({
    	                title: "@gua",
    	                message: "无此记录",
    	                
    	            })
	    		}else{
	    			  var tr;
	    	            tr += "<th>" + item.name + "</th>";
                        tr += "<th>" + item.number + "</th>";
                        tr += "<th>" + item.time + "</th>";
	    	            $("#tb").append("<tr>"+tr+"</tr>");
	    	
	    		}
	      
	    	});
    		
    	},
	  error : function() {
			bootbox.alert({
                title: "@gua",
                message: "异常",
                
            })
    }
     
     
    	
    })	
    },
    seachName: function () {
    	$.ajax({
    		type:"post",
    		url:"snameservlet",
    	    dataType:"json",
    	    data: $('#form1').serialize(),
    	    
    	    success : function(data){
//    	    	 var tb = document.getElementById("tb");
// 	    	    //删除原先数据，2 为保留表头，值为表头行数减1
// 	    	    for (var n = tb.rows.length ; n > 1; n--) {
// 	    	        tb.deleteRow(n);
// 	    	    }
    	    	$.each(data,function(index,item){
    	    		if(item.name== "null"){
    	    			bootbox.alert({
        	                title: "@gua",
        	                message: "无此记录",
        	                
        	            })
    	    		}else{
    	    			  var tr;
    	    	            tr += "<th>" + item.name + "</th>";
    	    	            tr += "<th>" + item.tzhihang + "</th>";
    	    	            tr += "<th>" + item.number + "</th>";
    	    	            tr += "<th>" + item.cname + "</th>";
    	    	            tr += "<th>" + item.tname + "</th>";
    	    	            if(item.tip == 0){
    	    	            	 tr += "<th>入库</th>";
    	    	            }
    	    	            
    	    	            if(item.tip == 1){
    	    	            	tr += "<th>出库</th>";
							}
    	    	           
    	    	            tr += "<th>" + item.gcnum + "</th>";
    	    	            tr += "<th>" + item.time + "</th>";
    	    	            $("#tb").append("<tr>"+tr+"</tr>");
    	    		}
    	      
    	    	});
    	    	},
    	error : function() {
    		bootbox.alert({
                title: "@gua",
                message: "异常",
                
            })
        }
    	     
    	    
    		
    	})
    	


    },
    resectList: function () {
        $('#show_tbody tr').show();
    },
    checkMustMes: function () {
    	 
    	var aa=document.getElementById("tip").value;
    	 if(aa == "10"){
    		  if ($('.Name').val().trim()==='') {
    	            bootbox.alert({
    	                title: "@gua",
    	                message: "姓名为必选项，请填写",
    	                closeButton:false
    	            })
    	            hasNullMes = true;
    	            return
    	        }
    		  if ($('.Number').val().trim()==='') {
    	            bootbox.alert({
    	                title: "@gua",
    	                message: "数量为必选项，请填写",
    	                closeButton:false
    	            })
    	            hasNullMes = true;
    	            return
    	        }
    	 }else{
    		 
    		 
    	 
    

        if ($('.Name').val().trim()==='') {
            bootbox.alert({
                title: "@gua",
                message: "姓名为必选项，请填写",
                closeButton:false
            })
            hasNullMes = true;
            return
        }
        if ($('.Zhi').val().trim()==='') {
            bootbox.alert({
                title: "@gua",
                message: "支行为必选项，请填写",
                closeButton:false
            })
            hasNullMes = true;
            return
        }
        if ($('.Number').val().trim()==='') {
            bootbox.alert({
                title: "@gua",
                message: "数量为必选项，请填写",
                closeButton:false
            })
            hasNullMes = true;
            return
        }
        if ($('.Cname').val().trim()==='') {
            bootbox.alert({
                title: "@gua",
                message: "操作员为必选项，请填写",
                closeButton:false
            })
            hasNullMes = true;
            return
        }
        if ($('.Tname').val().trim()==='') {
            bootbox.alert({
                title: "@gua",
                message: "领取人为必选项，请填写",
                closeButton:false
            })
            hasNullMes = true;
            return
        }
    	 }
    	 return true;
    },
    checkRepeat: function () {

        jobArr = [], phoneArr = [];

        for (var i = 0; i<$('#show_tbody tr:not(".has_case")').length;i++) {
            var par = '#show_tbody tr:not(".has_case"):eq(' + i + ')';
            var a = $('td:eq(1)', par).html().trim(),
                b = $('td:eq(2)', par).html().trim();
            jobArr.push(a);
            phoneArr.push(b);
        }
        var jobNum = $('.jobNum').val().trim(),
            phoneNum = $('.phoneNum').val().trim();

        if (jobArr.indexOf(jobNum)>-1) {
            noRepeat = false;
            bootbox.alert({
                title: "来自火星的提示",
                message: "工号重复了，请重新输入",
                closeButton:false
            })
            return;
        }
        if (phoneArr.indexOf(phoneNum)>-1) {
            noRepeat = false;
            bootbox.alert({
                title: "来自火星的提示",
                message: "手机号码重复了，请重新输入",
                closeButton:false
            })
            return;
        }
        noRepeat = true;
    }
}