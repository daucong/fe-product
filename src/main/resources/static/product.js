function deleteProduct(id) {
    var data = id;
    var name = $('#row_'+data).children('td.td_name').text();
    if (confirm("Bạn có muốn xóa " + name + " không???")) {
        $.ajax({
            type : "DELETE",
            contentType : "application/json",
            url : "http://localhost:8080/api/products/" + id,
            data : JSON.stringify(id),
            success: function (result){
                if (confirm("Xóa thành công!") == true) {
                    window.location.href ='/products';
                } else {
                    return false;
                }
            },
            error: function (error) {
                console.log(error)
            }
        });
    }
}