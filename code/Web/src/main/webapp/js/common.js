function canParseNumber(value){
    var numberRegex =/^\s*\d+\s*$/;
    if(numberRegex.test(value)){
        return true;
    }
    return false;
}

function canParseDecimal(value){
    var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
    if(floatRegex.test(value)){
        return true;
    }
    return false;
}

function canParseDate(dateString){
   try{
        $.datepicker.parseDate('dd-mm-yy', dateString);
        return true;
    }catch(err){
        return false;
    }

}