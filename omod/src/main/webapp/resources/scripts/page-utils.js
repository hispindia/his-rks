/**
 *  Copyright 2009 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of RKS module.
 *
 *  RKS module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  RKS module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with RKS module.  If not, see <http://www.gnu.org/licenses/>.
 *
*/
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


