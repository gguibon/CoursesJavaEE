<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Birdy</title>


<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />

<link href="${pageContext.request.contextPath}/css/blog-home.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/animate.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet" />

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Birdy</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><button class="btn btn-danger"
							onclick="quit();">Quit</button></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<!-- Blog Entries Column -->
			<div class="col-md-12">

				<h1 class="my-4">Login</h1>




				<label for="identifiant">Name:</label> <input type="text"
					class="form-control" id="id"> <label for="pwd">Password:</label>
				<input type="password" class="form-control" id="pwd">

				<button type="button" class="btn btn-default" onclick="login();">Submit</button>


			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->







	<!-- Bootstrap core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/popper/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>

	<script>
		function login2(){
			
		}
	
		function login() {
			jQuery.ajax({
				type : 'GET',
				url : '/login',
				data : {
					id : $('#id').val(),
					pwd : $('#pwd').val()
				},
				success : function(result) {
					console.log(result);
					location.reload();

				},
				error : function() {
					console.log('oops !');
				}
			});
		}
		
		function quit() {

			jQuery.ajax({
				type : 'GET',
				url : '/api', 
				data : { 
					button : "quit"
				},
				success : function(result) {
					$('body').empty();
				},
				error : function(){
					$('body').empty();
					console.log('oops !');
				}
			});
		}
	</script>

</body>

</html>
