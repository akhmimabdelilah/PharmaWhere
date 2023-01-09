
$(document)
	.ready(
		function () {

			table = $('#tpharmacie')
				.DataTable({
					ajax: {
						url: "pharmacie/",
						dataSrc: ''
					},
					columnDefs: [{
						"targets": 1,
						"render": function (data) {
							return '<img src="data:image/jpeg;base64,' + data.image + '" height="100" width="100" alt="' + data.nom + '"/>';
						}
					},
					{
						"targets": 3,
						"render": function (data) {
							return '<span id="etat" class="' + data.status + '">' + data.status + '</span>';
						}
					},
					{
						"targets": 9,
						"render": function (data) {
							return (data.etat != "APPROVEE" ? '<button type="button" class="btn btn-outline-secondary accepter" data-id="' + data.id + '">Accepter</button>' : '') + (data.etat != "REFUSEE" ? '<button type="button" class="btn btn-outline-danger refuser" data-id="' + data.id + '">Refuser</button>' : '');
						}
					}],
					columns: [
						{
							data: "id"
						},
						{
							data: null
						},
						{
							data: "nom"
						},
						{
							data: null
						},
						{
							data: "lat"
						},
						{
							data: "log"
						},
						{
							data: "adresse"
						},
						{
							data: "zone.ville.nom"
						},
						{
							data: "zone.nom"
						},
						{
							data: null
						}]

				});

			$('#table-content')
				.on(
					'click',
					'.accepter',
					function () {
						var id = $(this).attr('data-id');
						$
							.ajax({
								url: 'pharmacie/'
									+ id + '/valid',
								data: {},
								type: 'PUT',
								async: false,
								success: function (
									data,
									textStatus,
									jqXHR) {

									table.ajax
										.reload();
								},
								error: function (
									jqXHR,
									textStatus,
									errorThrown) {
								}
							});

					});



			$('#table-content').on(
				'click',
				'.refuser',
				async function () {


					var id = $(this).attr('data-id');
					$
						.ajax({
							url: 'pharmacie/'
								+ id + '/invalid',
							data: {},
							type: 'PUT',
							async: false,
							success: function (
								data,
								textStatus,
								jqXHR) {

								table.ajax
									.reload();
							},
							error: function (
								jqXHR,
								textStatus,
								errorThrown) {
							}
						});

				});


		});


