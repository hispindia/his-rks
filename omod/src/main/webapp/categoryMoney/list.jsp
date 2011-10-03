<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="View categoryMoney" otherwise="/login.htm" redirect="/module/rks/categoryMoneyList.form" />

<spring:message var="pageTitle" code="rks.categoryMoney.manage" scope="page"/>

<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="../includes/nav.jsp" %>

<h2><spring:message code="rks.categoryMoney.manage"/></h2>	

<br />
<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><
</c:forEach>
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code='rks.categoryMoney.add'/>" onclick="ACT.go('categoryMoney.form');"/>

<br /><br />
<input type="hidden" id="pageId" value="categoryMoneyListPage" />
<form method="post" onsubmit="return false" id="form">
<table cellpadding="5" cellspacing="0"  >
	<tr>
		<td>
		<spring:message code="rks.categoryMoney.category"/>
		
			<select name="categoryId" id="categoryId"  >
				<option value=""></option>
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" <c:if test="${category.id == categoryId }">selected</c:if> >${category.name}</option>
                </c:forEach>
   			</select>
		
		<spring:message code="rks.categoryMoney.transactionType"/>
	
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
		<input type="button" value="Search"  class="ui-button ui-widget ui-state-default ui-corner-all" onclick="RKS.searchCategoryMoney(this);"/>
		
		<input type="button" value="Excel"  class="ui-button ui-widget ui-state-default ui-corner-all" onclick="RKS.exportData(this);"/>
		 
		</td>
	</tr>
</table>
<span class="boxHeader"><spring:message code="rks.categoryMoney.list"/></span>
<div class="box">
<c:choose>
<c:when test="${not empty categoryMoneys}">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" onclick="RKS.checkValue();" value="<spring:message code='rks.deleteSelected'/>"/>
<table cellpadding="5" cellspacing="0" width="100%">
<tr>
	<th>#</th>
	<th><spring:message code="rks.categoryMoney.subCategory"/></th>
	<th><spring:message code="rks.categoryMoney.category"/></th>
	<th><spring:message code="rks.categoryMoney.transactionType"/></th>
	<th><spring:message code="rks.categoryMoney.dateIncomeOutcome"/></th>
	<th><spring:message code="rks.categoryMoney.amount"/></th>
	<th><spring:message code="rks.categoryMoney.description"/></th>
	<th><spring:message code="rks.categoryMoney.createdOn"/></th>
	<th><spring:message code="rks.categoryMoney.createdBy"/></th>
	<th></th>
</tr>
<c:forEach items="${categoryMoneys}" var="categoryMoney" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } '>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>	
		<td><a href="#" onclick="ACT.go('categoryMoney.form?categoryMoneyId=${ categoryMoney.id}');">${categoryMoney.subCategory.name}</a> </td>
		<td>${categoryMoney.subCategory.parent.name}</td>
		<td>${categoryMoney.transactionType}</td>
		<td><openmrs:formatDate date="${categoryMoney.dateIncomeOutcome}" type="textbox"/></td>
		<td>${categoryMoney.amount}</td>
		<td>${categoryMoney.description}</td>
		<td><openmrs:formatDate date="${categoryMoney.createdOn}" type="textbox"/></td>
		<td>${categoryMoney.createdBy}</td>
		<td><input type="checkbox" name="ids" value="${categoryMoney.id}"/></td>
	</tr>
</c:forEach>

<tr class="paging-container">
	<td colspan="9"><%@ include file="../paging.jsp" %></td>
</tr>
</table>
</c:when>
<c:otherwise>
	No categoryMoney found.
</c:otherwise>
</c:choose>
</div>
</form>



<%@ include file="/WEB-INF/template/footer.jsp" %>
