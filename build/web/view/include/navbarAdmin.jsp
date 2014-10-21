<nav class="navbar navbar-fixed-top color-uel" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="${pageContext.servletContext.contextPath}/"><img src="${pageContext.servletContext.contextPath}/files/uel.png" class="img-responsive" alt="UEL" height="100px" width="180px"/></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.servletContext.contextPath}/tecnico/create">Cadastrar técnico</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>