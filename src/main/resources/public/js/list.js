$(document).ready(function(){
	getStaffPageInfo(onpage);
	if(empty==0&&onpage>1){
		--onpage;
		getStaffPageInfo(onpage);
	}
	myfunction(total);
})
function getStaffPageInfo(onpage){
	$.ajax({
		type:"GET",
		url:"/ktv/staff/list/"+parseInt(onpage)+"/"+post+"/"+name,
		async:false,
		success:function(TS){
			document.getElementById('table_content').innerHTML = render(TS);
			empty = TS.staffs.length;
			total = TS.pageDto.total_rows;
		}
	})
}
function myfunction(total_rows){
	layui.use(['laypage', 'layer'], function(){
		var laypage = layui.laypage
		  ,layer = layui.layer
		  ,page_size=$('#page_size').val();
		 laypage.render({
			    elem: 'page'
			    ,count:total_rows
			    ,curr:onpage
		 		,limit:page_size
		 		,limits:[page_size]
			    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
			    ,jump: function(obj,first){
			      console.log(obj);
			      if(!first){
				      	onpage=obj.curr;
				      	getStaffPageInfo(onpage);
			      }
			    	  
			    }
			  });
	})
}
var render = function(TS){
				var html="",
					last = TS.staffs.length;
				for(var i=0;i<last;i++){
					html+= "<tr>"+
								"<td>" + TS.staffs[i].id + "</td>" +
								"<td>" + TS.staffs[i].staffname + "</td>" +
								"<td>" + TS.staffs[i].post + "</td>" +
								"<td class=\"am-hide-sm-only\">" + TS.staffs[i].birthday + "</td>" +
								"<td class=\"am-hide-sm-only\">" + TS.staffs[i].age + "</td>" +
								"<td class=\"am-hide-sm-only\">" + TS.staffs[i].salary + "</td>" +
								"<td>"+
									"<div class=\"am-btn-toolbar\">"+
		                    				"<div class=\"am-btn-group am-btn-group-xs\">"+
		                    					"<a href=\"/ktv/staff/" +
		                    					onpage+"/"+TS.staffs[i].id+"\" class=\"am-btn am-btn-default am-btn-xs am-text-secondary\"><span class=\"am-icon-pencil-square-o\"></span> 修改</a>"+
		                    					"<a href=\"/ktv/staff/" +
		                    					onpage+"/"+TS.staffs[i].id+"/0\" class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\"><span class=\"am-icon-trash-o\"></span> 删除</a>"+
						                 "</div>"+
					                 "</div>"+
					              "</td>"	
								"</tr><br/>"
								
				}
				return html;
			}
