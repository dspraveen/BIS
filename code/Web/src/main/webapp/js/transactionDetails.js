$(function() {
    $.widget("bis.transactionDetails", {
        options: {
            contextPath:'bis',
            type:null
        },

        _create: function() {
            this.updateGrid(this.options.contextPath+"/"+this.options.type+"/fetchTransactionDetails");
            this._bindEvents();
        },

        _addItem : function(){
            var addItemUrl = this.options.contextPath+"/"+this.options.type+"/addItem";
            this.updateGrid(addItemUrl);
        },
        _deleteItem : function(){
            var deleteItemUrl = this.options.contextPath+"/"+this.options.type+"/deleteItem";
            this.updateGrid(deleteItemUrl);
        },
        updateGrid:function(url){
            var self = this;
            var formData = $('form').serialize();
            $.ajax({
                type: 'POST',
                url : url,
                processData : true,
                async: false,
                timeout:3000,
                data:formData,
                success : function(data) {
                    $(".transaction_details").html(data);
                }
            });
			$('.date_of_publish').datepicker({dateFormat: 'dd-mm-yy' });
            $('.date_of_publish').hide();
            $('.date_of_publish_select').each(function(index){
				$(this).bind('change',function(){
                    if($(this).val()==-1){
                        var dop = $($('.date_of_publish')[index]);
                        dop.show();
                        dop.val("");
                    }else{
                        var dop = $($('.date_of_publish')[index]);
                        dop.val($(this).val());
                        dop.hide();
                    }
                });
				var dop = $($('.date_of_publish')[index]);
				if(dop.val())
					$(this).val(dop.val());
				else	
					$(this).val(-1);
				if($(this).val()==-1)
					dop.show();
            });
            
            $('.item_name').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self.updateGrid(self.options.contextPath+"/"+self.options.type+"/itemChanged");
                });
            });
            $('.discount').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self.updateGrid(self.options.contextPath+"/"+self.options.type+"/itemDiscountChanged");
                });
            });
            $('.price_per_item').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self.updateGrid(self.options.contextPath+"/"+self.options.type+"/itemPriceChanged");
                });
            });

            $('.quantity').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self.updateGrid(self.options.contextPath+"/"+self.options.type+"/itemQuantityChanged");
                });
            });
        },

        validateProcurementGrid : function(){
            var errors = new Array();
            var errorCount = 0;
            if( !$('.vendor_name').val() || $('.vendor_name').val()<1){
                errors[errorCount++] = "Please select vendor";
            }
            return this._validate(errors);
        },

        validateSalesGrid : function(){
            var errors = new Array();
            var errorCount = 0;
            if( !$('.hawker_name').val() || $('.hawker_name').val()<1){
                errors[errorCount++] = "Please select vendor";
            }
            return this._validate(errors);
        },

        _validate : function(errors){
			var errorCount = errors.length;
            if( !$('.transaction_date').val()){
                errors[errorCount++] = "Please select transaction date";
            }
            var itemsAdded = new Array();
            var itemCount = 0;
            $('.template_row').each(function(index){
                index = index+1;
                var itemSelectBox = $(this).find('.item_name');
                if( !itemSelectBox.val() || itemSelectBox.val() < 1){
                    errors[errorCount++] = "Please select item for row"+index;
                }else if($.inArray(itemSelectBox.val(),itemsAdded) > -1){
                    errors[errorCount++] = "Item:"+$(this).find('.item_name option:selected').text()+" repeated on row"+index;
                }else{
                    itemsAdded[itemCount++]= itemSelectBox.val();
                }

                var dateOfPublish = $(this).find('.date_of_publish');
                if( !dateOfPublish.val() || !canParseDate(dateOfPublish.val())){
                    errors[errorCount++] = "Please select valid date of publish for row"+index;
                }

                var mrp = $(this).find('.mrp');
                if( !mrp.val() || !canParseDecimal(mrp.val())){
                    errors[errorCount++] = "Please enter valid mrp for row"+index;
                }

                var discount = $(this).find('.discount');
                if( !discount.val() || !canParseDecimal(discount.val())){
                    errors[errorCount++] = "Please enter valid discount for row"+index;
                }

                var price = $(this).find('.price_per_item');
                if(!price.val() || !canParseDecimal(price.val())){
                    errors[errorCount++] = "Please enter valid price for row"+index;
                }

                var quantity = $(this).find('.quantity');
                if( !quantity.val() || !canParseNumber(quantity.val())){
                    errors[errorCount++] = "Please enter valid quantity for row"+index;
                }
            });
            return errors;
        },

        _bindEvents : function(){
            var self = this;
            $('.add_item').bind('click',function(){
                self._addItem();
            });
            $('.remove_item').bind('click',function(){
                self._deleteItem();
            });
			$('.hawker_name').bind('change',function(){
				self.updateGrid(self.options.contextPath+"/sales/hawkerChanged");
			});
			$('.vendor_name').bind('change',function(){
				self.updateGrid(self.options.contextPath+"/procurement/vendorChanged");
			});
			$('.submit').bind('click',function(){
				try{
					var errors;
					if(self.options.type=="procurement"){
						errors = self.validateProcurementGrid();
					} else{
						errors = self.validateSalesGrid();
					}
					if(errors && errors.length > 0){
					    var errorHtml="";
						$(errors).each(function(){
						    errorHtml += "<p class='error'>"+this+"</p>";
						});
						$('.errors').html(errorHtml);
						return false;
					}
					return true;
				}catch(err){
					return false;
				}
			});
        }
    });
});