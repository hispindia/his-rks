<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage KRS" otherwise="/login.htm" redirect="/module/rks/category.form" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<h2><spring:message code="rks.category.manage"/></h2>

<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span>
</c:forEach>
<spring:bind path="category">
<c:if test="${not empty  status.errorMessages}">
<div class="error">
<ul>
<c:forEach items="${status.errorMessages}" var="error">
    <li>${error}</li>   
</c:forEach>
</ul>
</div>
</c:if>
</spring:bind>
<input type="hidden" id="pageId" value="categoryPage" />
<form method="post" class="box" id="categoryForm">
<spring:bind path="category.id">
	<input type="hidden" name="${status.expression}" id="${status.expression}" value="${status.value}" />
</spring:bind>
<table>
	<tr>
		<td><spring:message code="general.name"/><em>*</em></td>
		<td>
			<spring:bind path="category.name">
				
				<input type="text" name="${status.expression}" id="${status.expression}" value="${status.value}" size="60" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td valign="top"><spring:message code="rks.category.description"/></td>
		<td>
			<spring:bind path="category.description">
				<textarea rows="2"  name="${status.expression}" id="${status.expression}" style="width:400px;"  >${status.value}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td valign="top"><spring:message code="rks.category.parent"/></td>
		<td>
			<spring:bind path="category.parent">
			<select name="${status.expression}" id="${status.expression}"  tabindex="20" >
				<option value=""></option>
                <c:forEach items="${parents}" var="vparent">
                    <option value="${vparent.id}" <c:if test="${vparent.id == category.parent.id }">selected</c:if> >${vparent.name}</option>
                </c:forEach>
   			</select>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="general.retired" /></td>
		<td>
			<spring:bind path="category.retired">
				<openmrs:fieldGen type="java.lang.Boolean" formFieldName="${status.expression}" val="${status.value}" parameters="isNullable=false" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
</table>
<br />
<input type="submit" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.save"/>">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.cancel"/>" onclick="ACT.go('categoryList.form');">
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>