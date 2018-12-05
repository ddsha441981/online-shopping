//--------------------------------Main Function Started---------------------------
$(function() {
	
//--------------------------------Begin------------------------------------------
	
	//solving the active menu problem
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;

	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
		
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	 default:
		 if(menu =="Home") break;
		$('#listProducts').addClass('active');
	 $('#a_'+menu).addClass('active');
		break;
	}
	
//--------------------------------End--------------------------------------------	
	//code for jquery datatable
//--------------------------------Begin------------------------------------------
	
	//create a dataset for testing purpose for table
	
	/*var products = [
	                
	                
	                ['1' , 'ABC','20000','5'],
	                ['2' , 'ABC','10000','2'],
	                ['3' , 'ABC','80000','4'],
	                ['4' , 'ABC','60000','6'],
	                ['5' , 'ABC','80000','3'],
	                ['6' , 'ABC','40000','6'],
	                ['7' , 'ABC','70000','1'],
	                ['8' , 'ABC','20000','8'],
	                ['9' , 'ABC','70000','9'],
	                
	                ];*/
	
	
	var $table = $('#productListTable');
	
	//execute the below code only where we have this table
	if($table.length){
	
		//console.log('inside the table');
		var jsonUrl='';
		if(window.categoryId==''){
		
			//console.log('inside the table'+jsonUrl);
			//alert(window.categoryId);
			
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}
		else{
			
			jsonUrl = window.contextRoot + '/json/data/category/'+window.categoryId+'/products';
			//console.log('inside the table'+jsonUrl);
		}
		
		$table.DataTable({
			lengthMenu:[[3,5,10,-1],['3 Records','5 Records','10 Records','All']],
			pageLength:5,
			ajax:{
				url: jsonUrl,
				dataSrc: ''
					
			},
			
			columns: [
			          {
			        	  data: 'code',
			        		  mRender: function(data, type, row){
			        			  return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
			        		  }
			          },
			          {
			        	  data: 'name'
			          },
			          {
			        	  data: 'brand'
			          },
			          {
			        	  //mRender function use for rupees symbol(Html code &#8377)
			        	  data: 'unitPrice',
			        	  mRender: function(data, type, row){
			        		  return '&#8377; ' + data
			        	  }
			          },
			          {
			        	  data: 'quantity',
			        	  mRender: function(data, type, row){
			        		  if(data < 1){
			        			  return '<span style="color:red">Out of Stock!</span>';
			        		  }
			        		  
			        		  return data;
			        	  }
			          },
			          {
			        	  //for disable to sortable button for particular row
			        	  bSortable: false,
			        	  data: 'id',
			        		  mRender: function(data, type, row){
				        		 var str = '';
				        		 str += '<a href="'+window.contextRoot+ '/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160';
				        		//disabled shopping cart button
				        		 if(row.quantity < 1){
				        			
				        			 str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
				        		 }
				        		 else{
				        			 
				        			 str += '<a href="'+window.contextRoot+ '/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>'; 
				        		 }
				        		 
				        		 return str;
				        	  }
			          }
			          
			          ]
			
		});
		
		
	}
	
//--------------------------------End--------------------------------------------
	//dismissing the alert after 3 second
//--------------------------------Begin------------------------------------------
	var $alert = $('.alert');
	
	if($alert.length){
		
		setTimeout(function(){
			$alert.fadeOut('slow');
		} , 3000)
	}
	
//--------------------------------End--------------------------------------------
	//for toggle alert box
//--------------------------------Begin------------------------------------------
	/*$('.switch input[type="checkbox"]').on('change', function() {
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		var dMsg = (checked)? 'You want to activate the Product?':
							  'You want to deactivate the Product?';
		var value = checkbox.prop('value');
		
		bootbox.confirm({
			size: 'medium',
			title: 'Product Activation & Deactivation',
			message: dMsg,
			callback: function(confirmed){
				
				if(confirmed){
					console.log(value);
					bootbox.alert({ 
						size: 'medium',
						title: 'Information',
						message: 'You are going to perform operation on Product'+ value
					});
					
				}
				else {
					alert("Else Part");
					checkbox.prop('checked', !checked);
				}
			}
			
		});
		
	});*/
	
	
//--------------------------------End--------------------------------------------	
	//DataTable For admin
//--------------------------------Begin------------------------------------------	
	
var $adminProductsTable = $('#adminProductsTable');
	
	//execute the below code only where we have this table
	if($adminProductsTable.length){
	
		//console.log('inside the table');
		var jsonUrl= window.contextRoot + '/json/data/admin/all/products';
		
		
		$adminProductsTable.DataTable({
			lengthMenu:[[10,30,50,-1],['10 Records','30 Records','50 Records','All']],
			pageLength:30,
			ajax:{
				url: jsonUrl,
				dataSrc: ''
					
			},
			
			columns: [
			          {
			        	data: 'id'  
			          },
			          {
			        	  data: 'code',
			        		  mRender: function(data, type, row){
			        			  return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminTableImg"/>';
			        		  }
			          },
			          {
			        	  data: 'name'
			          },
			          {
			        	  data: 'brand'
			          },
			          {
			        	  data: 'quantity',
			        	  mRender: function(data, type, row){
			        		  if(data < 1){
			        			  return '<span style="color:red">Out of Stock!</span>';
			        		  }
			        		  
			        		  return data;
			        	  }
			          },
			          {
			        	  //mRender function use for rupees symbol(Html code &#8377)
			        	  data: 'unitPrice',
			        	  mRender: function(data, type, row){
			        		  return '&#8377; ' + data
			        	  }
			          },
			          {
			        	  //for disable to sortable button for particular row
			        	  data: 'active',
			        	  bSortable: false,
			        	  mRender:function(data,type,row){
			        		  
			        		 var str = '';
			        		 str += '<label class="switch">';
			        		 
			        		 if(data){
			        			 
			        			 str +=	'<input type="checkbox" checked="checked" value="'+row.id+'"/>'; 
			        		 }
			        		 else{
			        			 str +=	'<input type="checkbox"  value="'+row.id+'"/>';
			        		 }
							
							 str +=	'<div class="slider round"></div> </label>';
								
			        		  return str;
			        	  }
			        	  
			        	  	 
			          },
			          {
			        	  data: 'id',
			        	  bSortable: false,
			        	  mRender: function(data,type,row){
			        		 
			        		  var str = '';
			        		  
			        		  str += '<a href="'+ window.contextRoot + '/manage/'+ data
								+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

							  
							  return str;
			        	  }
			          }
			          ],//table columns ended here
			          
			          //using initcomplete function to execute entire data table v/s toggle switch 
			          initComplete : function(){
			        	  
			        	  var api = this.api();
			        	  api.$('.switch input[type="checkbox"]').on('change', function() {
			        			var checkbox = $(this);
			        			var checked = checkbox.prop('checked');
			        			var dMsg = (checked)? 'You want to activate the Product?':
			        								  'You want to deactivate the Product?';
			        			var value = checkbox.prop('value');
			        			
			        			bootbox.confirm({
			        				size: 'medium',
			        				title: 'Product Activation & Deactivation',
			        				message: dMsg,
			        				callback: function(confirmed){
			        					
			        					if(confirmed){
			        						console.log(value);
			        						var activationUrl = window.contextRoot + '/manage/product/'+checkbox.prop('value')+'/activation';
			        						
			        						$.post(activationUrl, function(data){
			        							bootbox.alert({ 
				        							size: 'medium',
				        							title: 'Information',
				        							message: data
				        						});
			        							
			        						});
			        						
			        					}
			        					else {
			        						alert("Else Part");
			        						checkbox.prop('checked', !checked);
			        					}
			        				}
			        				
			        			});
			        			
			        		});
			          }
			
		});
		
		
	}
	
//--------------------------------End--------------------------------------------
	//validation code for category
//--------------------------------Begin------------------------------------------
	$categoryForm = $('#categoryForm');
	
	if($categoryForm.length){
		
		
		$categoryForm.validate({
			
			rules : {
				
				name : {
					
					required: true,
					minlength: 2
				},
				
				description : {
					
					required: true
				}
			},
			
			messages : {
				
				name : {
					
					required: 'please add the category name!!',
					minlength: 'The Category name should not be less than 2 characters'
				},
				
				description : {
					
					required: 'please add the description for this category!!'
				}
			},
			
			errorElement : 'em',
			errorPlacement: function(error, element){
				
				//add the class of help-block
				error.addClass('help-block');
				
				//add the error element after the input element
				error.insertAfter(element);
			}
			
		});
	}
	
//--------------------------------End--------------------------------------------	
	
});
//--------------------------------Main Function Ended----------------------------