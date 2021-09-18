window.onload = function () {

    $("li[id='replace-msg-icon']").load("/ajax/content/mensagem-icon");         //mensagem icon navbar
    $("div[id='replace-msg-modal']").load("/ajax/content/mensagem-modal");      //mensagens (caixa de entrada)

}

//função para submetimento de formulario de amizade
var frm = $("form[id='formPerfil']");

frm.submit(function (e) {

    e.preventDefault();

    $.ajax({
        type: frm.attr('method'),
        url: frm.attr('action'),
        data: frm.serialize(),
        success: function (data) {
            console.log('Submission was successful.');
            console.log(data);

            $("button[id='panel']").replaceWith(data);

        },
        error: function () {
            console.log('An error occurred.');
            console.log(data);

        },
    });
});

//metodo para confirmar solicitação enviado via correio
function respSolicit(user, resposta){
    $.ajax({
        type: "POST",
        url: "/principalUser/respSolicitacao",
        data: {username: user, resposta: resposta},
        success: function () {
            console.log('Submission was successful.');

            $("li[id='replace-msg-icon']").load("/ajax/content/mensagem-icon");   //recarregar icone msgm nav
            $("div[id='replace-msg-dados']").load("/ajax/content/mensagem-data"); //recarregar mensagens

        },
        error: function (data) {
            console.log('An error occurred.');
        },
    });
}

function curtir(id){
    $.ajax({
        type: "POST",
        url: "/postagem/altCurtir",
        data: {id : id},
        success: function (data) {
            console.log('Submission was successful.');
            console.log(data);

            $("div[id='btn-curtir"+id+"']").replaceWith(data);   //recarregar icone msgm nav

        },
        error: function (data) {
            console.log('An error occurred.');
            console.log(data);
        },
    });
}

function comentarP(id){

    $.ajax({
        type: "POST",
        url: "/postagem/comentar",
        data: {id : id, comentario : $("input[id='input-comentario"+id+"']").val()},
        success: function (data) {
            console.log('Submission was successful.');
            console.log(data);

            $("div[id='comentarios-list"+id+"']").replaceWith(data);
            $("span[id='comentario-num"+id+"']").load("/ajax/content/numcoment/"+id);
            $("input[id='input-comentario"+id+"']").val('');

        },
        error: function (data) {
            console.log('An error occurred.');
            console.log(data);
        },
    });
}
