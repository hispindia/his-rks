<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage KRS" otherwise="/login.htm" redirect="/module/rks/categoryMoney.form" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="../includes/js_css.jsp" %>
<h2><spring:message code="rks.categoryMoney.manage"/></h2>

<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span>
</c:forEach>
<spring:bind path="categoryMoney">
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
<input type="hidden" id="pageId" value="categoryMoneyPage" />
<form method="post" class="box" id="categoryMoneyForm">
<spring:bind path="categoryMoney.id">
	<input type="hidden" name="${status.expression}" id="${status.expression}" value="${status.value}" />
</spring:bind>
<table>
	
	
	<tr>
		<td valign="top"><spring:message code="rks.categoryMoney.subCategory"/><em>*</em></td>
		<td>
			<spring:bind path="categoryMoney.subCategory">
			<select name="${status.expression}" id="${status.expression}"  tabindex="20" >
				<option value=""></option>
                <c:forEach items="${subCategories}" var="category">
                    <option value="${category.id}" <c:if test="${category.id == categoryMoney.subCategory.id }">selected</c:if> >(${category.parent.name})-${category.name}</option>
                </c:forEach>
   			</select>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td valign="top"><spring:message code="rks.categoryMoney.transactionType"/><em>*</em></td>
		<td>
			<spring:bind path="categoryMoney.transactionType">
			<select name="${status.expression}" id="${status.expression}"  >
				<option value=""></option>
                <c:forEach items="${transactionTypes}" var="transactionType">
                    <option value="${transactionType}" <c:if test="${transactionType == categoryMoney.transactionType }">selected</c:if> >${transactionType}</option>
                </c:forEach>
   			</select>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="rks.categoryMoney.dateIncomeOutcome"/><em>*</em></td>
		<td>
			<spring:bind path="categoryMoney.dateIncomeOutcome">
				
				<input type="text" name="${status.expression}" id="${status.expression}" value="${status.value}" class="date-pick left" readonly="readonly"  ondblclick="this.value='';"/>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="rks.categoryMoney.amount"/><em>*</em></td>
		<td>
			<spring:bind path="categoryMoney.amount">
				
				<input type="text" name="${status.expression}" id="${status.expression}" value="${status.value}" size="15" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td><spring:message code="rks.categoryMoney.description"/></td>
		<td>
			<spring:bind path="categoryMoney.description">
				<textarea rows="2" style="width:400px;"  name="${status.expression}" id="${status.expression}"  >${status.value}</textarea>
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
</table>
<br />
<input type="submit" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.save"/>">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="general.cancel"/>" onclick="ACT.go('categoryMoneyList.form');">
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>