$('a').removeClass('active');
$('a:contains(Statistiques)').addClass('active');
$("#main-content").load("page/statistiques.html");

function show(page) {
	if (page == "pharmacie") {
		$('a').removeClass('active');
		$('a:contains(Pharmacies)').addClass('active');
		$("#main-content").load("page/th-pharmacie.html");
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
		$("#main-content").load("page/th-pharmaciegarde.html");
		event.preventDefault();
	}
	if (page == "user") {
		$('a').removeClass('active');
		$('a:contains(Pharmacien)').addClass('active');
		$("#main-content").load("page/th-user.html");
		event.preventDefault();
	}
}
