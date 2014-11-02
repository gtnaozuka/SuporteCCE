$(document).ready(function () {
    $('.input-group .dropdown-menu li').click(function (e) {
        e.preventDefault();
        $('.category').val($(this).data('id'));
        $('.input-group button').html($(this).text() + ' <span class="caret"></span>');
    });
    
    $('.btn_tecnicoAdmUpdate').click(function() {
        $.get($(this).data('href'), function(data) {
            var tecnico = JSON.parse(data);
            $("#inputId").val(tecnico.id);
            $("#inputChapa").val(tecnico.matriculaChapa);
            $("#inputNome").val(tecnico.nome);
            $("#inputDepartamento").val(tecnico.departamento);
            $("#inputEmail").val(tecnico.email);
            $("#tecnicoAdmUpdate").modal('show');
        });
    });
    
    $('.btn_tecnicoAdmDelete').click(function() {
        $('#a_tecnicoAdmDelete').attr('href', $(this).data('href'));
        $('#tecnicoAdmDelete').modal('show');
    });
    
    $('.btn_requisicaoDelete').click(function() {
        $.get($(this).data('href'), function(data) {
            var requisicao = JSON.parse(data);
            $('#reqTipo').html(requisicao.tipo);
            $('#reqLocalizacao').html(requisicao.localizacao);
            $('#reqFuel').html(requisicao.fuel);
            $('#reqDataCriacao').html(requisicao.dataCriacao);
            $('#reqDescricao').html(requisicao.descricao);
            $('#inputId').val(requisicao.id);
            $('#requisicaoDelete').modal('show');
        });
    });
    
    $('.btn_requisicaoAccept').click(function() {
        $.get($(this).data('href'), function(data) {
            var requisicao = JSON.parse(data);
            $('#reqRemetente').html(requisicao.usuarioId.nome);
            $('#reqTipo').html(requisicao.tipo);
            $('#reqLocalizacao').html(requisicao.localizacao);
            $('#reqFuel').html(requisicao.fuel);
            $('#reqDataCriacao').html(requisicao.dataCriacao);
            $('#reqDescricao').html(requisicao.descricao);
            $('#inputId').val(requisicao.id);
            $('#requisicaoAccept').modal('show');
        });
    });
});