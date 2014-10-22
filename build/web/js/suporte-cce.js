$(document).ready(function () {
    $('.dropdown-menu li').click(function (e) {
        e.preventDefault();
        var selected = $(this).text();
        $('.category').val(selected);
        $('.input-group button').html(selected + ' <span class="caret"></span>');
    });
    
    $('.btn_tecnicoAdmUpdate').click(function() {
        $.get($(this).data('href'), function(data) {
            var tecnico = JSON.parse(data);
            $("#inputId").val(tecnico.id);
            $("#inputChapa").val(tecnico.matricula_chapa);
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
        $('#a_requisicaoDelete').attr('href'. $(this).data('href'));
        $('#requisicaoDelete').modal('show');
    });
});