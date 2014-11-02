<div class="modal fade" id="requisicaoUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Edição de requisição</h4>
            </div>
            <form role="form" action="${pageContext.servletContext.contextPath}/requisicao/update" method="POST">
                <div class="modal-body" style="margin: 0 auto; width: 90%">
                    <div class="row">
                        <div class="col-md-6">
                            <h5>
                                <b>Remetente: </b>
                                <div id="reqRemetente2"></div>
                            </h5>
                            <h5>
                                <b>Tipo: </b>
                                <div id="reqTipo2"></div>
                            </h5>
                            <h5>
                                <b>Localização: </b>
                                <div id="reqLocalizacao2"></div>
                            </h5>
                            <h5>
                                <b>FUEL: </b>
                                <div id="reqFuel2"></div>
                            </h5>
                            <h5>
                                <b>Data de criação: </b>
                                <div id="reqDataCriacao2"></div>
                            </h5>
                            <h5>
                                <b>Descrição: </b>
                                <div id="reqDescricao2"></div>
                            </h5>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <input type="hidden" class="form-control" id="inputId2" name="id">
                            </div>
                            <div class="form-group">
                                <label for="inputPrioridade2">Prioridade</label>
                                <div class="input-group inputGroupPrioridade">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Selecione uma prioridade <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li data-id="1"><a href="#">Baixa</a></li>
                                        <li data-id="2"><a href="#">Média</a></li>
                                        <li data-id="3"><a href="#">Alta</a></li>
                                    </ul>
                                    <input type="hidden" class="form-control category" id="inputPrioridade2" name="prioridade" required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputObservacao2">Observação</label>
                                <textarea class="form-control" id="inputObservacao2" placeholder="Insira uma observação a ser enviada ao remetente" name="observacao" rows="3" style="resize: none"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="inputEstado2">Estado</label>
                                <div class="input-group inputGroupEstado">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Selecione um estado <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li data-id="2"><a href="#">Em execução</a></li>
                                        <li data-id="3"><a href="#">Em espera</a></li>
                                        <li data-id="4"><a href="#">Concluído</a></li>
                                    </ul>
                                    <input type="hidden" class="form-control category" id="inputEstado2" name="estado" required="true">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="form-group">
                        <button type="submit" class="btn btn-uel">Salvar</button>
                        <button type="button" class="btn btn-default modal-btn-uel" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>