<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html>

<head>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>YukAMU</title>

<link href="/css/bootstrap.min.css" rel="stylesheet" />
<link href="/css/shop-homepage.css" rel="stylesheet" />
<link href="/css/animate.css" rel="stylesheet" />
<link href="/css/all.min.css" rel="stylesheet" />

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-success fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/">YukAMU</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<ul class="navbar-nav">
			    <li class="nav-item">
			      <a class="nav-link" href="${pageContext.request.contextPath}/food/favorites"> <i class="fas fa-star" ></i> Favorites</a>
			    </li>
			 </ul>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<a href="${pageContext.request.contextPath}/shutdown" class="btn btn-danger" >Quit</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-md-6">
				<form id="typeselectform" action="${pageContext.request.contextPath}TODO : remplir" method="TODO : remplir">
					<div class="form-group">
						<label for="categoryselect">Filter by Food Type:</label> 
						<select	class="form-control" id="typeselect" name="TODO : remplir" onchange="this.form.submit()">
							<option>Select a type</option>
							<option>ALL</option>
							<!-- TODO : ajouter une option par type -->
						</select>
					</div>
				</form>
				<div id="previews" class="row">
					<div class="col-12">
							
						<ul id="foodlistview" class="list-group">
							<!-- TODO : repeter cette balise <li> pour chaque objet Food -->
								<li class="list-group-item" id="TODO : remplir">
									<h4 class="card-title">	TODO : remplir </h4>
									<p class="card-text">TODO : remplir avec la marque (brand)</p>
									<p class="card-text">TODO : remplir avec la qualité</p>
									<div class="row">
										<div class="col">
											<form action="${pageContext.request.contextPath}TODO : remplir" method="TODO : remplir">
												<input type="hidden" value="${food.id}" name="TODO : remplir" />
												<button name="buttonRemove" type="submit" class="btn btn-danger btn-xs" >
													<i class="fa fa-trash" aria-hidden="true"></i>
												</button>
											</form>
										</div>
										<div class="col">
											<form action="${pageContext.request.contextPath}TODO : remplir" method="TODO : remplir">
												<input type="hidden" value="${food.id}" name="TODO : remplir" />
												<button name="buttonFav" type="submit" class="btn btn-warning btn-xs" >
													<!-- Aspect de l'etoile selon si l'element est favori ou non -->
													<!-- etoile remplie --><i class="fas fa-star star" aria-hidden="true" ></i>
													<!--  etoile vide --><i class="far fa-star star" aria-hidden="true" ></i>
												</button>
											</form>
										</div>
									</div>
								</li>
							
						</ul>
					</div>
				</div>
				<button id="btnAddFood" type="button" class="btn btn-success"
					data-toggle="modal" data-target="#myModal">
					<i class="fa fa-plus" aria-hidden="true"></i> Add
				</button>

			</div>
			<!-- /.col-lg-3 -->

			<div class="col-md-6">

				<div class="row">		
					<canvas id="myChart" width="50" height="50"></canvas>			
				</div>



				<div id="myModal" class="modal fade" role="dialog">
					<div class="modal-dialog">

						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">Insert New Food</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>	
							</div>
							<div class="modal-body">
								<form id="addfoodform" action="${pageContext.request.contextPath}TODO : remplir" method="TODO : remplir">
									<div class="form-group">
										<label for="nameinput">Name:</label> <input type="text"
											class="form-control" id="nameinput" name="TODO : remplir"></input>
									</div>
									<div class="form-group">
										<label for="brandinput">Brand:</label> <input
											type="text" class="form-control" id="brandinput" name="TODO : remplir"></input>
									</div>
	
									<div class="form-group">
										<label for="typeselect">Type:</label> <select
											class="form-control" id="typeselect" name="TODO : remplir">
											<!-- TODO : balises <option></option> en fonction des types possibles -->
										</select>
									</div>
									
									<div class="form-group">
										<label for="qualityselect">Quality:</label> <select
											class="form-control" id="qualityselect" name="TODO : remplir">
											<!-- TODO : balises <option></option> en fonction des qualités possibles -->
										</select>
									</div>
								</form>
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									data-dismiss="modal" onclick="document.getElementById('addfoodform').submit(); return false;">Confirm</button>
							</div>
						</div>

					</div>
				</div>

				

			</div>
			<!-- /.col-lg-9 -->

		</div>
		<!-- /.row -->

		<input type="hidden" id="foodcount4chart" value='${qualitycounts}' />
		
	</div>
	<!-- /.container -->

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>	
<script src="/js/chart.bundle.min.js"></script>
<script src="/js/all.min.js"></script>
<script>
var ctx = document.getElementById("myChart").getContext('2d');
var foodcount = document.getElementById("foodcount4chart").value;
console.log('qualitycounts', foodcount);
if(foodcount){
	console.log('qualitycounts found', foodcount);
	foodcount = JSON.parse(foodcount);
	var myChart = new Chart(ctx, {
	    type: 'pie',
	    data: {
	        labels: ["Excellent", "Good", "Mediocre", "Bad"],
	        datasets: [{
	            label: '# of Votes',
	            data: [foodcount.EXCELLENT, foodcount.GOOD, foodcount.MEDIOCRE, foodcount.BAD],
	            backgroundColor: [
	                'rgba(0, 255, 0, 1)',
	                'rgba(0, 153, 51, 1)',
	                'rgba(255, 102, 0, 1)',
	                'rgba(204, 0, 0, 1)'
	            ],
	            borderColor: [
	            	'rgba(0, 255, 0, 1)',
	                'rgba(0, 153, 51, 1)',
	                'rgba(255, 102, 0, 1)',
	                'rgba(204, 0, 0, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },options: {
	    	responsive: true
	    }
	});
}else{
	console.log("Il manque les données de nombre par qualité pour le graphique !");
}
</script>

</body>

</html>
