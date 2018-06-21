<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="util.BoUtil"%>
<%@ page import="util.ReadGraphAPIFeed"%>

<html>
<head>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-classic/resources/theme-classic-all.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/packages/charts/classic/charts.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/packages/charts/classic/classic/resources/charts-all.css"
	rel="stylesheet" />
</head>


<body>
		<script type="text/javascript">
			Ext.onReady(function() {
				Ext.create('Ext.chart.CartesianChart', {
					renderTo : document.body,
					width : 500,
					height : 200,

					store : {
						fields : [ 'name', 'g1', 'g2', 'g3' ],
						data : [ {
							"name" : "02-04",
							"g1" : 57,
							"g2" : 59,
							"g3" : 29
						}, {
							"name" : "04-06",
							"g1" : 45,
							"g2" : 50,
							"g3" : 29
						}, {
							"name" : "06-08",
							"g1" : 67,
							"g2" : 43,
							"g3" : 19
						}, {
							"name" : "08-10",
							"g1" : 45,
							"g2" : 18,
							"g3" : 199
						}, {
							"name" : "10-12",
							"g1" : 30,
							"g2" : 90,
							"g3" : 50
						} ]
					},
					legend : {
						docked : 'bottom'
					},

					//define x and y axis.
					axes : [ {
						type : 'numeric',
						position : 'left',
						title : 'Number of Hits'
					}, {
						type : 'category',
						visibleRange : [ 0, 1 ],
						position : 'bottom',
						title : 'Dominant hours'
					} ],

					//define the actual series
					series : [ {
						type : 'line',
						xField : 'name',
						yField : 'g1',
						title : 'Likes'
					}, {
						type : 'line',
						xField : 'name',
						yField : 'g2',
						title : 'Shares'
					}, {
						type : 'line',
						xField : 'name',
						yField : 'g3',
						title : 'Comments'
					} ]
				});
			});
		</script>

	<%-- START --%>
	<%
		BoUtil boUtil = new BoUtil();
		Map<String, Map<String, List<String>>> daysMap = new HashMap<String, Map<String, List<String>>>();
		Map<String, Map<String, Integer>> finalCountMap = new HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Integer>> finalCountAfterWeightageMap = new HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Map<String, Integer>>> dayMap = new HashMap<String, Map<String, Map<String, Integer>>>();

		ReadGraphAPIFeed graphAPI = new ReadGraphAPIFeed();
		List<String> commentsList = graphAPI.getCommentsList();
		List<String> sharesList = graphAPI.getSharesList();
		List<String> likesList = graphAPI.getLikesList();

		boUtil.splitTheDatesIntoDaysList("Shares", sharesList, daysMap);
		boUtil.splitTheDatesIntoDaysList("Likes", likesList, daysMap);
		boUtil.splitTheDatesIntoDaysList("Comments", commentsList, daysMap);

		//System.out.println("----------------daysMap : ");
		//System.out.println(daysMap);

		boUtil.findMostActiveTimeSlots(daysMap, finalCountMap, finalCountAfterWeightageMap, dayMap);

		String finalJsonObj = graphAPI.convertMaptoJsonObj(dayMap);
		System.out.println("------Final Json Object : \n" + finalJsonObj);
		out.println(finalJsonObj);
	%>
	<%-- END --%>

</body>
</html>