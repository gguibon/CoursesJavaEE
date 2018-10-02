<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
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
					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="/lorem">Lorem</a></li>
					<li class="nav-item"><button class="btn btn-danger" onclick="quit();">Quit</button></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<!-- Blog Entries Column -->
			<div class="col-md-12">

				<h1 class="my-4">
					Derniers messages
				</h1>


				
				
				<div id="messageView" class="messageview">
				
				
				
				</div>
				
				
				<div id="inputpanel" class="card card-primary">
			      <div id="userinput" class="card-body" contenteditable>
			      </div>
			      <div class="card-footer">
			      	<button name="buttonAdd" type="button" onclick="addMessage($('#userinput').text());" class="btn btn-primary"><i class="fa fa-paper-plane fa-lg" aria-hidden="true"></i></button>
			      </div>
			    </div>
				
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
	<%-- <script src="${pageContext.request.contextPath}/vendor/jquerymy-1.2.11.min.js"></script> --%>

	<script>
	
		
		function tmplMessage(msg, user){
				whoLiked = []
				for(j in msg.likes){
					whoLiked.push(msg.likes[j].liike_author); 
				}
				
				var card = jQuery('<div></div>').attr("class", "card mb-4 animated bounceInDown").attr('id',msg.id);
				var cardbody = jQuery('<div></div>').attr('class','card-body');
				var cardtitle = jQuery('<h2></h2>').attr('class','card-title').text(msg.content);
				var cardtext = jQuery('<p></p>').attr('class','card-text').text("Par "+msg.author+", le "+ msg.date);
				cardbody.append(cardtitle); cardbody.append(cardtext); card.append(cardbody);
				var cardfooter = jQuery('<div></div>').attr('class', 'card-footer text-muted');
				
				var btnLike = jQuery('<button name="buttonLike" title="'+whoLiked.join()+'" type="button" onclick="like(\''+msg.id+'\')" class="btn btn-default btn-xs"><i class="fa fa-thumbs-o-up fa-lg" aria-hidden="true"></i></button>');
				if(whoLiked.includes(user)){btnLike.attr('class','btn btn-primary btn-xs');}
				
				var btnRemove = jQuery('<button name="buttonRemove" type="button" onclick="removeMessage(\''+msg.id+'\')" class="btn btn-danger btn-xs"><i class="fa fa-trash fa-lg" aria-hidden="true"></i></button>');
				
				btnGroup = jQuery('<div class="btn-group"></div>')
				btnGroup.append(btnLike);
				btnGroup.append(btnRemove);
				cardfooter.append(btnGroup);
				card.append(cardfooter);
				return card;
		}
	
		function getMessages() {
			jQuery.ajax({
				type: 'GET',
				url: 'api',
				data: {
					action : "lookup"
				},
				success: function(result){
					console.log("getMessages",result);
					
					
					for (i = result.messages.length; i--;) {
						$('#messageView').append(tmplMessage(result.messages[i], result.user) );
					}
				},
				error : function(){
					console.log('oops !');
				}
			});
		}
			
	
		getMessages();
		
		
		
		function updateView() {
			jQuery.ajax({
				type: 'GET',
				url: 'api',
				data: {
					action : "lookup"
				},
				success: function(result){
					
					var ids = $('#messageView').children().map(function(){
						  return $(this).attr('id');
					  }).get();
									
					
					var resultIds = [];
					for (i in result.messages){
						resultIds.push(result.messages[i].id);
					} 

					
					for (i = result.messages.length; i--;) {
						if(!ids.includes(result.messages[i].id)){
							$('#messageView').prepend(tmplMessage(result.messages[i], result.user) );
						}
					}
					
					for (i = ids.length; i--;) {
						if(!resultIds.includes(ids[i])){
							$('#'+ids[i]).remove();
						}
					}

				},
				error : function(){
					console.log('oops !');
				}
			});
		}
			
		setInterval(function(){  updateView(); }, 200);
		
		function addMessage(content) {

			jQuery.ajax({
				type : 'GET',
				url : 'api', 
				data : { 
					action : "Add Message",
					content : content
				},
				success : function(result) {
					/* console.log(result.messages);
					var messageCard = tmplMessage(result.messages[result.messages.length-1]); */
					console.log(result);
					var messageCard = tmplMessage(result.message);
					$("#messageView").prepend(messageCard);
			    	$('#userinput').text(''); 
				},
				error : function(){
					console.log('oops !');
					$('#userinput').text(''); 
				}
			});
			return true;
		}
		
		
		function removeMessage(id) {

			jQuery.ajax({
				type : 'GET',
				url : 'api', 
				data : { 
					action : "removeMessage",
					id : id
				},
				success : function(result) {
					$('#'+id).hide(300, function(){ $('#'+id).remove(); });
				},
				error : function(){
					console.log('oops !');
				}
			});
		}
		
		function like(id) {

			jQuery.ajax({
				type : 'GET',
				url : 'api', 
				data : { 
					action : "like",
					id : id
				},
				success : function(result) {
					console.log(result.messages);
					$('#'+id+' [name=buttonLike]').toggleClass('btn-default btn-primary');
				},
				error : function(){
					console.log('oops !'); 
				}
			});
			return true;
		}
		
		function quit() {

			jQuery.ajax({
				type : 'GET',
				url : 'api', 
				data : { 
					action : "quit"
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
		
		
		var textarea = document.getElementById("userinput");

		textarea.addEventListener("paste", function() {
		    
		    if (this.value !== "")
		    {
		        this.value = this.value + "\n";
		    }

		}, false);
		
		
		
		
		var objDiv = document.getElementById("messageView");
		objDiv.scrollTop = objDiv.scrollHeight;
		
		$('#userinput').keypress(function(e){
		      if(e.keyCode==13){
		    	   addMessage($('#userinput').text());
		      }
		    });
		
		$('#userinput').focus();
		
		
		
		
	</script>

</body>

</html>
