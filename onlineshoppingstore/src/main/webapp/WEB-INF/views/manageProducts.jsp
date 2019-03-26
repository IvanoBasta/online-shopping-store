<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">
	<div class="row">
	<c:if test="${not empty message}">
	<div class="col-xs-12">
		<div class="alert alert-success alert-dismissible">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			${message}			
		</div>
	</div>
	</c:if>
	
	<c:if test="${not empty messageField}">
	<div class="col-xs-12">
		<div class="alert alert-warning alert-dismissible">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			${messageField}			
		</div>
	</div>
	</c:if>
	
	
	
	
		<div class="com-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Managment</h4>
				</div>
			<div class="panel-body">
					<!-- FORM ELEMENTS -->
					<sf:form class="form-horizontal" modelAttribute="product"
					action="${contextRoot}/manage/products"
					method="POST"
					enctype="multipart/form-data">

						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter Product Name: </label>
							<div class="col-md-8">	
								<sf:input type="text" path="name" id="name" placeholder="Product Name" class="form-control"></sf:input>
								 <sf:errors path="name" cssClass="help-block" element="em"/>
								<!--  <em class="help-block">Please enter Product Name!</em> -->
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Enter Brand Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control"></sf:input>
								 <sf:errors path="brand" cssClass="help-block" element="em"/>
								<!-- <em class="help-block">Please enter Brand Name!</em> -->	
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product Description: </label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4" placeholder="Write a description" class="form-control"></sf:textarea>
								<sf:errors path="description" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Enter Unit Price: </label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice" rows="4" placeholder="Enter Unit Price" class="form-control"></sf:input>
								<sf:errors path="unitPrice" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity Available: </label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" rows="4" placeholder="Enter Quantity" class="form-control"></sf:input>	
							</div>
						</div>
						
						<!-- File element for image upload -->
						
						<div class="form-group">
							<label class="control-label col-md-4" for="file">Select an image: </label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control"></sf:input>
								<sf:errors path="file" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select Category: </label>
							<div class="col-md-8">
								<sf:select path="categoryId" id="quantity" class="form-control"
								items="${categories}"
								itemLabel="name"
								itemValue="id"
								/>
								
								<c:if test="${product.id == 0}">
									<div class="text-right">
										<br/>
										<button type="button" data-toggle="modal" data-target="#myCategoryModal" class ="btn-warning btn-xs">Add Category</button>
									</div>
								</c:if>
								
							</div>

						</div>
						
						<div class="form-group">

							<div class="col-md-8">

								<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info"></input>
								
								<!-- Hidden fields for products -->
								
								<sf:hidden path="id"/>
								<sf:hidden path="code"/>
								<sf:hidden path="supplierId"/>
								<sf:hidden path="active"/>
								<sf:hidden path="purchases"/>
								<sf:hidden path="views"/>
								
							</div>

						</div>
				</sf:form>

			</div>

		</div>

	</div>
</div>

	<div class="row">
		
		<div class="col-lg-12">
			<br/>
			<h4>Available Products</h4>
			<hr/>
		</div>

		
		<div class="col-lg-12">
				<div class="container-fluid">
				
					<div class="table-responsive">
					
						<!-- Products table for Admin -->
						<table id="adminProductsTable" class="table table-condensed table-bordered">
							
							<thead>					
								<tr>					
									<th>Id</th>
									<th>&#160;</th>
									<th>Name</th>
									<th>Brand</th>
									<th>Qty. Avail</th>
									<th>Unit Price</th>
									<th>Activate</th>				
									<th>Edit</th>
								</tr>					
							</thead>
							
							
							
							<tfoot>
								<tr>					
									<th>Id</th>
									<th>&#160;</th>
									<th>Name</th>
									<th>Brand</th>
									<th>Qty. Avail</th>
									<th>Unit Price</th>
									<th>Activate</th>				
									<th>Edit</th>
								</tr>									
							</tfoot>
									
						</table>
					</div>
				
				</div>
				
		</div>
	
		
	</div>
	
	<!-- Modal -->

	<div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	      	<h4 class="modal-title" id="myModalLabel">New Category</h4>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	       	
	       	<!-- Category form -->
	        <sf:form id="categoryForm" class="form-horizontal" modelAttribute="category" action="${contextRoot}/manage/category" method="POST">
	        	
       			<div class="form-group">
					<label class="control-label col-md-4">Name</label>
					<div class="col-md-8 validate">
						<sf:input type="text" path="name" class="form-control"
							placeholder="Category Name" /> 
					</div>
				</div>
       			
       			<div class="form-group">				
					<label class="control-label col-md-4">Description</label>
					<div class="col-md-8 validate">
						<sf:textarea path="description" class="form-control"
							placeholder="Enter category description here!" /> 
					</div>
				</div>	        	        
	        
	        
				<div class="form-group">				
					<div class="col-md-offset-4 col-md-4">					
						<input type="submit" name="submit" value="Save" class="btn btn-primary"/>						
					</div>
				</div>	        
	        </sf:form>
	      </div>
	    </div>
	  </div>
	</div>


</div>