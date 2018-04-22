		


		var query = window.location.search;
		alert(query);
		
		var str = query.substring(1);
		var imgsrc="static/img/s"+str+".jpg";
		var mscsrc="static/msc/s"+str+".mp3";

		document.getElementById("img").src=imgsrc;
		document.getElementById("msc").src=mscsrc;
  		document.getElementById("lyrics").innerHTML="<blockquote>lyrics and information</blockquote>";
  		document.getElementById("backgroundstory").innerHTML="<blockquote>background story</blockquote>";
  		
/*
  		document.getElementById("lyrics").innerHTML="<blockquote>lyrics and information</blockquote>";
  		document.getElementById("backgroundstory").innerHTML="<blockquote>background story</blockquote>";	
*/
  	
  		
/*  		
  		
  		var fso;
  		try { 
  		fso=new ActiveXObject("Scripting.FileSystemObject"); 

  		} catch (e) { 
  		alert("当前浏览器不支持");
  		return;
  		} 

  		alert("方法已执行2");
  		var openf1 = fso.OpenTextFile("/static/lyc/ylq.txt",1,true);
  		str = openf1.ReadLine();
  		alert("里面的内容为'" + str + "'");
*/
  		