<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<a href="${ctx}">Home</a> | 
<a href="${ctx}/about_us">About Us</a> | 
<a href="${ctx}/contact_us">Contact Us</a> | 

<shiro:user>
  Hi, 
  <!-- Single button -->
  <div class="btn-group">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
      <shiro:principal />
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="${ctx}/news">News</a></li>
      <li><a href="${ctx}/categories">Category</a></li>
      <li class="divider"></li>
      <li><a href="${ctx}/permissions">Permissions</a></li>
      <li><a href="${ctx}/roles">Roles</a></li>
      <li><a href="${ctx}/users">Users</a></li>
      <li class="divider"></li>
      <li><a href="${ctx}/users/profile">Profile</a></li>
      <li><a href="${ctx}/users/change_password">Change Password</a></li>
      <li class="divider"></li>
      <li><a href="${ctx}/logout">LOGOUT</a></li>
    </ul>
  </div>
</shiro:user>
<shiro:guest>
  <a href="${ctx}/login">LOGIN</a>
</shiro:guest>
