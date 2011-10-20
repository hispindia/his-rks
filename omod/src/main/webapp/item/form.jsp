 <%--
 *  Copyright 2009 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of RKS module.
 *
 *  RKS module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  RKS module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with RKS module.  If not, see <http://www.gnu.org/licenses/>.
 *
--%> 
<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage KRS" otherwise="/login.htm" redirect="/module/rks/item.form" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<h2><spring:message code="rks.item.manage"/></h2>

<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span>
</c:forEach>
<spring:bind path="item">
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
<input type="hidden" id="pageId" value="itemPage" />
<form method="post" class="box" id="itemForm">
<spring:bind path="item.id">
	<input type="hidden" name="${status.expression}" id="${status.expression}" value="${status.value}" />
</spring:bind>
<table>
	
	
	<tr>
		<td valign="top"><spring:message code="rks.item.category"/><em>*</em></td>
		<td>
			<spring:bind path="item.category">
			<select name="${status.expression}" id="${status.expression}"  tabindex="20" >
				<option value=""></option>
                <c:forEach items="${subCategories}" var="category">
                    <option value="${category.id}" <c:if test="${category.id == item.category.id }">selected</c:if> > ${category.name}</option>
                </c:forEach>
   			</select>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td valign="top"><spring:message code="rks.item.transactionType"/><em>*</em></td>
		<td>
			<spring:bind path="item.transactionType">
			<select name="${status.expression}" id="${status.expression}"  >
				<option value=""></option>
                <c:forEach items="${transactionTypes}" var="transactionType">
                    <option value="${transactionType}" <c:if test="${transactionType == item.transactionType }">selected</c:if> >${transactionType}</option>
                </c:forEach>
   			</select>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="rks.item.dateIncomeOutcome"/><em>*</em></td>
		<td>
			<spring:bind path="item.dateIncomeOutcome">
				
				<input type="text" name="${status.expression}" id="${status.expression}" value="${status.value}" class="date-pick left" readonly="readonly"  ondblclick="this.value='';"/>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="rks.item.amount"/><em>*</em></td>
		<td>
			<spring:bind path="item.amount">
				
				<input type="text" name="${status.expression}" id="${status.expression}" value="${status.value}" size="15" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="rks.item.description"/></td>
		<td>
			<spring:bind path="item.description">
				<textarea rows="2" style="width:400px;"  name="${status.expression}" id="${status.expression}"  >${status.value}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
</table>
<br />
<input type="submit" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.save"/>">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.cancel"/>" onclick="ACT.go('itemList.form');">
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>