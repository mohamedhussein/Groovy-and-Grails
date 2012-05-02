<%@ page import="placement.Application" %>



<div class="fieldcontain ${hasErrors(bean: applicationInstance, field: 'applicationname', 'error')} ">
	<label for="applicationname">
		<g:message code="application.applicationname.label" default="Applicationname" />
		
	</label>
	<g:textField name="applicationname" value="${applicationInstance?.applicationname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationInstance, field: 'placement', 'error')} required">
	<label for="placement">
		<g:message code="application.placement.label" default="Placement" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="placement" name="placement.id" from="${placement.PlacementOportunity.list()}" optionKey="id" required="" value="${applicationInstance?.placement?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: applicationInstance, field: 'student', 'error')} required">
	<label for="student">
		<g:message code="application.student.label" default="Student" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="student" name="student.id" from="${placement.Student.list()}" optionKey="id" required="" value="${applicationInstance?.student?.id}" class="many-to-one"/>
</div>

