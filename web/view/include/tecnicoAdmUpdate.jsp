<div class="modal fade" id="tecnicoAdmUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Edi��o de t�cnico</h4>
            </div>
            <form role="form" action="${pageContext.servletContext.contextPath}/tecnico/adm_update" method="POST">
                <div class="modal-body" style="margin: 0 auto; width: 55%">
                    <div class="form-group">
                        <input type="hidden" class="form-control" id="inputId" name="id">
                    </div>
                    <div class="form-group">
                        <label for="inputChapa">Chapa funcional</label>
                        <input type="text" class="form-control" id="inputChapa" placeholder="Insira seu n�mero de chapa funcional" name="chapa" required="true" maxlength="20" pattern="[0-9]{6,20}">
                        <span class="help-block text-right">M�nimo 6 d�gitos.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputSenha">Senha</label>
                        <input type="password" class="form-control" id="inputSenha" placeholder="Insira sua senha" name="senha" required="true" maxlength="20" pattern=".{6,20}">
                        <span class="help-block text-right">M�nimo 6 caracteres.</span>
                    </div>
                    <div class="form-group">
                        <label for="inputNome">Nome</label>
                        <input type="text" class="form-control" id="inputNome" placeholder="Insira seu nome" name="nome" required="true" maxlength="40">
                    </div>
                    <div class="form-group">
                        <label for="inputLocal">Departamento (opcional)</label>
                        <div class="input-group inputGroupLocal">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Selecione um departamento (opcional) <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="#">Departamento de Computa��o</a></li>
                                <li><a href="#">Departamento de Estat�stica</a></li>
                                <li><a href="#">Departamento de F�sica</a></li>
                                <li><a href="#">Departamento de Geografia</a></li>
                                <li><a href="#">Departamento de Qu�mica</a></li>
                            </ul>
                            <input type="hidden" class="form-control category" id="inputLocal" name="departamento">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail">E-mail</label>
                        <input type="text" class="form-control" id="inputEmail" placeholder="Insira seu e-mail" name="email" required="true" maxlength="40">
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