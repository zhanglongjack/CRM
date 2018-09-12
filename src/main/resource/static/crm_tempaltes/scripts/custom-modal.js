$(function() {
	$("#ModifyModal").on("shown.bs.modal", function() {  
		$("#ModifyModalSubmit").click(function(){
			var bootstrapValidator = $("#modifyDataFrom").data("bootstrapValidator");
			$('#modifyDataFrom').data('bootstrapValidator').resetForm();
			bootstrapValidator.validate();
			if($("#modifyDataFrom").data('bootstrapValidator').isValid()){
				var targetUrl = pageQueryParams.preUrl+$("#modifyDataFrom #modifyModel").val();
				var id = pageQueryParams.preUrl+$("#modifyDataFrom #modifyId").val();
				var dataParams = $("#modifyDataFrom").serialize();     
				$.ajax({ 
					type:'post',  
					url:targetUrl, 
					cache: false,
					data:dataParams,  
					dataType:'json', 
					success:function(data){  
						toastr.success(id ? "更新成功!":"添加成功!"); 
						render(pageQueryParams);  // 渲染页面函数
						$('#ModifyModal').modal('toggle')
						//window.location.href="/custInfoView?index="+currPage+"&pageSize="+currPageSize;
					},
					error:function(data){ 
						if(data.responseJSON){
							toastr.error(dataParams+"添加失败:"+data.responseJSON.message); 
						}
					}
				})
			}else{
				//错误消息提示，默认背景为浅红色 
				toastr.warning("请填写完整资料再提交");  
			}
		});
	}); 
});


var initTimePicker =function(){
	$('#datepicker').datepicker({
	    format: "yyyy-mm-dd",
	    maxViewMode: 0,
	    autoclose: true,
	    todayHighlight: true
	});
	
	$('[datepicker-flag]').datepicker({
		format: "yyyymmdd",
		maxViewMode: 0,
		autoclose: true,
		todayHighlight: true
	});
	
	$('#datetimepicker').datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
		startDate: "2018-01-01 00:00",
	    language: "zh-CN",
	    autoclose: true,
	    todayHighlight: true,
	    pickerPosition: "bottom-left"
	});
}
