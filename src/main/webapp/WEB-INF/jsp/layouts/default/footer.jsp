<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<a href="${ctx}"><spring:message code="page.footer.home" /></a>
|
<a href="${ctx}/about_us"><spring:message code="page.footer.about" /></a>
|
<a href="${ctx}/contact_us"><spring:message code="page.footer.contact" /></a>
|

<shiro:user>
  Hi, 
  <!-- Single button -->
  <div class="btn-group dropup">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
      <shiro:principal />
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <shiro:hasPermission name="contact_us_message_list">
        <li><a href="${ctx}/contact_us_messages"><spring:message
              code="page.menu.maintenance.contact.us.messages" /></a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="setting_list">
        <li><a href="${ctx}/settings"><spring:message code="page.menu.maintenance.settings" /></a></li>
      </shiro:hasPermission>
      <li class="divider"></li>
      <shiro:hasPermission name="permission_list">
        <li><a href="${ctx}/permissions"><spring:message code="page.menu.maintenance.permissions" /></a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="role_list">
        <li><a href="${ctx}/roles"><spring:message code="page.menu.maintenance.roles" /></a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="user_list">
        <li><a href="${ctx}/users"><spring:message code="page.menu.maintenance.users" /></a></li>
      </shiro:hasPermission>
      <li class="divider"></li>
      <li><a href="${ctx}/users/profile"><spring:message code="page.menu.profile" /></a></li>
      <li><a href="${ctx}/users/change_password"><spring:message code="page.menu.change.password" /></a></li>
      <li class="divider"></li>
      <li><a href="${ctx}/logout"><spring:message code="page.menu.log.out" /></a></li>
    </ul>
  </div>
  <div class="btn-group dropup">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
      Web site maintenance <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <shiro:hasPermission name="image_list">
        <li><a href="${ctx}/images"><spring:message code="page.menu.maintenance.images" /></a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="product_list">
        <li><a href="${ctx}/products"><spring:message code="page.menu.maintenance.products" /></a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="category_list">
        <li><a href="${ctx}/categories"><spring:message code="page.menu.maintenance.categories" /></a></li>
      </shiro:hasPermission>
      <li class="divider"></li>
      <shiro:hasPermission name="news_list">
        <li><a href="${ctx}/news"><spring:message code="page.menu.maintenance.news" /></a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="testimonial_list">
        <li><a href="${ctx}/testimonials"><spring:message code="page.menu.maintenance.testimonial" /></a></li>
      </shiro:hasPermission>
      <shiro:hasPermission name="link_list">
        <li><a href="${ctx}/links"><spring:message code="page.menu.maintenance.links" /></a></li>
      </shiro:hasPermission>
    </ul>
  </div>
</shiro:user>
<shiro:guest>
  <a href="${ctx}/login"><spring:message code="page.menu.log.in" /></a>
</shiro:guest>
