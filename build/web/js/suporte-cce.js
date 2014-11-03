$(document).ready(function () {
    $('.inputGroupPrioridade li').click(function (e) {
        e.preventDefault();
        $('.inputGroupPrioridade input').val($(this).data('id'));
        $('.inputGroupPrioridade button').html($(this).text() + ' <span class="caret"></span>');
    });

    $('.inputGroupEstado li').click(function (e) {
        e.preventDefault();
        $('.inputGroupEstado input').val($(this).data('id'));
        $('.inputGroupEstado button').html($(this).text() + ' <span class="caret"></span>');
    });

    $('.inputGroupTipo li').click(function (e) {
        e.preventDefault();
        var selected = $(this).text();
        $('.inputGroupTipo input').val(selected);
        $('.inputGroupTipo button').html(selected + ' <span class="caret"></span>');
    });

    $('.inputGroupGrafico li').click(function (e) {
        e.preventDefault();
        var selected = $(this).text();
        $('.inputGroupGrafico input').val(selected);
        $('.inputGroupGrafico button').html(selected + ' <span class="caret"></span>');
    });

    $('.inputGroupLocal li').click(function (e) {
        e.preventDefault();
        var selected = $(this).text();
        $('.inputGroupLocal input').val(selected);
        $('.inputGroupLocal button').html(selected + ' <span class="caret"></span>');
    });

    $('.btn_tecnicoAdmUpdate').click(function () {
        $.get($(this).data('href'), function (data) {
            var tecnico = JSON.parse(data);
            $("#inputId").val(tecnico.id);
            $("#inputChapa").val(tecnico.matriculaChapa);
            $("#inputNome").val(tecnico.nome);
            $('.inputGroupLocal input').val(tecnico.departamento);
            $('.inputGroupLocal button').html(tecnico.departamento + ' <span class="caret"></span>');
            $("#inputEmail").val(tecnico.email);
            $("#tecnicoAdmUpdate").modal('show');
        });
    });

    $('.btn_tecnicoAdmDelete').click(function () {
        $('#a_tecnicoAdmDelete').attr('href', $(this).data('href'));
        $('#tecnicoAdmDelete').modal('show');
    });

    $('.btn_requisicaoDelete').click(function () {
        $.get($(this).data('href'), function (data) {
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

    $('.btn_requisicaoAccept').click(function () {
        $.get($(this).data('href'), function (data) {
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

    $('.btn_requisicaoUpdate').click(function () {
        $.get($(this).data('href'), function (data) {
            var requisicao = JSON.parse(data);
            $('#reqRemetente2').html(requisicao.usuarioId.nome);
            $('#reqTipo2').html(requisicao.tipo);
            $('#reqLocalizacao2').html(requisicao.localizacao);
            $('#reqFuel2').html(requisicao.fuel);
            $('#reqDataCriacao2').html(requisicao.dataCriacao);
            $('#reqDescricao2').html(requisicao.descricao);
            $('#inputId2').val(requisicao.id);
            $('.inputGroupPrioridade input').val(requisicao.prioridade);
            if (requisicao.prioridade === 1) {
                $('.inputGroupPrioridade button').html('Baixa <span class="caret"></span>');
            } else if (requisicao.prioridade === 2) {
                $('.inputGroupPrioridade button').html('M&eacute;dia <span class="caret"></span>');
            } else {
                $('.inputGroupPrioridade button').html('Alta <span class="caret"></span>');
            }
            $('#inputObservacao2').val(requisicao.observacao);
            $('.inputGroupEstado input').val(requisicao.estado);
            if (requisicao.estado === 2) {
                $('.inputGroupEstado button').html('Em execu&ccedil;&atilde;o <span class="caret"></span>');
            } else if (requisicao.estado === 3) {
                $('.inputGroupEstado button').html('Em espera <span class="caret"></span>');
            } else {
                $('.inputGroupEstado button').html('Conclu&iacute;do <span class="caret"></span>');
            }
            $('#requisicaoUpdate').modal('show');
        });
    });

    $(".formRelatorio").submit(function (e) {
        e.preventDefault();
        var dataPost = {
            grafico: $(".formRelatorio #inputGrafico").val(),
            data_inicio: $(".formRelatorio #inputDataInicio").val(),
            data_termino: $(".formRelatorio #inputDataTermino").val()
        };
        $.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            dataType: 'json',
            data: dataPost,
            success: function (data) {
                createChart(data);
            },
            cache: false
        });
        $("#chart").modal('show');
    });

    function createChart(data) {
        var options = {
            chart: {
                renderTo: 'container',
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            title: {},
            subtitle: {},
            tooltip: {
                pointFormat: '{series.name}: {point.y} (<b>{point.percentage:.1f}%</b>)'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    dataLabels: {
                        enabled: true,
                        format: '{point.name}'
                    }
                }
            },
            series: [{
                    type: 'pie',
                    name: 'Quantidade'
                }]
        };

        options.title.text = data.title;
        options.subtitle.text = data.subtitle;
        options.series[0].data = data.data;
        var chart = new Highcharts.Chart(options);
    }
});