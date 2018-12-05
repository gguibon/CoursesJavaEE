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
			      <a class="nav-link" href="${pageContext.request.contextPath}/"> <i class="fas fa-home"></i> Home</a>
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

			<div class="col-md-12">
				<div id="previews" class="row">
						<!-- TODO : repeter cette balise <li> pour chaque objet Food -->
							<div class="card col-md-4" id="TODO : remplir">
								<h4 class="card-title">	TODO : remplir </h4>
								<p class="card-text">TODO : remplir avec la marque (brand)</p>
								<p class="card-text">TODO : remplir avec la qualité</p>
							</div>
						
				</div>

			</div>

		</div>
		<!-- /.row -->

		
	</div>
	<!-- /.container -->

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.bundle.min.js"></script>	
<script src="/js/all.min.js"></script>

</body>

</html>
