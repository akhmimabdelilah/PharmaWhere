var map = L.map('map');
map.setView([51.505, -0.09], 13);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	attribution: 'PharmaWhere',
	maxZoom: 18
}).addTo(map);
var username = username||"t";
$(document)
	.ready(
		function () {



			// Add a marker to the map
			var marker = L.marker([51.5, -0.09])
			map.on('click', function (e) {
				marker.setLatLng([e.latlng.lat, e.latlng.lng]);
				$("#lat").val(e.latlng.lat);
				$("#log").val(e.latlng.lng);
			});
			marker.addTo(map);
			async function user() {
				await $.ajax({
					url: '/username',
					type: 'GET',
					success: function (data) {
						username = data;
					},
					error: function (jqXHR, textStatus,
						errorThrown) {
						console.log(textStatus);
					}
				});
			}
			user().then(function () {

				table = $('#tpharmacie')
					.DataTable({
						ajax: {
							url: "user/" + username + "/pharmacies",
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

			})
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
						alert(i)
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


			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function (position) {
					marker.setLatLng([position.coords.latitude, position.coords.longitude]);
				});
			}
			$('#btn').click(
				function () {
					var nom = $("#nom");
					var lat = $("#lat");
					var log = $("#log");
					var adresse = $("#adresse");
					var zone = $("#zone");

					if ($('#btn').text() == 'Ajouter') {
						var formData = new FormData();
						formData.append('image', $('#image')[0].files[0]);
						var p = {
							nom: nom.val(),
							lat: lat.val(),
							log: log.val(),
							adresse: adresse.val(),
							zone: {
								id: zone.val()
							}
						};
						formData.append('nom', p.nom);
						formData.append('lat', p.lat);
						formData.append('log', p.log);
						formData.append('adresse', p.adresse);
						formData.append('zone', p.zone.id);
						formData.append('user', username);
						$.ajax({
							url: 'pharmacie/',
							data: formData,
							contentType: false,
							processData: false,
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
							"./page/th-pharmacie.html");
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
						newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment supprimer cette pharmacie ? </h4>';
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
											url: 'pharmacie/'
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
					var id = $(this).closest('tr').find('td').eq(0)
						.text();

					var nom = $(this).closest('tr').find('td').eq(
						2).text();
					var lat = $(this).closest('tr').find('td')
						.eq(4).text();
					var log = $(this).closest('tr').find('td')
						.eq(5).text();
					var adresse = $(this).closest('tr').find('td')
						.eq(6).text();
					var ville = $(this).closest('tr').find('td')
						.eq(7).text();
					var zone = $(this).closest('tr').find('td')
						.eq(8).text();


					btn.text('Modifier');
					$("#nom").val(nom);
					$("#lat").val(lat);
					$("#log").val(log);
					marker.setLatLng([+lat, +lng]);
					map.flyTo([+lat, +lng], 12);
					$("#adresse").val(adresse);

					var op = $('#ville option').filter(function () { return $(this).html() == ville; }).val();
					$("#ville").val(op);
					await loadZones(op);

					var opi = $('#zone option').filter(function () { return $(this).html() == zone; }).val();
					$("#zone").val(opi);

					$("#id").val(id);

					btn.click(function (e) {
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
								success: function (data,
									textStatus, jqXHR) {
									table.ajax.reload();
									$("#nom").val('');
									$("#lat").val('');
									$("#log").val('');
									$("#adresse").val('');
									$("#zone").val('');
									btn.text('Ajouter');
								},
								error: function (jqXHR, textStatus,
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
