$(document).ready(
    function() {

        // SUBMIT FORM
        $("#category").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                name : $("#name").val(),
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "http://localhost:8080/api/categories/",
                data : JSON.stringify(formData),
                success: function(result) {
                    if (confirm("added successfully!") == true) {
                        window.location.href ='/categories';
                    } else {
                        return false;
                    }
                },
                error: function(err) {
                    alert("Data already exists!")
                    console.log("ERROR: ", e);
                }
            });
        }
    });