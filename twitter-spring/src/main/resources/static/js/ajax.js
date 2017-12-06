
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
				
//				var btnLike = jQuery('<button name="buttonLike" type="button" class="btn btn-default btn-xs"><i class="fa fa-thumbs-o-up fa-lg" aria-hidden="true"></i></button>');
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
					/*
					 * console.log(result.messages); var messageCard =
					 * tmplMessage(result.messages[result.messages.length-1]);
					 */
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
		
		