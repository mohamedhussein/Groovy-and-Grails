
<%@ page import="placement.Application" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-application" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-application" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list application">
			
				<g:if test="${applicationInstance?.applicationname}">
				<li class="fieldcontain">
					<span id="applicationname-label" class="property-label"><g:message code="application.applicationname.label" default="Applicationname" /></span>
					
						<span class="property-value" aria-labelledby="applicationname-label"><g:fieldValue bean="${applicationInstance}" field="applicationname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationInstance?.placement}">
				<li class="fieldcontain">
					<span id="placement-label" class="property-label"><g:message code="application.placement.label" default="Placement" /></span>
					
						<span class="property-value" aria-labelledby="placement-label"><g:link controller="placementOportunity" action="show" id="${applicationInstance?.placement?.id}">${applicationInstance?.placement?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${applicationInstance?.student}">
				<li class="fieldcontain">
					<span id="student-label" class="property-label"><g:message code="application.student.label" default="Student" /></span>
					
						<span class="property-value" aria-labelledby="student-label"><g:link controller="student" action="show" id="${applicationInstance?.student?.id}">${applicationInstance?.student?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${applicationInstance?.id}" />
					<g:link class="edit" action="edit" id="${applicationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
