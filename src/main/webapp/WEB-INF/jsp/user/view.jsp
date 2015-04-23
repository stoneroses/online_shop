<h1>View User page</h1>
<p>${user.name}</p>
<p>${user.description}</p>
<a href="${ctx}/users">User list</a>
<a href="${ctx}/users/${user.id}/edit">Edit</a>
<a href="${ctx}/users/${user.id}/delete">Delete</a>
