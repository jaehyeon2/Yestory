<#import "/layout/layout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<div class="p-5 mb-4 bg-body-tertiary rounded-3">
<div class="container-fluid py-5">
	<#if model??>
	<table>
		<tr>
			<td>no.</td>
			<td>트렌드명</td>
			<td>history</td>
		</tr>
		<#list model.trendList as trend>
		<tr>
			<td>1</td>
			<td>${trend.mtTrend}</td>
			<td>${trend.history}</td>
		</tr>
		</#list>
	</table>
	</#if>
</div>
</div>

</@layout.myLayout>
