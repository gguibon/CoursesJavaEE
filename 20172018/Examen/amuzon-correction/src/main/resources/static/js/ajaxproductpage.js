			
		

		function creaComment(json) {
			var panel = jQuery('<div></div>').attr('class', 'panel panel-default').attr('id', json.comment_id);
			var body = jQuery('<div></div>').attr('class', 'panel-body');
			var footer = jQuery('<div></div>').attr('class', 'panel-footer');
			var p = jQuery('<p></p>').text(json.text);
			var small = jQuery('<small class="text-muted">Posted by '+json.comment_author+' on '+json.comment_date+'</small>');
			var btnDelete = jQuery('<button class="btn btn-danger" onclick="removeComment(\''+json.comment_id+'\')">del</button>');
			body.append(p);
			footer.append(small);
			footer.append(btnDelete);
			panel.append(body);
			panel.append(footer);
			return panel;
		}
		
		function getComments() {

			jQuery.ajax({
				type : 'GET',
				url : 'comments',
				data : {
					action : "lookup",
					product : $("#productId").text()
				},
				success : function(result) {
					console.log("getComments", result);

					for (i = result.comments.length; i--;) {
						console.log(i, creaComment(result.comments[i]));
						$('#comments').append(
								creaComment(result.comments[i]));
					}
				},
				error : function() {
					console.log('oops !');
				}
			});

		}

		function addComment() {
			
			
			jQuery.ajax({
				type : 'GET',
				url : 'comments',
				data : {
					action : "add",
					product : $("#productId").text(),
					text : $("#commentinput").val()
				},
				success : function(result) {
					console.log(result);
					var comment = creaComment(result.comment)
					$("#comments").prepend(comment);
				},
				error : function() {
					$("#commentinput").text("");
					console.log('oops !');
				}
			});
			return true;
		}
		
		function removeComment(id) {

			jQuery.ajax({
				type : 'GET',
				url : 'comments',
				data : {
					action : "remove",
					id : id,
					product : $("#productId").text()
				},
				success : function(result) {
					$('#'+id).hide(300, function(){ $('#'+id).remove(); });
				},
				error : function() {
					console.log('oops !');
				}
			});
			return true;
		}
		
		function quit() {

			jQuery.ajax({
				type : 'GET',
				url : 'products',
				data : {
					action : "quit"
				},
				success : function(result) {
					$('body').empty();
				},
				error : function() {
					console.log('oops !');
				}
			});
			return true;
		}

		getComments();
		
		
		