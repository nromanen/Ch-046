<%@ tag pageEncoding="UTF-8" %>
<%@attribute name="title" type="java.lang.String" %>


<div class="alert alert-danger" role="alert">
    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
    <span class="sr-only">Error:</span>
    ${title}
</div>