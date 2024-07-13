<#import "/layout/layout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<div class="p-5 mb-4 bg-body-tertiary rounded-3">
<div class="container-fluid py-5">
	<#if model??>
	<table>
		<tr>
			<td>no.</td>
			<td>제목</td>
			<td>내용</td>
			<td>링크</td>
			<td>history</td>
		</tr>
		<#list model.summaryList as summary>
		<tr onClick="location.href='#'">
			<td>1</td>
			<td>${summary.msTitle}</td>
			<td>${summary.msSummary}</td>
			<td><a href="${summary.msUrl}">${summary.msUrl}</a></td>
			<td>${summary.history}</td>
		</tr>
		</#list>
	</table>
	</#if>
</div>
</div>

</@layout.myLayout>
