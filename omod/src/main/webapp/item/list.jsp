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

<openmrs:require privilege="View RKS" otherwise="/login.htm" redirect="/module/rks/itemList.form" />

<spring:message var="pageTitle" code="rks.item.manage" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="../includes/nav.jsp" %>

<h2><spring:message code="rks.item.manage"/></h2>	

<br />
<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><
</c:forEach>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code='rks.item.add'/>" onclick="ACT.go('item.form');"/>

<br /><br />
<input type="hidden" id="pageId" value="itemListPage" />
<form method="post" onsubmit="return false" id="form">
<table cellpadding="5" cellspacing="0"  >
	<tr>
		<td>
		<spring:message code="rks.item.category"/>
		
			<select name="categoryId" id="categoryId"  >
				<option value=""></option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" <c:if test="${category.id == categoryId }">selected</c:if> >${category.name}</option>
                </c:forEach>
   			</select>
		
		<spring:message code="rks.item.transactionType"/>
	
			<select name="transactionType" id="transactionType"  >
				<option value=""></option>
                <c:forEach items="${transactionTypes}" var="Type">
                    <option value="${Type}" <c:if test="${Type == transactionType }">selected</c:if> >${Type}</option>
                </c:forEach>
   			</select>
		
		<spring:message code="rks.fromDate"/>
		<input type="text" id="fromDate" class="date-pick left" readonly="readonly" name="fromDate" value="${fromDate}" title="Double Click to Clear" ondblclick="this.value='';"/>
		<spring:message code="rks.toDate"/>
		<input type="text" id="toDate" class="date-pick left" readonly="readonly" name="toDate" value="${toDate}" title="Double Click to Clear" ondblclick="this.value='';"/>
		
		<spring:message code="general.name"/>
		<input type="text" id="searchName" name="searchName" value="${searchName}" />
		<input type="button" value="Search"  class="ui-button ui-widget ui-state-default ui-corner-all" onclick="RKS.searchItem(this);"/>
		
		<input type="button" value="Excel"  class="ui-button ui-widget ui-state-default ui-corner-all" onclick="RKS.exportData(this);"/>
		 
		</td>
	</tr>
</table>
<span class="boxHeader"><spring:message code="rks.item.list"/></span>
<div class="box">
<c:choose>
<c:when test="${not empty items}">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="RKS.checkValue();" value="<spring:message code='rks.deleteSelected'/>"/>
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
	<th>#</th>
	<th><spring:message code="rks.item.category"/></th>
	<th><spring:message code="rks.item.transactionType"/></th>
	<th><spring:message code="rks.item.dateIncomeOutcome"/></th>
	<th><spring:message code="rks.item.amount"/></th>
	<th><spring:message code="rks.item.description"/></th>
	<th><spring:message code="rks.item.createdOn"/></th>
	<th><spring:message code="rks.item.createdBy"/></th>
	<th></th>
</tr>
<c:forEach items="${items}" var="item" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>	
		<td><a href="#" onclick="ACT.go('item.form?itemId=${ item.id}');">${item.category.name}</a> </td>
		<td>${item.transactionType}</td>
		<td><openmrs:formatDate date="${item.dateIncomeOutcome}" type="textbox"/></td>
		<td>${item.amount}</td>
		<td>${item.description}</td>
		<td><openmrs:formatDate date="${item.createdOn}" type="textbox"/></td>
		<td>${item.createdBy}</td>
		<td><input type="checkbox" name="ids" value="${item.id}"/></td>
	</tr>
</c:forEach>

<tr class="paging-container">
	<td colspan="9"><%@ include file="../paging.jsp" %></td>
</tr>
</table>
</c:when>
<c:otherwise>
	No item found.
</c:otherwise>
</c:choose>
</div>
</form>



<%@ include file="/WEB-INF/template/footer.jsp" %>
