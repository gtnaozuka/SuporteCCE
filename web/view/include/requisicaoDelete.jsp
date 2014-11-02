<div class="modal fade" id="requisicaoDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Exclusão de requisição</h4>
            </div>
            <form role="form" action="${pageContext.servletContext.contextPath}/requisicao/delete" method="POST">
                <div class="modal-body" style="margin: 0 auto; width: 90%">
                    <div class="form-group">
                        <input type="hidden" class="form-control" id="inputId" name="id">
                    </div>
                    Deseja realmente excluir essa requisição?
                    <h5>
                        <b>Tipo: </b>
                        <div id="reqTipo"></div>
                    </h5>
                    <h5>
                        <b>Localização: </b>
                        <div id="reqLocalizacao"></div>
                    </h5>
                    <h5>
                        <b>FUEL: </b>
                        <div id="reqFuel"></div>
                    </h5>
                    <h5>
                        <b>Data de criação: </b>
                        <div id="reqDataCriacao"></div>
                    </h5>
                    <h5>
                        <b>Descrição: </b>
                        <div id="reqDescricao"></div>
                    </h5>
                </div>
                <div class="modal-footer">
                    <div class="form-group">
                        <button type="submit" class="btn btn-uel">Confirmar</button>
                        <button type="button" class="btn btn-default modal-btn-uel" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>