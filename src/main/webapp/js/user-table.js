
// khi nao file html da duoc nap vao trinh duyet
// doan code trong function se duoc thuc hien
$(document).ready(function(){
    $(".btn-delete-user").click(function(event){
            var id = $(this).attr("userid")
            var This = $(this)
            $.ajax({
              method: "GET",
              url: "http://localhost:8080/demoservlet/user/delete?id=" + id,

            })
              .done(function( result ) {
                This.closest("tr").remove()
                console.log("ket qua", result)
              });

    });
})