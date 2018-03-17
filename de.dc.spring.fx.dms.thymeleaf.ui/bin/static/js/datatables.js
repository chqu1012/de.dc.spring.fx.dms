$(document).ready( function () {
	 var table = $('#ticketsTable').DataTable({
			"scrollY" : '50vh',
			"scrollCollapse" : true,
			"language" : {
				url : "json/dataTableGerman.json"
			}
	 })
});