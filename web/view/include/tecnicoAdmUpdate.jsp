<div class="modal fade" id="tecnicoAdmUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Edição de técnico</h4>
            </div>
            <form class="form-uel" role="form" action="${pageContext.servletContext.contextPath}/tecnico/adm_update" method="POST">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="hidden" class="form-control" id="inputId" name="id">
                    </div>
                    <div class="form-group">
                        <label for="inputChapa">Chapa funcional</label>
                        <input type="text" class="form-control" id="inputChapa" placeholder="Insira seu número de chapa funcional" name="chapa" required="true" maxlength="20" pattern="[0-9]{6,20}">
                        <span class="help-block text-right">Mínimo 6 dígitos.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputSenha">Senha: <a class="btn btn-uel btn-xs" href="${pageContext.servletContext.contextPath}/pessoa/update_password">Alterar senha</a></label>
                    </div>
                    <div class="form-group">
                        <label for="inputNome">Nome</label>
                        <input type="text" class="form-control" id="inputNome" placeholder="Insira seu nome" name="nome" required="true" maxlength="20">
                    </div>
                    <div class="form-group">
                        <label for="inputDepartamento">Departamento (opcional)</label>
                        <input type="text" class="form-control" id="inputDepartamento" placeholder="Insira seu departamento (opcional)" name="departamento" required="true" maxlength="20">
                    </div>
                    <div class="form-group">
                        <label for="inputEmail">E-mail</label>
                        <input type="text" class="form-control" id="inputEmail" placeholder="Insira seu e-mail" name="email" required="true" maxlength="20">
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="form-group">
                        <div class="text-center">
                            <button type="submit" class="btn btn-uel">Salvar</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>