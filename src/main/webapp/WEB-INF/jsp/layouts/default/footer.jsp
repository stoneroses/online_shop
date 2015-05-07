<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<a href="${ctx}">Home</a>
|
<a href="${ctx}/about_us">About Us</a>
|
<a href="${ctx}/contact_us">Contact Us</a>
|

<shiro:user>
  Hi, 
  <!-- Single button -->
  <div class="btn-group">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
      <shiro:principal />
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <shiro:hasPermission name="image_list">
        <li><a href="${ctx}/images">Images Maintenance</a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="product_list">
        <li><a href="${ctx}/products">Products Maintenance</a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="category_list">
        <li><a href="${ctx}/categories">Categories Maintenance</a></li>
      </shiro:hasPermission>
      <li class="divider"></li>
      <shiro:hasPermission name="news_list">
        <li><a href="${ctx}/news">News Maintenance</a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="link_list">
        <li><a href="${ctx}/links">Link Maintenance</a></li>
      </shiro:hasPermission>
      <li class="divider"></li>
      <shiro:hasPermission name="contact_us_message_list">
        <li><a href="${ctx}/contact_us_messages">Contact Us Message Maintenance</a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="setting_list">
        <li><a href="${ctx}/settings">Settings Maintenance</a></li>
      </shiro:hasPermission>
      <li class="divider"></li>
      <shiro:hasPermission name="permission_list">
        <li><a href="${ctx}/permissions">Permissions Maintenance</a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="role_list">
        <li><a href="${ctx}/roles">Roles Maintenance</a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="user_list">
        <li><a href="${ctx}/users">Users Maintenance</a></li>
      </shiro:hasPermission>
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
