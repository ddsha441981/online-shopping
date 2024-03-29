<div class="container">

<!-- when cart line update -->
<c:if test="${not empty message}">
	<div class="alert alert-info">
		<h3 class="text-center">
			${message}
		</h3>
	</div>
</c:if>

<!-- when cart is empty -->
<c:choose>
	<c:when test="${not empty cartLines}">
	
			<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:50%">Product</th>
							<th style="width:10%">Price</th>
							<th style="width:8%">Quantity</th>
							<th style="width:22%" class="text-center">Subtotal</th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach items="${cartLines}" var="cartLine">
					
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src="${images}/${cartLine.product.code}.jpg" alt="${cartLine.product.name}" class="img-responsive cartImg"/></div>
									<div class="col-sm-10">
										<h4 class="nomargin"> <span style="color: navy;"> <b>${cartLine.product.name}</b></span>
											<c:if test="${cartLine.available == false}">
												<strong class="unavailable">(Not Available!!!)</strong>
											</c:if>
										</h4>
										<p><span style="color: olive;"><b>Brand</b></span> - ${cartLine.product.brand}</p>
										<p><span style="color:olive;"><b>Description</b></span> - ${cartLine.product.description}</p>
									</div>
								</div>
							</td>
							<td data-th="Price"> &#8377; ${cartLine.buyingPrice}</td>
							<td data-th="Quantity">
								<input type="number" id="count_${cartLine.id}" min="1" max="3" class="form-control text-center" value="${cartLine.productCount}">
							</td>
							<td data-th="Subtotal" class="text-center"> &#8377; ${cartLine.total}</td>
							<td class="actions" data-th="">
								<button type="button" name="refreshCart" value="${cartLine.id}" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-refresh"></span></button>
								<a href="${contextRoot}/cart/${cartLine.id}/delete" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span></a>								
							</td>
						</tr>
					
					</c:forEach>
					
						
					</tbody>
					<tfoot>
						<tr class="visible-xs">
							<td class="text-center"><strong>Total &#8377; ${userModel.cart.grandTotal}</strong></td>
						</tr>
						<tr>
							<td><a href="${contextRoot}/show/all/products" class="btn btn-warning"><span class="glyphicon glyphicon-chevron-left"></span> Continue Shopping</a></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center"><strong>Total &#8377; ${userModel.cart.grandTotal}</strong></td>
							<!-- <td><a href="#" class="btn btn-success btn-block">Checkout <span class="glyphicon glyphicon-chevron-right"></span></a></td> -->
							<c:choose>
							<c:when test="${availableCount != 0}">
								<td><a href="${contextRoot}/cart/validate" class="btn btn-success btn-block">Checkout <span class="glyphicon glyphicon-chevron-right"></span></a></td>
							</c:when>							
							<c:otherwise>
								<td><a href="javascript:void(0)" class="btn btn-success btn-block disabled"><strike>Checkout <span class="glyphicon glyphicon-chevron-right"></span></strike></a></td>
							</c:otherwise>
						</c:choose>
						</tr>
					</tfoot>
				</table>
	
	</c:when>
	
	<c:otherwise>
		<div class="jumbotron">
			<div class="test-center">
				<h1>Your Cart is Empty!</h1>
			</div>
		</div>
	</c:otherwise>
</c:choose>


</div>