$(document)
	.ready(
		function () {

			table = $('#tville')
				.DataTable({
					ajax: {
						url: "/ville/",
						dataSrc: ''
					},
					columns: [
						{
							data: "id"
						},
						{
							data: "nom"
						},
						{
							"render": function () {
								return '<button type="button" class="btn btn-outline-danger supprimer">Supprimer</button>';
							}
						},
						{
							"render": function () {
								return '<button type="button" class="btn btn-outline-secondary modifier">Modifier</button>';
							}
						}]

				});



			$('#btn').click(
				function () {
					var nom = $("#nom");
					if ($('#btn').text() == 'Ajouter') {
						var p = {
							nom: nom.val()
						};

						$.ajax({
							url: '/ville/',
							contentType: "application/json",
							dataType: "json",
							data: JSON.stringify(p),
							type: 'POST',
							async: false,
							success: function (data, textStatus,
								jqXHR) {
								table.ajax.reload();
							},
							error: function (jqXHR, textStatus,
								errorThrown) {
								console.log(textStatus);
							}
						});
						$("#main-content").load(
							"./page/ville.html");
					}


				});
			$('#table-content')
				.on(
					'click',
					'.supprimer',
					function () {

						var id = $(this).closest('tr').find(
							'td').eq(0).text();
						var oldLing = $(this).closest('tr')
							.clone();
						var newLigne = '<tr style="position: relative;" class="bg-light" ><th scope="row">'
							+ id
							+ '</th><td colspan="4" style="height: 100%;">';
						newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment supprimer cette ville ? </h4>';
						newLigne += '<button type="button" class="btn btn-outline-primary btn-sm confirmer" style="margin-left: 25px;">Oui</button>';
						newLigne += '<button type="button" class="btn btn-outline-danger btn-sm annuler" style="margin-left: 25px;">Non</button></td></tr>';

						$(this).closest('tr').replaceWith(
							newLigne);
						$('.annuler').click(
							function () {
								$(this).closest('tr')
									.replaceWith(
										oldLing);
							});
						$('.confirmer')
							.click(
								function (e) {
									e.preventDefault();
									$
										.ajax({
											url: '/ville/'
												+ id,
											data: {},
											type: 'DELETE',
											async: false,
											success: function (
												data,
												textStatus,
												jqXHR) {
												if (data
													.includes("error") == true) {
													$("#error")
														.modal();
												} else {
													table.ajax
														.reload();
												}
											},
											error: function (
												jqXHR,
												textStatus,
												errorThrown) {
												$(
													"#error")
													.modal();
											}
										});

								});

					});

			$('#table-content').on(
				'click',
				'.modifier',
				function () {
					var btn = $('#btn');
					var id = $(this).closest('tr').find('td').eq(0)
						.text();

					var nom = $(this).closest('tr').find('td').eq(
						1).text();


					btn.text('Modifier');
					$("#nom").val(nom);
					$("#id").val(id);

					btn.click(function (e) {
						e.preventDefault();
						var p = {
							id: $("#id").val(),
							nom: $("#nom").val()
						};
						if ($('#btn').text() == 'Modifier') {
							$.ajax({
								url: '/ville/',
								contentType: "application/json",
								dataType: "json",
								data: JSON.stringify(p),
								type: 'POST',
								async: false,
								success: function (data,
									textStatus, jqXHR) {
									table.ajax.reload();
									$("#nom").val('');
									btn.text('Ajouter');
								},
								error: function (jqXHR, textStatus,
									errorThrown) {
									console.log(textStatus);
								}
							});
							$("#main-content").load(
								"./page/ville.html");
						}
					});
				});
		});