$(document).ready(function(){
	function countt(x, data){
		data = Number(data);
			let f = 0, data_ = data / 5;
			setTimeout(function(){
				f += data_;
				$(x).html(Math.round(f));
			}, 250);
			setTimeout(function(){
				f += data_;
				$(x).html(Math.round(f));
			}, 500);
			setTimeout(function(){
				f += data_;
				$(x).html(Math.round(f));
			}, 750);
			setTimeout(function(){
				f += data_;
				$(x).html(Math.round(f));
			}, 1000);
			setTimeout(function(){
				f += data_;
				$(x).html(Math.round(f));
			}, 1250);
	}
	
// # ===============================
// # = Nombre des produits
// # ===============================
	$.ajax({
		url : 'pharmacie/count',
		data : '',
		type : 'GET',
		success : function(data) {
			countt('#pharmacie',data);
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			console.log(textStatus);
		}
	});
	$.ajax({
		url : 'ville/count',
		data : '',
		type : 'GET',
		success : function(data) {
			countt('#ville', data);
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			console.log(textStatus);
		}
	});
	$.ajax({
		url : 'zone/count',
		data : '',
		type : 'GET',
		success : function(data) {
			countt('#zone', data);
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			console.log(textStatus);
		}
	});
	$.ajax({
		url : 'pharmacie/count/enattend',
		data : '',
		type : 'GET',
		success : function(data) {
			countt('#garde',data);
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			console.log(textStatus);
		}
	});
	$.ajax({
		url : 'user/count',
		data : '',
		type : 'GET',
		success : function(data) {
			countt('#user',data);
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			console.log(textStatus);
		}
	});
	

	
// # ===============================
// # = Nombre des machines achetées par année
// # ===============================
	$.ajax({
		url : 'zone/count/pharmacies',
		contentType : "application/json",
		dataType : "json",
		data : '',
		type : 'GET',
		async : false,
		success : function(data) {
			console.log(data);
			var labels = data.map(x=>x[0]);
			var dt = data.map(x=>x[1]);
			
			
				
			var ctx = document.getElementById('byYear').getContext('2d');
			var myChart = new Chart(ctx, {
			    type: 'line',
			    data: {
			        labels: labels,
			        datasets: [{
			            data: dt,
			            backgroundColor: [
			                'rgba(255, 99, 132, 0.2)',
			                'rgba(54, 162, 235, 0.2)',
			                'rgba(255, 206, 86, 0.2)',
			                'rgba(75, 192, 192, 0.2)',
			                'rgba(153, 102, 255, 0.2)',
			                'rgba(255, 159, 64, 0.2)'
			            ],
			            borderColor: [
			                'rgba(255, 99, 132, 1)',
			                'rgba(54, 162, 235, 1)',
			                'rgba(255, 206, 86, 1)',
			                'rgba(75, 192, 192, 1)',
			                'rgba(153, 102, 255, 1)',
			                'rgba(255, 159, 64, 1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			    	title: {
	                    display: true,
	                    text: 'Pharmacies de gardes',
	                    fontSize: 21,
	                    padding: 20,
	                    fontFamily: 'Calibri',
	                },	
	                legend: {
	                    display: false
	                },
			    	type: 'line',
			        scales: {
			           
	                yAxes: [{
		                	ticks: {
				                    beginAtZero: true
				            },	
		                    scaleLabel: {
		                      display: true,
		                      labelString: 'Nombre des pharmacies'
		                    }
	                }],
	                xAxes: [{
		                    scaleLabel: {
		                      display: true,
		                      labelString: 'Zones'
		                    }
		            }],
			        }
			    }
			});
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			console.log(textStatus);
		}
	});    

// # ===============================
// # = Nombre des machines par marque
// # ===============================
	$.ajax({
		url : 'ville/count/pharmacies',
		contentType : "application/json",
		dataType : "json",
		data : '',
		type : 'GET',
		async : false,
		success : function(data) {
			console.log(data);
			
			var labels = data.map(x=>x[0]);
			var dt = data.map(x=>x[1]);
				
			var ctx = document.getElementById('myChart').getContext('2d');
			var myChart = new Chart(ctx, {
			    type: 'bar',
			    data: {
			        labels: labels,
			        datasets: [{
			            data: dt,
			            backgroundColor: [
			                'rgba(255, 99, 132, 0.2)',
			                'rgba(54, 162, 235, 0.2)',
			                'rgba(255, 206, 86, 0.2)',
			                'rgba(75, 192, 192, 0.2)',
			                'rgba(153, 102, 255, 0.2)',
			                'rgba(255, 159, 64, 0.2)'
			            ],
			            borderColor: [
			                'rgba(255, 99, 132, 1)',
			                'rgba(54, 162, 235, 1)',
			                'rgba(255, 206, 86, 1)',
			                'rgba(75, 192, 192, 1)',
			                'rgba(153, 102, 255, 1)',
			                'rgba(255, 159, 64, 1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			    	title: {
	                    display: true,
	                    text: 'Nombre des pharmacies par ville',
	                    fontSize: 21,
	                    padding: 20,
	                    fontFamily: 'Calibri',
	                },	
	                legend: {
	                    display: false
	                },
			    	type: 'line',
			        scales: {
			           
	                yAxes: [{
		                	ticks: {
				                    beginAtZero: true
				            },	
		                    scaleLabel: {
		                      display: true,
		                      labelString: 'Nombre des pharmacies'
		                    }
	                }],
	                xAxes: [{
		                    scaleLabel: {
		                      display: true,
		                      labelString: 'Villes'
		                    }
		            }],
			        }
			    }
			});
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			console.log(textStatus);
		}
	});    
});
