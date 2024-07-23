<#import "/layout/layout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<div class="p-5 mb-4 bg-body-tertiary rounded-3">
<div class="container-fluid py-5">
	<#if model??>
	<table class="summary_table">
		<tr class="summary_tr">
			<td class="summary_td">no.</td>
			<td class="summary_td">제목</td>
			<td class="summary_td">내용</td>
			<td class="summary_td">링크</td>
			<td class="summary_td">history</td>
		</tr>
		<#list model.summaryList as summary>
		<tr class="summary_tr" onClick="location.href='#'">
			<td class="summary_td">1</td>
			<td class="summary_td">${summary.msTitle}</td>
			<td class="summary_td">${summary.msSummary}</td>
			<td class="summary_td"><a href="${summary.msUrl}">${summary.msUrl}</a></td>
			<td class="summary_td">${summary.history}</td>
		</tr>
		</#list>
	</table>
	</#if>
</div>
</div>

</@layout.myLayout>
