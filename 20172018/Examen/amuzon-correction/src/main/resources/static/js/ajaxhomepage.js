$('.list-group-item').on('click', function() {
	var $this = $(this);
	var $alias = $this.data('alias');

	$('.active').removeClass('active');
	$this.toggleClass('active');
})

function creaCardProduct(json) {
	var div = jQuery('<div></div>').attr("class", "col-lg-4 col-md-6 mb-4")
			.attr("id", json.id);
	var card = jQuery("<div></div>").attr("class", "card h-100");
	var pic = jQuery('<a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""/></a>');
	var cardBody = jQuery('<div class="card-body"><h4 class="card-title"><a href="/productpage?item='+json.id+'">'
			+ json.productTitle
			+ '</a> </h4><p class="card-text">'
			+ json.description + '</p> </div>');
	var cardFooter = jQuery('<div class="card-footer"><small class="text-muted">'+json.comments+' comments</small></div>');
	var btnRemove = jQuery('<button name="buttonRemove" type="button" onclick="removeProduct(\''
			+ json.id
			+ '\')" class="btn btn-danger btn-xs"><i class="fa fa-trash fa-lg" aria-hidden="true"></i></button>');
	cardFooter.append(btnRemove);
	card.append(pic);
	card.append(cardBody);
	card.append(cardFooter);
	div.append(card);
	return div;
}


function getCategory(element) {
	jQuery.ajax({
		type : 'GET',
		url : 'products',
		data : {
			action : "lookup",
			category : element.text()
		},
		success : function(result) {
			$('#previews').empty();
			console.log("getCategory", result);

			for (i = result.products.length; i--;) {
				console.log(i, creaCardProduct(result.products[i]));
				$('#previews').append(creaCardProduct(result.products[i]));
			}
		},
		error : function() {
			console.log('oops !');
		}
	});
}

function getPreviews() {

	jQuery.ajax({
		type : 'GET',
		url : 'products',
		data : {
			action : "lookup"
		},
		success : function(result) {
			$('#previews').empty();
			console.log("getPreviews", result);

			for (i = result.products.length; i--;) {
				console.log(i, creaCardProduct(result.products[i]));
				$('#previews').append(creaCardProduct(result.products[i]));
			}
		},
		error : function() {
			console.log('oops !');
		}
	});

}

function addProduct() {

	jQuery.ajax({
		type : 'GET',
		url : 'products',
		data : {
			action : "add",
			productTitle : $("#titleinput").val(),
			description : $("#descriptioninput").val(),
			category : $("#categoryselect").find("option:selected").text()
		},
		success : function(result) {
			console.log(result);
			var productCard = creaCardProduct(result.product)
			$("#previews").prepend(productCard);
		},
		error : function() {
			console.log('oops !');
		}
	});
	return true;
}

function removeProduct(id) {

	jQuery.ajax({
		type : 'GET',
		url : 'products',
		data : {
			action : "remove",
			id : id
		},
		success : function(result) {
			$('#' + id).hide(300, function() {
				$('#' + id).remove();
			});
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

getPreviews();

