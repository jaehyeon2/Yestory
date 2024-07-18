<#import "/layout/layout.ftl" as layout>
<#import "/spring.ftl" as spring/>
<@layout.myLayout>

<div class="p-5 mb-4 bg-body-tertiary rounded-3">
<div class="container-fluid py-5">
	<form action="/admin/trend" method="get">
		<input type="date" name="history"/>
		<button type="submit">조회</button>
	</form>

	<#if model??>
	<table class="trend_table">
		<tr class="trend_tr">
			<td class="trend_td">no.</td>
			<td class="trend_td">트렌드명</td>
			<td class="trend_td">history</td>
		</tr>
		<#list model.trendList as trend>
		<tr class="trend_tr" onClick="location.href='/admin/summary?mtTrend=${trend.mtTrend}&history=${trend.history}'">
			<td class="trend_td">1</td>
			<td class="trend_td">${trend.mtTrend}</td>
			<td class="trend_td">${trend.history}</td>
		</tr>
		</#list>
	</table>
	</#if>
</div>
</div>

</@layout.myLayout>
