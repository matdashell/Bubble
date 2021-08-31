
//alterar se esta ou não seguindo um usuario

var frm = $("#formSeguir");
var btn = $("#btnSeguir");
var spn = $("#textoSeguir");
var txt;

frm.submit(function (e) {

    e.preventDefault();

    $.ajax({
        type: frm.attr('method'),
        url: frm.attr('action'),
        data: frm.serialize(),
        success: function (data) {
            console.log('Submission was successful.');
            console.log(data);

    if(btn.classList.contains("primary")){
        btn.classList.remove("primary");
        btn.classList.add("erro");
        txt = document.createTextNode("Deixar de seguir");
    }else{
        btn.classList.remove("erro");
        btn.classList.add("primary");
        txt = document.createTextNode("Seguir");
    }

    spn.appendChild(txt);

        },
        error: function (data) {
            console.log('An error occurred.');
            console.log(data);
        },
    });
});