$(document)
	.ready(
		function() {

			table = $('#tpharmacie')
				.DataTable({
					ajax: {
						url: "pharmacie/",
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
							"render": function() {
								return '<button type="button" class="btn btn-outline-danger supprimer">Supprimer</button>';
							}
						},
						{
							"render": function() {
								return '<button type="button" class="btn btn-outline-secondary modifier">Modifier</button>';
							}
						}]

				});

			$.ajax({
				url: '/ville/',
				type: 'GET',
				success: function(data) {
					var option = '';
					data.forEach(e => {
						option += '<option value =' + e.id + '>' + e.nom + '</option>';
					});
					$('#ville').append(option);
				},
				error: function(jqXHR, textStatus,
					errorThrown) {
					console.log(textStatus);
				}

			});
			async function loadZones(i) {
				await $.ajax({
					url: '/ville/' + i + "/zones",
					type: 'GET',
					async: true,
					success: function(data) {
						var option = '<option value="0">choisir une option</option>';
						data.forEach(e => {
							option += '<option value =' + e.id + '>' + e.nom + '</option>';
						});
						$('#zone').html(option);
						alert(i)
					},
					error: function(jqXHR, textStatus,
						errorThrown) {
						console.log(textStatus);
					}

				});
			}
			$("#ville").on("change", function() {
				loadZones($("#ville").val());
			})



			$('#btn').click(
				function() {
					var nom = $("#nom");
					var lat = $("#lat");
					var log = $("#log");
					var adresse = $("#adresse");
					var zone = $("#zone");
					if ($('#btn').text() == 'Ajouter') {
						var p = {
							nom: nom.val(),
							lat: lat.val(),
							log: log.val(),
							adresse: adresse.val(),
							zone: {
								id: zone.val()
							}
						};

						$.ajax({
							url: 'pharmacie/',
							contentType: "application/json",
							dataType: "json",
							data: JSON.stringify(p),
							type: 'POST',
							async: false,
							success: function(data, textStatus,
								jqXHR) {
								table.ajax.reload();
							},
							error: function(jqXHR, textStatus,
								errorThrown) {
								console.log(textStatus);
							}
						});
						$("#main-content").load(
							"./page/pharmacie.html");
					}
				});

			$('#table-content')
				.on(
					'click',
					'.supprimer',
					function() {

						var id = $(this).closest('tr').find(
							'td').eq(0).text();
						var oldLing = $(this).closest('tr')
							.clone();
						var newLigne = '<tr style="position: relative;" class="bg-light" ><th scope="row">'
							+ id
							+ '</th><td colspan="4" style="height: 100%;">';
						newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment supprimer cette pharmacie ? </h4>';
						newLigne += '<button type="button" class="btn btn-outline-primary btn-sm confirmer" style="margin-left: 25px;">Oui</button>';
						newLigne += '<button type="button" class="btn btn-outline-danger btn-sm annuler" style="margin-left: 25px;">Non</button></td></tr>';

						$(this).closest('tr').replaceWith(
							newLigne);
						$('.annuler').click(
							function() {
								$(this).closest('tr')
									.replaceWith(
										oldLing);
							});
						$('.confirmer')
							.click(
								function(e) {
									e.preventDefault();
									$
										.ajax({
											url: 'pharmacie/'
												+ id,
											data: {},
											type: 'DELETE',
											async: false,
											success: function(
												data,
												textStatus,
												jqXHR) {
												if (data
													.includes("error") == true) {
													$(
														"#error")
														.modal();
												} else {
													table.ajax
														.reload();
												}
											},
											error: function(
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
				async function() {
					var btn = $('#btn');
					var id = $(this).closest('tr').find('td').eq(0)
						.text();

					var nom = $(this).closest('tr').find('td').eq(
						1).text();
					var lat = $(this).closest('tr').find('td')
						.eq(2).text();
					var log = $(this).closest('tr').find('td')
						.eq(3).text();
					var adresse = $(this).closest('tr').find('td')
						.eq(4).text();
					var ville = $(this).closest('tr').find('td')
						.eq(5).text();
					var zone = $(this).closest('tr').find('td')
						.eq(6).text();


					btn.text('Modifier');
					$("#nom").val(nom);
					$("#lat").val(lat);
					$("#log").val(log);
					$("#adresse").val(adresse);

					var op = $('#ville option').filter(function() { return $(this).html() == ville; }).val();
					$("#ville").val(op);
					await loadZones(op);

					var opi = $('#zone option').filter(function() { return $(this).html() == zone; }).val();
					$("#zone").val(opi);

					$("#id").val(id);

					btn.click(function(e) {
						e.preventDefault();
						var p = {
							id: $("#id").val(),
							nom: $("#nom").val(),
							lat: $("#lat").val(),
							log: $("#log").val(),
							adresse: $("#adresse").val(),
							zone: {
								id: $("#zone").val()
							},
							image: {
								data: $("#image").val()
							}
						};
						if ($('#btn').text() == 'Modifier') {
							$.ajax({
								url: 'pharmacie/',
								contentType: "application/json",
								dataType: "json",
								data: JSON.stringify(p),
								type: 'POST',
								async: false,
								success: function(data,
									textStatus, jqXHR) {
									table.ajax.reload();
									$("#nom").val('');
									$("#lat").val('');
									$("#log").val('');
									$("#adresse").val('');
									$("#zone").val('');
									btn.text('Ajouter');
								},
								error: function(jqXHR, textStatus,
									errorThrown) {
									console.log(textStatus);
								}
							});
							$("#main-content").load(
								"./page/pharmacie.html");
						}
					});
				});


		});
