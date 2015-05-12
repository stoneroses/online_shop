<%@ page contentType="text/html"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="500" />
<tiles:insertDefinition name="error/404" />
