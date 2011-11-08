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
        }
    });
});