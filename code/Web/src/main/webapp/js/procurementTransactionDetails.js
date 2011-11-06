$(function() {
    $.widget("bis.procurementTransactionDetails", {
        options: {
            contextPath:'bis'
        },

        _create: function() {
            this._updateGrid(this.options.contextPath+"/procurement/fetchTransactionDetails");
            this._bindEvents();
        },

        _addItem : function(){
            var addItemUrl = this.options.contextPath+"/procurement/addItem";
            this._updateGrid(addItemUrl);
        },
        _deleteItem : function(){
            var deleteItemUrl = this.options.contextPath+"/procurement/deleteItem";
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
                    self._updateGrid(self.options.contextPath+"/procurement/itemChanged");
                });
            });
            $('.discount').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self._updateGrid(self.options.contextPath+"/procurement/itemDiscountChanged");
                });
            });
            $('.price_per_item').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self._updateGrid(self.options.contextPath+"/procurement/itemPriceChanged");
                });
            });

            $('.quantity').each(function(index){
                $(this).bind('change',function(){
                    $('#effectedRowId').val(index);
                    self._updateGrid(self.options.contextPath+"/procurement/itemQuantityChanged");
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
                self._updateGrid(self.options.contextPath+"/procurement/vendorChanged");
            });
        }
    });
});