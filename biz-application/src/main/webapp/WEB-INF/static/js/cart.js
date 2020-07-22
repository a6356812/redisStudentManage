function updateQuantity(e) {
    var realId= $(e).attr('id');
    var form=document.getElementById(realId.toString());
    form.action="/cart?method=updateQuantity";
    form.submit();
}

function deleteCartBook(e) {
    var realId= $(e).attr('id');
    var form=document.getElementById(realId.toString());
    form.action="/cart?method=deleteCartBook";
    form.submit();
}
function saveCart(e) {
    var realId= $(e).attr('id');
    var form=document.getElementById(realId.toString());
    form.action="/cart?method=saveCartBook";
    form.submit();
}

function deleteAllCartBook(e) {
    var realId= $(e).attr('id');
    var form=document.getElementById(realId.toString());
    form.action="/cart?method=deleteAllCartBook";
    form.submit();

}