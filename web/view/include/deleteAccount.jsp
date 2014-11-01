<div class="modal fade" id="deleteAccount" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Exclusão de perfil</h4>
            </div>
            <form role="form" action="${pageContext.servletContext.contextPath}/pessoa/delete" method="POST">
                <div class="modal-body" style="margin: 0 auto; width: 55%">
                    <div class="form-group">
                        <label for="inputSenhaAtual">Senha atual</label>
                        <input type="password" class="form-control" id="inputSenhaAtual" placeholder="Insira sua senha atual" name="senha_atual" required="true" maxlength="20">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-uel">Excluir</button>
                    <button type="button" class="btn btn-default modal-btn-uel" data-dismiss="modal">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>