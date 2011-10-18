
RKS={
		checkValue : function()
		{
			var form = jQuery("#form");
			if( jQuery("input[type='checkbox']:checked",form).length > 0 )
			{ 
				if(confirm("Are you sure?"))
				{
					form.submit();
				}
			}
			else
			{
				alert("Please choose objects for deleting");
				return false;
			}
		},
		search : function(url, value){
			ACT.go(url+"?"+value+"="+jQuery("#"+value).val());
		},
		searchItem : function(thiz)
		{
			var categoryId = jQuery("#categoryId").val();
			var fromDate = jQuery("#fromDate").val();
			var toDate = jQuery("#toDate").val();
			var transactionType = jQuery("#transactionType").val();
			var searchName = jQuery("#searchName").val();
			ACT.go("itemList.form?fromDate="+fromDate+"&toDate="+toDate+"&categoryId="+categoryId+"&transactionType="+transactionType+"&searchName="+searchName);
		},
		exportData : function(thiz)
		{
			var categoryId = jQuery("#categoryId").val();
			var fromDate = jQuery("#fromDate").val();
			var toDate = jQuery("#toDate").val();
			var transactionType = jQuery("#transactionType").val();
			var searchName = jQuery("#searchName").val();
			ACT.go("itemExport.form?categoryId="+categoryId+"&fromDate="+fromDate+"&toDate="+toDate+"&transactionType="+transactionType+"&searchName="+searchName);
		}
		
};


