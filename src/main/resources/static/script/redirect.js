$('a').removeClass('active');
$('a:contains(Statistiques)').addClass('active');
$("#main-content").load("page/statistiques.html");

function show(page) {
	if (page == 'ville') {
		$('a').removeClass('active');
		$('a:contains(Villes)').addClass('active');
		$("#main-content").load("page/ville.html");
		event.preventDefault();
	}
	if (page == "zone") {
		$('a').removeClass('active');
		$('a:contains(Zones)').addClass('active');
		$("#main-content").load("page/zone.html");
		event.preventDefault();
	}
	if (page == "pharmacie") {
		$('a').removeClass('active');
		$('a:contains(Pharmacies)').addClass('active');
		$("#main-content").load("page/pharmacie.html");
		event.preventDefault();
	}
	if (page == "statistiques") {
		$('a').removeClass('active');
		$('a:contains(Statistiques)').addClass('active');
		$("#main-content").load("page/statistiques.html");
		event.preventDefault();
	}
	if (page == "garde") {
		$('a').removeClass('active');
		$('a:contains(Gardes)').addClass('active');
		$("#main-content").load("page/pharmaciegarde.html");
		event.preventDefault();
	}
	if (page == "user") {
		$('a').removeClass('active');
		$('a:contains(Pharmacien)').addClass('active');
		$("#main-content").load("page/user.html");
		event.preventDefault();
	}
}
