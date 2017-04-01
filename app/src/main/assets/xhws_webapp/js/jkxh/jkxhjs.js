$(document).ready(function(){
	
	
});

function addmuen(){
	$(".time-data-table tr:eq(3) td:eq(1)").remove();
//	$(".time-data-table tr:eq(3)").append("<td><div class='mod-data-text'>"+
//                       "<div class='img-wrap'>"+
//                            "<img class='img-response' src='images/icon-cfys.png' />"+
//                        "</div>"+
//                        "<div class='text-wrap'>"+
//                           " <h5>未知业务</h5>"+
//                           " <p><strong id='cfys'></strong>张</p>"+
//                        "</div>"+
//                    "</div>"+
//                    "</td>");
//	$(".time-data-table").append("<tr><td><div class='mod-data-text'>" +
//			"<div class='img-wrap'>" +
//			"<img class='img-response' src='images/icon-yjyjyqy.png' />"+
//            "</div> <div class='text-wrap'><h5>未知业务</h5><p>" +
//            "<strong id='11qy'>0</strong>人</p></div></div></td>" +
//            "<td><div class='mod-data-text'>"+
//                       "<div class='img-wrap'>"+
//                            "<img class='img-response' src='images/icon-cfys.png' />"+
//                        "</div>"+
//                        "<div class='text-wrap'>"+
//                           " <h5>未知业务</h5>"+
//                           " <p><strong id='cfys'></strong>张</p>"+
//                        "</div>"+
//                    "</div>"+
//                    "</td></tr>" +
//                    "<tr><td><div class='mod-data-text'>" +
//			"<div class='img-wrap'>" +
//			"<img class='img-response' src='images/icon-yjyjyqy.png' />"+
//            "</div> <div class='text-wrap'><h5>未知业务</h5><p>" +
//            "<strong id='11qy'>0</strong>人</p></div></div></td></tr>" +
//                    "");
	$(".time-data-table tr:eq(3)").append("<td>"+
                        "<div class='mod-data-text'>"+
                            "<a href='javascript:void(0)' onclick='return removemenu()'><i class='icon-round-more'></i>隐藏</a>"+
                        "</div>"+
                    "</td>");
}
function removemenu(){
	$(".time-data-table tr:eq(3) td:eq(1)").remove();
	$(".time-data-table tr:eq(3)").append("<td>"+
            "<div class='mod-data-text'>"+
                "<a href='javascript:void(0)' onclick='return addmuen()'><i class='icon-round-more'></i>更多</a>"+
            "</div>"+
        "</td>");
	//$(".time-data-table tr:eq(4)").remove();
	//$(".time-data-table tr:eq(:last)").remove(); 
	//$(".time-data-table tr:eq(4)").remove();
}