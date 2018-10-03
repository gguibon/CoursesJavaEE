<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />

<title>Amuzon</title>

<!-- Bootstrap core CSS -->
<!-- <link href="css/bootstrap.min.css" rel="stylesheet" /> -->
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />

<!-- Custom styles for this template -->
<link href="/css/shop-homepage.css" rel="stylesheet" />

<link href="/css/animate.css" rel="stylesheet" />
<link href="/css/font-awesome.min.css" rel="stylesheet" />

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/">Amuzon</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><button class="btn btn-danger">Quit</button></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="col-lg-3">

				<h1 class="my-4">Amuzon</h1>
				<div class="list-group">
					<form id="allfilter"
						action="${pageContext.request.contextPath}/category" method="POST">
						<input type="hidden" value="[VALEUR_DU_PARAMETRE]" name="[NOM_DU_PARAMETRE]" /> <a
							class="list-group-item" href="javascript:{}"
							onclick="document.getElementById('allfilter').submit(); return false;">ALL</a>
					</form>
					<form id="computerfilter"
						action="${pageContext.request.contextPath}/category" method="POST">
						<input type="hidden" value="[VALEUR_DU_PARAMETRE]" name="[NOM_DU_PARAMETRE]" /> <a
							class="list-group-item" href="javascript:{}"
							onclick="document.getElementById('computerfilter').submit(); return false;">Computer</a>
					</form>
					<form id="clothesfilter"
						action="${pageContext.request.contextPath}/category" method="POST">
						<input type="hidden" value="[VALEUR_DU_PARAMETRE]" name="[NOM_DU_PARAMETRE]" /> <a
							class="list-group-item" href="javascript:{}"
							onclick="document.getElementById('clothesfilter').submit(); return false;">Clothes</a>
					</form>
					<form id="moviesfilter"
						action="${pageContext.request.contextPath}/category" method="POST">
						<input type="hidden" value="[VALEUR_DU_PARAMETRE]" name="[NOM_DU_PARAMETRE]" /> <a
							class="list-group-item" href="javascript:{}"
							onclick="document.getElementById('moviesfilter').submit(); return false;">Movies</a>
					</form>
				</div>

			</div>
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">

				<div class="my-4 row">
					<!-- version pour générer un produit arbitraire, conservée pour l'example
					<form action="${pageContext.request.contextPath}/add" method="POST">
						<button id="btnAddProduct" type="submit" class="btn btn-success">
							<i class="fa fa-plus" aria-hidden="true"></i> Add Product
						</button>
					</form> -->

					<button id="btnAddProduct" type="button" class="btn btn-success"
						data-toggle="modal" data-target="#myModal">
						<i class="fa fa-plus" aria-hidden="true"></i> Add Product
					</button>
				</div>



				<div id="myModal" class="modal fade" role="dialog">
					<div class="modal-dialog">

						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add a product</h4>
							</div>
							<div class="modal-body">
								<form id="addproductform"
									action="${pageContext.request.contextPath}" method="[METHODE_HTTP]"> <!-- Choisir l'url adéquate et la méthode http voulue -->
									<div class="form-group">
										<label for="titleinput">Title:</label> <input type="text"
											class="form-control" id="titleinput" name="[NOM_DU_PRARAMETRE]"></input>
									</div>
									<div class="form-group">
										<label for="descriptioninput">Description:</label> <input
											type="text" class="form-control" id="descriptioninput"
											name="[NOM_DU_PARAMETRE]"></input>
									</div>

									<div class="form-group">
										<label for="categoryselect">Category:</label> <select
											class="form-control" id="categoryselect" name="[NOM_DU_PARAMETRE]">
											<option>computer</option>
											<option>clothes</option>
											<option>movies</option>
										</select>
									</div>
								</form>
							</div>

							<div class="modal-footer">
								<!--  bouton avec un javascript pour submit le formulaire à distance -->
								<button type="button" class="btn btn-primary"
									data-dismiss="modal"
									onclick="document.getElementById('addproductform').submit(); return false;">Confirm</button>
							</div>
						</div>

					</div>
				</div>


				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div id="carousel" class="carousel-inner" role="listbox"></div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>

				<div id="previews" class="row">

					<!--  cette div est un exemple . Il faut en faire une pour chaque produit par JSTL. -->
					<div class="col-lg-4 col-md-6 mb-4" id="[ID_DU_PRODUIT]">
						<div class="card h-100">
							<a href="#"><img class="card-img-top"
								src="http://placehold.it/700x400" alt=""></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#">[TITRE_DU_PRODUIT]</a>
								</h4>
								<p class="card-text">Type : [CATEGORIE_DU_PRODUIT]</p>
								<p class="card-text">[DESCRIPTION_DU_PRODUIT]</p>
							</div>
							<div class="card-footer">
								<form action="${pageContext.request.contextPath}" <!-- URL à choisir en fonction -->
									method="POST">
									<input type="hidden" value="[ID_DU_PRODUIT]" name="productId" />
									<button name="buttonRemove" type="submit"
										class="btn btn-danger btn-xs">
										<i class="fa fa-trash fa-lg" aria-hidden="true"></i>
									</button>

								</form>
							</div>
						</div>
					</div>
					
					
				</div>

			</div>
			<!-- /.col-lg-9 -->

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->


	<!-- appel manuel des dependances js -->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.bundle.min.js"></script>

	<!-- Si vous voulez utiliser les webjars (mais desfois cela fonctionne bizarrement : par exemple le modal 
voici le lien : https://www.webjars.org/
-->
	<!-- <script type="text/javascript"
		src="webjars/jquery/3.3.1-1/js/jquery.min.js"></script>
		<script type="text/javascript"
		src="webjars/popper-js/1.14.4/js/popper.min.js"></script>
	 <script type="text/javascript"
		src="webjars/bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
		
		<script type="text/javascript" src="webjars/font-awesome/5.3.1/js/all.min.js"></script> -->


</body>

</html>
