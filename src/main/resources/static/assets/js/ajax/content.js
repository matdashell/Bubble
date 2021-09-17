window.onload = function () {

    $("li[id='replace-msg-icon']").load("/ajax/content/mensagem-icon");         //mensagem icon navbar
    $("div[id='replace-msg-modal']").load("/ajax/content/mensagem-modal");      //mensagens (caixa de entrada)

}

var frm = $("form[id='formPerfil']");

frm.submit(function (e) {

    e.preventDefault();

    e = null;

    $.ajax({
        type: frm.attr('method'),
        url: frm.attr('action'),
        data: frm.serialize(),
        success: function (data) {
            console.log('Submission was successful.');
            console.log(data);

            $("div[id='panel']").replaceWith(data);

        },
        error: function () {
            console.log('An error occurred.');
            console.log(data);

        },
    });
});

function confirmarSolicit(user){
    $.ajax({
        type: "POST",
        url: "/principalUser/respSolicitacao",
        data: {username: user, resposta: true},
        success: function () {
        console.log('Submission was successful.');

        $("li[id='replace-msg-icon']").load("/ajax/content/mensagem-icon");   //recarregar icone msgm nav
        $("div[id='replace-msg-dados']").load("/ajax/content/mensagem-data"); //recarregar mensagens

        },
        error: function (data) {
            console.log('An error occurred.');
            console.log(data);
        },
    });
}

function negarSolicit(user){
    $.ajax({
        type: "POST",
        url: "/principalUser/respSolicitacao",
        data: {username: user, resposta: false},
        success: function () {
            console.log('Submission was successful.');

            $("li[id='replace-msg-icon']").load("/ajax/content/mensagem-icon");   //recarregar icone msgm nav
            $("div[id='replace-msg-dados']").load("/ajax/content/mensagem-data"); //recarregar mensagens

        },
        error: function (data) {
            console.log('An error occurred.');
            console.log(data);
        },
    });
}
