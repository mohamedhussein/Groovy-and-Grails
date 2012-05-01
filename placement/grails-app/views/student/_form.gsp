<%@ page import="placement.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'studentname', 'error')} ">
	<label for="studentname">
		<g:message code="student.studentname.label" default="Studentname" />
		
	</label>
	<g:textField name="studentname" value="${studentInstance?.studentname}"/>
</div>

