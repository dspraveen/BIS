<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">

    $(document).ready(function(){
        $('.transaction_date').datepicker({dateFormat: 'dd-mm-yy' });
        $('.date_of_publish').datepicker({dateFormat: 'dd-mm-yy' });
    });

    function validateForm(){
		var errors=new Array(); 
		var errorCount =0;
		if($('.vendor_name').val()==-1){
			errors[errorCount++]="Please select vendor";
		}
		if($('.transaction_date').val().trim()==""){
			errors[errorCount++]="Please enter transaction date";
		}
		$('.item_name').each(function(index){
			if($(this).val()=="-"){
				errors[errorCount++]="Please select item for row:"+(index+1);
			}
		});
		$('.date_of_publish').each(function(index){
			if($(this).val().trim()==""){
				errors[errorCount++]="Please select date of publish  for row:"+(index+1);
			}
		});
		$('.discount').each(function(index){
			if($(this).val().trim()=="" || $(this).val().trim()<0){
				errors[errorCount++]="Please select valid discount for row:"+(index+1);
			}
		});
        $('.price_per_item').each(function(index){
			if($(this).val().trim()=="" || $(this).val().trim()<1){
				errors[errorCount++]="Please select valid item price for row:"+(index+1);
			}
		});
		$('.qty').each(function(index){
			if($(this).val().trim()=="" || $(this).val().trim()<1){
				errors[errorCount++]="Please select valid item price for row:"+(index+1);
			}
		});
		if(errorCount >0){
			alert(errors.join('\n'));
			return false;
		}
    }

    function onDiscountChange(rowId){
        var mrp = $($('.mrp')[rowId]).val();
        var discount = $($('.discount')[rowId]).val();
        var qty = $($('.qty')[rowId]).val();
        var calculatedPrice = mrp * (100 - discount) / 100;
        $($('.price_per_item')[rowId]).val(calculatedPrice);
        $($('.total')[rowId]).val(qty * calculatedPrice);
    }

    function onPriceChange(rowId){
        var mrp = $($('.mrp')[rowId]).val();
        var price = $($('.price_per_item')[rowId]).val();
        var qty = $($('.qty')[rowId]).val();
        $($('.discount')[rowId]).val((mrp-price)*100/mrp);
        $($('.total')[rowId]).val(qty * price);
    }

    function onQtyChange(rowId){
        var price = $($('.price_per_item')[rowId]).val();
        var qty = $($('.qty')[rowId]).val();
        $($('.total')[rowId]).val(qty * price);
    }
	
	function itemSelected(rowId){
	    var mrpElement = $('.mrp')[rowId];
        var itemPriceUrl = "/item/price?selectedItemCode="+$($('.item_name')[rowId]).val();
        $.ajax({
            url : itemPriceUrl,
            processData : true,
            success : function(data) {
                $(mrpElement).val(data.price);
            }
        })

        var vendorDiscountUrl = "/vendor/discount?selectedVendorId="+$('.vendor_name').val();
		var discountElement = $('.discount')[rowId];
        $.ajax({
            url : vendorDiscountUrl,
            processData : true,
            success : function(data) {
                $(discountElement).val(data.discount);
                $(discountElement).trigger('change');
            }
        });
    }
	
	function updateDiscount(){
	    var vendorDiscountUrl = "/vendor/discount?selectedVendorId="+$('.vendor_name').val();
        $.ajax({
            url : vendorDiscountUrl,
            processData : true,
            success : function(data) {
                $('.discount').val(data.discount);
                $('.discount').each(function(index){
                    $(this).trigger('change');
                });
            }
        });
	}
	
	function addRow(tableID) {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		var firstRowHtml = document.getElementById(tableID).rows[0].innerHTML;
		row.innerHTML = firstRowHtml.replace(/[0]/g,rowCount).replace(/(0)/g,rowCount);
        $($('.date_of_publish')[rowCount]).removeClass('hasDatepicker').datepicker({dateFormat: 'dd-mm-yy' });
	}

	function deleteRow(tableID) {
		try {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;

		for(var i=0; i<rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if(null != chkbox && true == chkbox.checked) {
				table.deleteRow(i);
				rowCount--;
				i--;
			}

		}
		}catch(e) {
			alert(e);
		}
    }
</script>
<form:form commandName="procurementTransaction" method="POST" action="addProcurementTransaction"  onsubmit="return validateForm();">
    <div>
        <div class="section">
            <span class="left"><label>Select Vendor:</label></span
            <span class="right">
				<form:select path="vendor.vendorId" class="vendor_name" onChange="updateDiscount();">
					<form:option value="-1" label="--Please Select"/>
					<form:options items="${vendors}" itemLabel="vendorName" itemValue="vendorId" />
				</form:select>
			</span>
        </div>
        <div class="section">
            <span class="left"><label>Transaction Date:</label></span
            <span class="right"><input name="date" class="transaction_date"/></span>
        </div>
        <div class="section">
            <span class="left"><label>Transaction Type:</label></span
            <span class="right"><form:select path="transactionType" items="${procurementTransactionType}" /></span>
        </div>
        <div>
		 	<TABLE id="dataTable" width="350px" border="1">
				<thead>
					<TD></TD>
					<TD>Item</TD>
					<TD>Date Of Publishing</TD>
					<TD>MRP</TD>
					<TD>Discount</TD>
					<TD>Price Per Item</TD>
					<TD>Qty</TD>
					<TD>Total</TD>
				</thead>
				<tr>
					<td><input type='checkbox' name='chk' class='item_select'/></td>
					<td>
						<form:select path='transactionDetails[0].item.itemCode' class='item_name'  onChange='itemSelected(0);'>
							<form:option value="-" label="--Please Select"/>
							<form:options items="${items}" itemLabel="itemName" itemValue="itemCode"/>
						</form:select>
					</td>
					<td><input name='transactionDetails[0].dateOfPublishing' class='date_of_publish' type='text'/></td>
					<td><input class='mrp' type='text' readonly='true'/></td>
					<td><input class='discount' type='text' onChange='onDiscountChange(0)'/></td>
					<td><input name='price_per_item' class='price_per_item' type='text' onChange='onPriceChange(0)'/></td>
					<td><input name='transactionDetails[0].quantity' class='qty' type='text' onChange='onQtyChange(0)'/></td>
					<td><input name='transactionDetails[0].amount' class='total' type='text' readonly='true'/></td>
				</tr>
			</TABLE>
			<INPUT type="button" value="Add Row" onclick="addRow('dataTable')" />
		 	<INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" />
        </div>
        <div class="section">
            <span class="center"><input type="submit" value="Submit"/> <input type="reset" value="Clear"/></span>
        </div>
    </div>
</form:form>



