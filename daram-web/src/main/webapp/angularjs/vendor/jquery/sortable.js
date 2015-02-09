	$(function() {
		$(".column").sortable({
			connectWith : ".column",
			handle : ".portlet-header",
			cancel : ".portlet-toggle",
			placeholder : "portlet-placeholder ui-corner-all"
		});


		$(".portlet-toggle").click(function() {
			var icon = $(this);
			icon.closest(".portlet").find(".portlet-content").toggle();
		});
	});