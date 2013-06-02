<%@ taglib uri="http://www.mentaframework.org/tags-mtw/" prefix="mtw" %>
<mtw:useI18N />
<html>
<head>
 <title><mtw:i18n key="login.titulo"/></title>
</head>
<body style="background-color: #e1f3d6;background-repeat:no-repeat;">

			<table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="100">&nbsp;</td>
				</tr>
		  		<tr>
			      	<td width="500" height="240" background="/img/login.jpg">
			      		<div align="center">
			      			<form action="/games/Login" method="post">
				        		<table width="50%" border="0" cellspacing="2" cellpadding="2">

									<tr>
										<td style="width: 30%;" class="texto">
											&nbsp;
							        	</td>
										<td class="texto">
											<mtw:i18n key="login.usuario"/>
										</td>
										<td class="texto">
											<input type="text" name="usuario" id="usuario" size="10">
							        	</td>
							        </tr>

							        <tr>
								        <td style="width: 30%;" class="texto">
											&nbsp;
							        	</td>
							        	<td class="texto">
											<mtw:i18n key="login.senha"/>
										</td>

										<td class="texto">
											<input type="password" name="senha" size="10">
							        	</td>
							        </tr>

				          			<tr>
				          				<td style="width: 30%;" class="texto">&nbsp;
							        	</td>
				            			<td>&nbsp;</td>

				            			<td>
				                			<input type="submit" value="Entrar">
				              			</td>
				          			</tr>
				        		</table>
			        		</form>
			        	</div>
		  			</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>






		</body>
	</html>
 <script language="JavaScript">
  document.getElementById('usuario').focus();
 </script>