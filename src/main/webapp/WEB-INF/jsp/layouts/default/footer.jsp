<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<a href="${ctx}">Home</a>
<a href="${ctx}/about_us">About Us</a>
<a href="${ctx}/contact_us">Contact Us</a>

<shiro:user>
  Hi, <shiro:principal/> <a href="${ctx}/logout" >LOGOUT</a>
</shiro:user>
<shiro:guest>
  <a href="${ctx}/login" >LOGIN</a>
</shiro:guest>
