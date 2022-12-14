function deleteCategory(id) {
    var data = id;
    var name = $('#row_'+data).children('td.td_name').text();
    if (confirm("Bạn có muốn xóa " + name + " không ???")) {
        $.ajax({
            type : "DELETE",
            contentType : "application/json",
            url : "http://localhost:8081/api/categories/" + id,
            data : JSON.stringify(id),
            success: function (result){
                if (confirm("Xóa thành công!") == true) {
                    window.location.href ='/categories';
                } else {
                    return false;
                }
            },
            error: function (er) {
                alert("Bạn phải xóa hết các sản phẩm có liên quan đến danh mục trước")
            },
        });
    }
}
function SaveCategory(id) {

    if (id==null) {
        var formData = {
            name : $("#name").val(),
        }
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "http://localhost:8081/api/categories",
            data : JSON.stringify(formData),
            success: function(result) {
                if (confirm("Save Successfully!") == true) {
                    window.location.href ='/categories';
                } else {
                    return false;
                }
            },
            error: function(err) {
                alert("Data already exists!")
                console.log("ERROR: ", err);
            }
        });
    }
    else {
        var formData = {
            id : $("#id").val(),
            name : $("#name").val(),
        }
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "http://localhost:8081/api/categories",
                data : JSON.stringify(formData),
                success: function(result) {
                    if (confirm("Edit Successfully!") == true) {
                        window.location.href ='/categories';
                    } else {
                        return false;
                    }
                },
                error: function(err) {
                    alert("Data already exists!")
                    console.log("ERROR: ", err);
                }
            });
    }
}
