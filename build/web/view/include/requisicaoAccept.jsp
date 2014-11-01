<div class="modal fade" id="requisicaoAccept" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Atendimento de requisição</h4>
            </div>
            <form role="form" action="${pageContext.servletContext.contextPath}/requisicao/accept" method="POST">
                <div class="modal-body" style="margin: 0 auto; width: 90%">
                    <div class="row">
                        <div class="col-md-6">
                            <h5>
                                <b>Remetente: </b>
                                <div id="reqRemetente"></div>
                            </h5>
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
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="hidden" class="form-control" id="inputId" name="id">
                            </div>
                            <div class="form-group">
                                <label for="inputPrioridade">Prioridade</label>
                                <div class="input-group">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Selecione uma prioridade <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li data-id="1"><a href="#">Baixa</a></li>
                                        <li data-id="2"><a href="#">Média</a></li>
                                        <li data-id="3"><a href="#">Alta</a></li>
                                    </ul>
                                    <input type="hidden" class="form-control category" id="inputPrioridade" name="prioridade" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputObservacao">Observação</label>
                                <textarea class="form-control" id="inputObservacao" placeholder="Insira uma observação a ser enviada ao remetente" name="observacao" rows="3" style="resize: none"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="form-group">
                        <button type="submit" class="btn btn-uel">Aceitar</button>
                        <button type="button" class="btn btn-default modal-btn-uel" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>