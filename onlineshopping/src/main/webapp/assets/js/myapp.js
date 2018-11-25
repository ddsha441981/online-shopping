$(function() {
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
		
	 default:
		 if(menu =="Home") break;
		$('#listProducts').addClass('active');
	 $('#a_'+menu).addClass('active');
		break;
	}

	
	//code for jquery datatable
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
	
});