$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event) {
	// Clear status msges---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	
	//if valid
	var type = ($("#hidcomIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
		url : "UserAPI",
		type : type,
		data : $("#Complaint").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onComplainSaveComplete(response.responseText, status);
		}
	});
	
	//$("#Complaint").submit();
	

});

function onComplainSaveComplete(response, status) 
{ 
 	if(status == "success")
 	{
		var resultSet = JSON.parse(response);
		
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved."); 
			$("#alertSuccess").show(); 
			
			$("#comGrid").html(resultSet.data); 
		}else if(resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data); 
 			$("#alertError").show();
		}
	}else if(status == "error")
	{
		 $("#alertError").text("Error while saving status."); 
		 $("#alertError").show();
	}else
	{
		 $("#alertError").text("Error while saving wena ekk."); 
		 $("#alertError").show();
	}
		$("#hidcomIDSave").val(""); 
		$("#Complaint")[0].reset(); 
}

//Update-------
$(document).on("click", ".btnUpdate", function(event)
	{
		$("#hidcomIDSave").val($(this).data("itemid"));
		$("#Username").val($(this).closest("tr").find('td:eq(1)').text()); 
		$("#Userpwd").val($(this).closest("tr").find().text());
		$("#Userphn").val($(this).closest("tr").find('td:eq(2)').text()); 
		$("#Useremail").val($(this).closest("tr").find('td:eq(3)').text()); 
		$("#Useradrs").val($(this).closest("tr").find('td:eq(4)').text());
		$("#Useracc").val($(this).closest("tr").find('td:eq(5)').text()); 
		$("#Date").val($(this).closest("tr").find('td:eq(6)').text()); 
	});

//delete	
$(document).on("click", ".btnRemove", function(event) {

	
	$.ajax(
	{
		url : "UserAPI",
		type : "DELETE",
		data : "comId=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status)
		{
			onComplainDeleteComplete(response.responseText, status);
		}
	});
	
});	

function onComplainDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#comGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


