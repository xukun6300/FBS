<%@ page import="sg.com.fbs.core.techinfra.util.DateUtil" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="netui" uri="/tags/netui" %>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="block-center span12">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h4>Success!</h4>
				<p>Account has been saved successfully!</p>
			</div>

			<div class="well clearfix form-horizontal">
				<legend>Account Preview</legend>
				<div class="control-group">
					<label class="control-label" style="width:170px;font-weight: bold"><spring:message code="fbs.common.account.ui.label.account.code"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">${command.accountCode}</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:170px;font-weight: bold"><spring:message code="fbs.common.account.ui.label.account.desc"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">${command.accountDesc}</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:170px;font-weight: bold"><spring:message code="fbs.common.account.ui.label.account.fy"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px"><%=String.valueOf(DateUtil.getCurrentYear()) %></label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:170px;font-weight: bold"><spring:message code="fbs.common.account.ui.label.account.need.requisition.form"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">
					<c:choose>
					   <c:when test="${command.needRequisitionForm}">
					       Yes
					   </c:when>
					   <c:otherwise>
					       No
					   </c:otherwise>
					</c:choose>
					</label>
				</div>
				
				<div class="control-group">
					<label class="control-label" style="width:170px;font-weight: bold"><spring:message code="fbs.common.account.ui.label.account.spend.period"/></label>
					<label class="control-label" style="width:auto;text-align:left;padding-left:20px">${command.acctSpendingPeriod}</label>
				</div>
				
				  <table id="accountTb" class="table ftable table-bordered table-hover table-condensed">
                    <thead>
                      <tr>
                        <c:forEach items="${command.acctStructures}" var="acctColumn">
                           <th width="${acctColumn.columnSize}">${acctColumn.columnName}</th>                            
                        </c:forEach>                      
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <c:forEach items="${command.acctStructures}" var="acctColumn">
                           <td>
	                           <c:choose>
	                              <c:when test="${acctColumn.columnType =='T'}">
	                                Test
	                              </c:when>
	                              <c:when test="${acctColumn.columnType =='N'}">
	                                100
	                              </c:when>
	                              <c:otherwise>
	                                01/01/2000
	                              </c:otherwise>
	                           </c:choose>
                           </td>
                        </c:forEach>                      
                       </tr>
                    </tbody>
				  </table>
				
			</div>
			
			
			
			
			
		</div>
	</div>
</div>