<%@ page import="br.com.linuxgames.util.Constantes" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<mtw:requiresAuthentication cache="no" redir="true"/>
<mtw:requiresAuthorization groups="admins"/>
<c:set var="buscaUm"><%=String.valueOf(Constantes.ACTION_BUSCA)%></c:set>
<c:import url="cabecalho.jsp"/>
<html>
<body>
<div class="formCadastro">
 <h1 class="cadastro"><mtw:i18n key="colaborador.titulo"/></h1>

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <form name="formulario"  id="formulario" action="colaborador.atualiza.action" method="post">
 </c:when>
 <c:otherwise>
  <form name="formulario" id="formulario" action="colaborador.novo.action" method="post">
 </c:otherwise>
</c:choose>

 <div class="row">
  <span class="label">
   <mtw:i18n key="colaborador.nome"/>:
  </span>
  <span class="form">
   <mtw:select name="id" list="USUARIOS" />
    <mtw:hasError>
     <font class="errorMessage"><mtw:error field="id" /></font>
    </mtw:hasError>
   </span>   
 </div>

 <div class="row">
  <span class="label">
   <mtw:i18n key="colaborador.email"/>:
  </span>
  <span class="form">
   <mtw:inputText name="email" /> 
    <mtw:hasError>
     <font class="errorMessage"><mtw:error field="email" /></font>
    </mtw:hasError>
   </span>   
 </div>
 
 <div class="row">
  <span class="label">
   <mtw:i18n key="colaborador.admin"/>:
  </span>
  <span class="form">
   <mtw:radiobuttons name="admin" list="simOuNao" />
    <mtw:hasError>
     <font class="errorMessage"><mtw:error field="admin" /></font>
    </mtw:hasError>
   </span>   
 </div>

 <div class="row"> 
  <span class="label">
   <mtw:i18n key="colaborador.status"/>:
  </span>
  <span class="form">
   <mtw:select name="ativo" list="ativadoOuDesativado" />
    <mtw:hasError>
     <font class="errorMessage"><mtw:error field="ativo" /></font>
    </mtw:hasError>
   </span>   
 </div>
 
 <div class="spacer">
  &nbsp;
  </div>

 <div class="row">
  <span class="botoes">

<c:choose>
 <c:when test="${actionCRUD == buscaUm}">
  <input type="submit" name="tipoCRUD" value="Alterar" />
  <mtw:input type="hidden" name="id" />
  <input type="hidden" name="id_old" value="<mtw:out value="id"/>" />
  <input type="submit" name="tipoCRUD" value="Remover" /></form>
  <form name="formulario" action="colaborador.action" method="post"><input type="submit" name="tipoCRUD" value="Voltar" />
 </span>
</div>

</div>
 </c:when>
 <c:otherwise>
   <input type="submit" value="Cadastrar" />
  </span>
 </div>
</div>

<br><br>
<div class="formCadastro">

 <h1 class="cadastro"><mtw:i18n key="colaborador.lista"/></h1>
<c:choose>
 <c:when test="${empty colaboradores}">
  <mtw:i18n key="aplicacao.resultado.nenhumRegistro"/>
 </c:when>
 <c:otherwise>
	<c:forEach items="${colaboradores}" var="linha" varStatus="status">
     <ul id="resultlist">
      <li>
        <a href="colaborador.buscaUm.action?id=<c:out value="${linha.id}"/>">
         <c:out value="${linha.email}"/></a> -
         <c:choose>
          <c:when test="${linha.ativo}">
           <font color="green"><mtw:i18n key="colaborador.status.ativo"/></font>
          </c:when>
          <c:otherwise>
           <font color="red"><mtw:i18n key="colaborador.status.inativo"/></font>
          </c:otherwise>
         </c:choose>
         <c:choose>
          <c:when test="${linha.admin}">
           &nbsp;&nbsp;<font color="red">[<mtw:i18n key="colaborador.admin"/>]</font>
          </c:when>
          <c:otherwise>
          </c:otherwise>
         </c:choose>
         
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ <a href="colaborador.remove.action?id=<c:out value="${linha.id}"/>">X</a> ]
      </li>
     </ul>
      <c:set var="total"><c:out value="${status.count}"/></c:set>
	</c:forEach>
 </c:otherwise>
</c:choose>
 <h1 class="cadastro"><mtw:i18n key="aplicacao.total"/>&nbsp;<c:out value="${total}"/>&nbsp;<mtw:i18n key="aplicacao.registros"/></h1>
</div>
 </c:otherwise>
</c:choose>

 <div class="spacer">
  &nbsp;
  </div>
<div class="formCadastro">
 <div class="row"> 
  <b><mtw:i18n key="colaborador.ativos"/></b>:
  <mtw:list value="colabAtivos"><mtw:loop var="colab"><mtw:out value="colab.key"/>&nbsp;</mtw:loop> </mtw:list> 
 </div>
 <div class="row"> 
  <b><mtw:i18n key="colaborador.admins"/></b>:
  <mtw:list value="admins"><mtw:loop var="admin"><mtw:out value="admin.key"/>&nbsp;</mtw:loop> </mtw:list> 
 </div>
</div>

</form>
</body>
</html>