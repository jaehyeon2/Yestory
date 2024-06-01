<#macro myLayout title="FreeMarker index">
<!DOCTYPE html>

<#include "header.ftl"/>
<body>

<#include "navi.ftl"/>

<#nested/>

<#include "footer.ftl"/>

</body>
</html>

</#macro>