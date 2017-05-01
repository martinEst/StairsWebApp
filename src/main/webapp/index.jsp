<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel='stylesheet' href='webjars/bootstrap/3.3.7-1/css/bootstrap.min.css'>
</head>
<body>
  
    <!-- YOUR CODE HERE -->
    <body>

    <div class="row">
    <div class="col-lg-6">
    
    <div class="input-group">
	  <span class="input-group-addon" id="basic-addon3">Numb. of Flights</span>
   	  <input id="1" type="text" class="form-control" placeholder="ex:{num1,num2,...,numN}" value="">
    </div>
	
	<div class="input-group">
	  <span class="input-group-addon" id="basic-addon3">Steps per Stride </span>
   	   <input id="2" type="text" class="form-control" placeholder="ex: num" value="">
    </div>
    
    <span class="input-group-btn">
        <button title = "calculate	the	minimum	number	of	strides	" class="btn btn-default" type="button"  onclick="loadDoc()">Calculate</button>
      </span>
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->
    
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<p id="demo"></p>
 <script>
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("demo").innerHTML = this.responseText;
    }
  };
  var param1 = document.getElementById('1').value;
  if(param1 === "")
	  {
	 	param1 = "_"; 
	  }
  var param2 = document.getElementById('2').value;
  if(param2 === "")
	  {
	 	param2 = "_"; 
	  }
  xhttp.open("GET", "http://localhost:8080/StairsWebApp/rest/input/"+param1+"/"+param2, true);
  xhttp.send();
}
</script>
</body>
</html>
