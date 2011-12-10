<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript">
    $(document).ready(function(){
        $('.from_date').datepicker({dateFormat: 'dd-mm-yy' });
        $('.to_date').datepicker({dateFormat: 'dd-mm-yy' });
        $('#fetch_Bills').bind("click",function(){
		    if(!($('.from_date').val())){
				alert("From-Date Not Provided");
				return false;
			}
		    if(!($('.to_date').val())){
				alert("To-Date Not Provided");
				return false;
			}			
            var BillsInRangeUrl = "<%=request.getContextPath()%>/salesBilling/billsInRange?fromDate="+$('.from_date').val()+"&toDate="+$('.to_date').val()+"&hawkerId="+$('.hawker_name').val();
            $.ajax({
                url : BillsInRangeUrl,
                processData : true,
                success : function(data) {
                    $(".sales_Bills").html(data);
                }
            })
        });
    });
</script>
<div class="general_division">
    <div class="content_header">Sales Bill Search Form</div>
    <div class="section">
        <span class="left"><label>From Date:</label></span
        <span class="right"><input type="text" class="from_date"/></span>
    </div>
    <div class="section">
        <span class="left"><label>To Date:</label></span
        <span class="right"><input type="text" class="to_date"/></span>
    </div>
        <div class="section">
        <span class="left"><label>Select Vendor:</label></span
        <span class="right">
            <select class="hawker_name">
			    <option value="-1">--Please Select</option>
			    <c:forEach var="hawker" items="${hawkers}">
				    <option value="${hawker.hawkerId}">${hawker.hawkerName}</option>
				</c:forEach>
			</select>
        </span>
    </div>
    <div class="section">
        <span class="left"><input class="buttons" type="button" value="Fetch Sales Bills" id="fetch_Bills"/></span>
    </div></br></br>
    <div class="sales_Bills">
    </div>
</div>