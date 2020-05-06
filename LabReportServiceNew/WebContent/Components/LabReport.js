$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "LabReportAPI",
		type : type,
		data : $("#formHospital").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onHospitalSaveComplete(response.responseText, status);
		}
	});	
});

function onHospitalSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divUsersGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidHospitalIDSave").val("");
	$("#formHospital")[0].reset();
}

//DELETE=============================================================================
	$(document).on("click", ".btnRemove", function(event) {
		$.ajax({
			url : "LabReportAPI",
			type : "DELETE",
			data : "lReportID=" + $(this).data("hospitalid"),
			dataType : "text",
			complete : function(response, status) {
				onHospitalDeleteComplete(response.responseText, status);
			}
		});
	});
	
	
	function onHospitalDeleteComplete(response, status) {
		if (status == "success") {
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success") {
				$("#alertSuccess").text("Successfully deleted.");
				$("#alertSuccess").show();
				$("#divUsersGrid").html(resultSet.data);
			} else if (resultSet.status.trim() == "error") {
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
		} else if (status == "error") {
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		} else {
			$("#alertError").text("Unknown error while deleting hospital..");
			$("#alertError").show();
		}
	}


// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidHospitalIDSave").val(
					$(this).closest("tr").find('#hidHopsitalIDUpdate').val());
			$("#lReportNum").val($(this).closest("tr").find('td:eq(0)').text());
			$("#lReportName").val($(this).closest("tr").find('td:eq(1)').text());
			$("#lReportDet").val($(this).closest("tr").find('td:eq(2)').text());
	
		});
// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// PHONE NUMBER------------------------
	if ($("#lReportNum").val().trim() == "") {
		return "Insert the Report Number of the Lab.";
	}
	// is numerical value
	var tmpPhone = $("#lReportNum").val().trim();
	if (!$.isNumeric(tmpPhone)) {
		return "Insert Only Numbers for Lab Report No.";
	}
	// DESC------------------------
	if ($("#lReportName").val().trim() == "") {
		return "Insert the name of the Lab Report.";
	}
	
	if ($("#lReportDet").val().trim() == "") {
		return "Insert the Lab Report Details.";
	}
	
	return true;
}
