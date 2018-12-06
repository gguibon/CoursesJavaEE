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

<title>YukAMU - Tagger</title>

<link href="/css/bootstrap.min.css" rel="stylesheet" />
<link href="/css/shop-homepage.css" rel="stylesheet" />
<link href="/css/animate.css" rel="stylesheet" />
<link href="/css/all.min.css" rel="stylesheet" />

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-success fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/">YukAMU - Tagger</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<ul class="navbar-nav">
			    <li class="nav-item">
			      <a class="nav-link" href="${pageContext.request.contextPath}/progress"> <i class="fas fa-list-ul"></i> Progress Details</a>
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
				
				<div id="previews" class="row">
					<div class="col-12">
						<form id="formdonefilter" action="${pageContext.request.contextPath}TODO : remplir" method="TODO : remplir">
						  <div class="custom-control custom-checkbox custom-control-inline">
						    <input type="checkbox" class="custom-control-input" id="todoCheck" name="TODO : remplir">
						    <label class="custom-control-label" for="todoCheck">TODO</label>
						  </div>
						  <div class="custom-control custom-checkbox custom-control-inline">
						  	<input type="checkbox" class="custom-control-input" id="doneCheck" name="TODO : remplir">
						    <label class="custom-control-label" for="doneCheck">DONE</label>
						  </div>
						  <button name="checkbutton" type="submit" class="btn btn-primary btn-xs">Filter <i class="fas fa-filter"></i></button>
						</form>
						<ul id="foodlistview" class="list-group">
							<!-- TODO : repeter cette balise <li> pour chaque objet Food -->
								<li class="list-group-item" id="TODO : remplir">
									<div class="media border p-3">
										<img src="TODO : remplir" alt="imgurl" class="mr-3 mt-3" style="width:60px;"/>
										<div class="media-body">
											<h4 class="card-title">	TODO : remplir avec le nom (name) </h4>
											<div class="row">
												<div class="col">
													<i class="fas fa-circle fa-2x TODO : remplir avec le tag"></i>
													TODO : remplir avec le tag + tag comme nom de classe juste au dessus
												</div>
												<div class="col">
													<form id="tagselectform" action="${pageContext.request.contextPath}TODO : remplir" method="TODO : remplir">
														<input type="hidden" value="TODO : remplir" name="TODO : remplir" />
														<div class="form-group">
															<label for="tagselect">Nutritional Quality:</label> 
															<select	class="form-control" id="tagselect" name="TODO : remplir" onchange="this.form.submit()">
																<option>Select a tag</option>
																<!-- TODO : ajouter une option par tag -->
															</select>
														</div>
													</form>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<form action="${pageContext.request.contextPath}TODO : remplir" method="TODO : remplir">
														<input type="hidden" value="TODO : remplir" name="TODO : remplir" />
														<button name="buttonRemove" type="submit" class="btn btn-danger btn-xs" >
															<i class="fa fa-trash" aria-hidden="true"></i>
														</button>
													</form>
												</div>
												<div class="col">
													<form action="${pageContext.request.contextPath}TODO : remplir" method="TODO : remplir">
														<input type="hidden" value="TODO : remplir" name="TODO : remplir" />
														<!-- TODO : Aspect du bouton selon si l'element est fait ou reste a faire -->
														<!-- button si l'element est fait --><button name="buttonFav" type="submit" class="btn btn-success btn-xs" ><i class="fas fa-check"></i> DONE</button>
														<!--  bouton si l'element reste a faire --><button name="buttonFav" type="submit" class="btn btn-warning btn-xs" ><i class="fas fa-exclamation"></i> TODO</button>
													</form>
												</div>
											</div>
										</div>
									</div>
								</li>
							<!-- fin de la balise <li> a repeter -->
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
										<label for="imgurlinput">Image Url:</label> <input
											type="text" class="form-control" id="imgurlinput" name="TODO : remplir"></input>
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

		<input type="hidden" id="foodprogress4chart" value='${progresscounts}' />
		
	</div>
	<!-- /.container -->

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>	
<script src="/js/chart.bundle.min.js"></script>
<script src="/js/all.min.js"></script>
<script>
var ctx = document.getElementById("myChart").getContext('2d');
var progresscounts = document.getElementById("foodprogress4chart").value;
console.log('progresscounts', progresscounts);
if(progresscounts){
	console.log('progresscounts found', progresscounts);
	progresscounts = JSON.parse(progresscounts);
	var myChart = new Chart(ctx, {
	    type: 'doughnut',
	    data: {
	        labels: ["DONE", "TODO"],
	        datasets: [{
	            label: '# of Votes',
	            data: [progresscounts.DONE, progresscounts.TODO],
	            backgroundColor: [
	                'rgba(0, 255, 0, 1)',
	                'rgba(191, 191, 191, 1)'
	            ],
	            borderColor: [
	            	'rgba(0, 255, 0, 1)',
	                'rgba(191, 191, 191, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },options: {
	    	responsive: true
	    }
	});
}else{
	console.log("Il manque les donnees de nombre par articles faits et articles qu'ils restent a faire pour le graphique !");
}
</script>

</body>

</html>
