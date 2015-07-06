<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- DW6 -->
<head>
<!-- Copyright 2005 Macromedia, Inc. All rights reserved. -->
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>${title}</title>
<link rel="stylesheet" href="${webRoot}/css/alert/emx_nav_left.css" type="text/css" />
<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
<script src="${webRoot}/script/quota/monitor.js"></script>
<script language="javascript" type="text/javascript" src="${webRoot}/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

<!--
var time = 3000;
var numofitems = 7;

//menu constructor
function menu(allitems,thisitem,startstate){ 
  callname= "gl"+thisitem;
  divname="subglobal"+thisitem;  
  this.numberofmenuitems = allitems;
  this.caller = document.getElementById(callname);
  this.thediv = document.getElementById(divname);
  this.thediv.style.visibility = startstate;
}

//menu methods
function ehandler(event,theobj){
  for (var i=1; i<= theobj.numberofmenuitems; i++){
    var shutdiv =eval( "menuitem"+i+".thediv");
    shutdiv.style.visibility="hidden";
  }
  theobj.thediv.style.visibility="visible";
}
				
function closesubnav(event){
  if ((event.clientY <48)||(event.clientY > 107)){
    for (var i=1; i<= numofitems; i++){
      var shutdiv =eval('menuitem'+i+'.thediv');
      shutdiv.style.visibility='hidden';
    }
  }
}
// -->

function refreshPage(){
	location.reload();
}

</script>
<style type="text/css">
<!--
.STYLE1 {font-family: "宋体"}
.searchform ul{list-style:none;} 
.searchform li{float:left;} 

-->
</style>
</head>
<body onmousemove="closesubnav(event);">
<div class="skipLinks">skip to: <a href="#content">page content</a> | <a href="#pageNav">links on this page</a> | <a href="#globalNav">site navigation</a> | <a href="#siteInfo">footer (site information)</a> </div>
<div id="masthead">
  <h1 id="siteName">Red Alert 监控平台</h1>
  <div id="utility"> <a href="#" class="STYLE1">显示用户名</a> </div>
  <div id="globalNav"> <img alt="" src="${webRoot}/picture/alert/gblnav_left.gif" height="32" width="4" id="gnl" /> <img alt="" src="${webRoot}/picture/alert/glbnav_right.gif" height="32" width="4" id="gnr" />
    <div id="globalLink"> <a href="#" id="gl1" class="glink" onmouseover="ehandler(event,menuitem1);">首页</a><a href="#" id="gl2" class="glink" onmouseover="ehandler(event,menuitem2);">主机</a><a href="#" id="gl3" class="glink" onmouseover="ehandler(event,menuitem3);">oracle</a><a href="#" id="gl4" class="glink" onmouseover="ehandler(event,menuitem4);">mysql</a><a href="#" id="gl5" class="glink" onmouseover="ehandler(event,menuitem5);">监控</a><a href="#" id="gl6" class="glink" onmouseover="ehandler(event,menuitem6);">报表</a><a href="#" id="gl7" class="glink" onmouseover="ehandler(event,menuitem7);">配置</a> </div>
    <!--end globalLinks-->
    
  </div>
  <!-- end globalNav -->
  <div id="subglobal1" class="subglobalNav"> <a href="#">subglobal1 link</a> | <a href="#">subglobal1 link</a> | <a href="#">subglobal1 link</a> | <a href="#">subglobal1 link</a> | <a href="#">subglobal1 link</a> | <a href="#">subglobal1 link</a> | <a href="#">subglobal1 link</a> </div>
  <div id="subglobal2" class="subglobalNav"> <a href="#">subglobal2 link</a> | <a href="#">subglobal2 link</a> | <a href="#">subglobal2 link</a> | <a href="#">subglobal2 link</a> | <a href="#">subglobal2 link</a> | <a href="#">subglobal2 link</a> | <a href="#">subglobal2 link</a> </div>
  <div id="subglobal3" class="subglobalNav"> <a href="#">subglobal3 link</a> | <a href="#">subglobal3 link</a> | <a href="#">subglobal3 link</a> | <a href="#">subglobal3 link</a> | <a href="#">subglobal3 link</a> | <a href="#">subglobal3 link</a> | <a href="#">subglobal3 link</a> </div>
  <div id="subglobal4" class="subglobalNav"> <a href="#">subglobal4 link</a> | <a href="#">subglobal4 link</a> | <a href="#">subglobal4 link</a> | <a href="#">subglobal4 link</a> | <a href="#">subglobal4 link</a> | <a href="#">subglobal4 link</a> | <a href="#">subglobal4 link</a> </div>
  <div id="subglobal5" class="subglobalNav"> <a href="#">subglobal5 link</a> | <a href="#">subglobal5 link</a> | <a href="#">subglobal5 link</a> | <a href="#">subglobal5 link</a> | <a href="#">subglobal5 link</a> | <a href="#">subglobal5 link</a> | <a href="#">subglobal5 link</a> </div>
  <div id="subglobal6" class="subglobalNav"> <a href="#">subglobal6 link</a> | <a href="#">subglobal6 link</a> | <a href="#">subglobal6 link</a> | <a href="#">subglobal6 link</a> | <a href="#">subglobal6 link</a> | <a href="#">subglobal6 link</a> | <a href="#">subglobal6 link</a> </div>
  <div id="subglobal7" class="subglobalNav"> <a href="#">subglobal7 link</a> | <a href="#">subglobal7 link</a> | <a href="#">subglobal7 link</a> | <a href="#">subglobal7 link</a> | <a href="#">subglobal7 link</a> | <a href="#">subglobal7 link</a> | <a href="#">subglobal7 link</a> </div>
  <div id="subglobal8" class="subglobalNav"> <a href="#">subglobal8 link</a> | <a href="#">subglobal8 link</a> | <a href="#">subglobal8 link</a> | <a href="#">subglobal8 link</a> | <a href="#">subglobal8 link</a> | <a href="#">subglobal8 link</a> | <a href="#">subglobal8 link</a> </div>
</div>
<!-- end masthead -->
<div class="capsule" id="pagecell1">
