$(document)
	.ready(
		function () {

			table = $('#tpharmaciegarde')
				.DataTable({
					ajax: {
						url: "pharmaciegarde/",
						dataSrc: ''
					},
					columns: [
						{
							data: "pharmacie.nom"
						},
						{
							data: "garde.type"
						},
						{
							data: "pharmacie.zone.ville.nom"
						},
						{
							data: "pharmacie.zone.nom"
						},
						{
							data: "dateDebut"
						},
						{
							data: "dateFin"
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

			$.ajax({
				url: '/garde/',
				type: 'GET',
				success: function (data) {
					var option = '';
					data.forEach(e => {
						option += '<option value =' + e.id + '>' + e.type + '</option>';
					});

					$('#garde').append(option);
				},
				error: function (jqXHR, textStatus,
					errorThrown) {
					console.log(textStatus);
				}

			});

			$.ajax({
				url: '/ville/',
				type: 'GET',
				success: function (data) {
					var option = '';
					data.forEach(e => {
						option += '<option value =' + e.id + '>' + e.nom + '</option>';
					});
					$('#ville').append(option);
				},
				error: function (jqXHR, textStatus,
					errorThrown) {
					console.log(textStatus);
				}

			});



			async function loadZones(i) {
				await $.ajax({
					url: '/ville/' + i + "/zones",
					type: 'GET',
					async: true,
					success: function (data) {
						var option = '<option value="0">choisir une option</option>';
						data.forEach(e => {
							option += '<option value =' + e.id + '>' + e.nom + '</option>';
						});
						$('#zone').html(option);
					},
					error: function (jqXHR, textStatus,
						errorThrown) {
						console.log(textStatus);
					}

				});
			}

			$("#ville").on("change", function () {
				loadZones($("#ville").val());
			})

			async function loadPharmacies(i) {
				await $.ajax({
					url: '/zone/' + i + "/pharmacies",
					type: 'GET',
					async: true,
					success: function (data) {
						var option = '<option value="0">choisir une option</option>';
						data.forEach(e => {
							option += '<option value =' + e.id + '>' + e.nom + '</option>';
						});
						$('#pharmacie').html(option);
					},
					error: function (jqXHR, textStatus,
						errorThrown) {
						console.log(textStatus);
					}

				});
			}
			$("#zone").on("change", function () {
				loadPharmacies($("#zone").val());
			})

			$('#btn').click(
				function () {
					var dateFin = $("#dateFin");
					var dateDebut = $("#dateDebut");
					var garde = $("#garde");
					var pharmacie = $("#pharmacie");
					alert(dateFin.val())
					if ($('#btn').text() == 'Ajouter') {
						alert(dateFin.val())
						var p = {
							pk: {
								pharmacieId: pharmacie.val(),
								dateDebut: dateDebut.val(),
								gardeId: garde.val()
							},
							pharmacie: { id: pharmacie.val() },
							dateDebut: dateDebut.val(),
							dateFin: dateFin.val(),
							garde: {
								id: garde.val()
							}
						};

						$.ajax({
							url: 'pharmaciegarde/',
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
							"./page/pharmaciegarde.html");
					}
				});

			$('#table-content')
				.on(
					'click',
					'.supprimer',
					function () {

						var pharmacie = $(this).closest('tr').find(
							'td').eq(0).text();
						var garde = $(this).closest('tr').find(
							'td').eq(1).text();
						var dateDebut = $(this).closest('tr').find(
							'td').eq(2).text();


						var opp = $('#pharmacie option').filter(function () { return $(this).html() == pharmacie; }).val();
						var opg = $('#garde option').filter(function () { return $(this).html() == garde; }).val();

						alert(opp)
						alert(opg)
						var oldLing = $(this).closest('tr')
							.clone();
						var newLigne = '<tr style="position: relative;" class="bg-light" ><th scope="row">'
							+ opp + ' ' + opg
							+ '</th><td colspan="4" style="height: 100%;">';
						newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment supprimer cette garde ? </h4>';
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
											url: 'pharmaciegarde/one',
											data: JSON.stringify({
												pharmacieId: opp,
												gardeId: opg,
												dateDebut: dateDebut
											}),
											type: 'DELETE',
											async: false,
											success: function (
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
				async function () {
					var btn = $('#btn');

					var dateFin = $(this).closest('tr').find('td').eq(5).text();
					var dateDebut = $(this).closest('tr').find('td')
						.eq(4).text();
					var ville = $(this).closest('tr').find('td')
						.eq(2).text();
					var zone = $(this).closest('tr').find('td')
						.eq(3).text();
					var garde = $(this).closest('tr').find('td')
						.eq(1).text();
					var pharmacie = $(this).closest('tr').find('td')
						.eq(0).text();


					btn.text('Modifier');
					$("#dateDebut").val(dateDebut);
					$("#dateFin").val(dateFin);


					var op = $('#ville option').filter(function () { return $(this).html() == ville; }).val();
					$("#ville").val(op);

					await loadZones(op);

					var opi = $('#zone option').filter(function () { return $(this).html() == zone; }).val();
					$("#zone").val(opi);

					await loadPharmacies(opi);

					var op = $('#pharmacie option').filter(function () { return $(this).html() == pharmacie; }).val();
					$("#pharmacie").val(op);

					var op = $('#garde option').filter(function () { return $(this).html() == garde; }).val();
					$("#garde").val(op);

					btn.click(function (e) {
						e.preventDefault();
						var p = {
							pk: {
								pharmacieId: $("#pharmacie").val(),
								dateDebut: $("#dateDebut").val(),
								gardeId: $("#garde").val()
							},
							pharmacie: { id: $("#pharmacie").val() },
							dateDebut: $("#dateDebut").val(),
							dateFin: $("#dateFin").val(),
							garde: {
								id: $("#garde").val()
							}

						};
						if ($('#btn').text() == 'Modifier') {
							$.ajax({
								url: 'pharmaciegarde/',
								contentType: "application/json",
								dataType: "json",
								data: JSON.stringify(p),
								type: 'POST',
								async: false,
								success: function (data,
									textStatus, jqXHR) {
									table.ajax.reload();
									$("#dateFin").val('');
									$("#dateDebut").val('');
									$("#garde").val('');
									$("#pharmacie").val('');
									btn.text('Ajouter');
								},
								error: function (jqXHR, textStatus,
									errorThrown) {
									console.log(textStatus);
								}
							});
							$("#main-content").load(
								"./page/pharmaciegarde.html");
						}
					});
				});


		});
