$(function() {
    $.widget("bis.transactionDetails", {
        options: {
            contextPath:'bis',
            type:null
        },

        _create: function() {
            this._updateGrid(this.options.contextPath+"/"+type+"/fetchTransactionDetails");
            this._bindEvents();
        },

        _addItem : function(){
            var addItemUrl = this.options.contextPath+"/"+type+"/addItem";
            this._updateGrid(addItemUrl);
        },
        _deleteItem : function(){
            var deleteItemUrl = this.options.contextPath+"/"+type+"/deleteItem";
            this._updateGrid(deleteItemUrl);
        },
        _updateGrid:function(url){
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
                    self._updateGrid(self.options.contextPath+"/"+type+"/itemChanged");
                });
            });
            $('.discount').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self._updateGrid(self.options.contextPath+"/"+type+"/itemDiscountChanged");
                });
            });
            $('.price_per_item').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self._updateGrid(self.options.contextPath+"/"+type+"/itemPriceChanged");
                });
            });

            $('.quantity').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self._updateGrid(self.options.contextPath+"/"+type+"/itemQuantityChanged");
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
            $('.vendor_name').bind('change',function(){
                self._updateGrid(self.options.contextPath+"/"+type+"/vendorChanged");
            });
        }
    });
});