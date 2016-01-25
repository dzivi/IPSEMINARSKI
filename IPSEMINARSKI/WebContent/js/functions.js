$(document).ready(function(){
	
	//open the message
  $(".openMess").click(function(){
	  var globaCount="";
	   globaCount= $("body").find(".headersmallbox .countMess").text();
	  if(globaCount!=""){
		  globaCount=globaCount.substring(1,(globaCount.length-1));
		  globaCount--;
		  if(globaCount==0){
			  $("body").find(".countMess").text("");
		  }else{
			  $("body").find(".countMess").text("("+ globaCount +")");
		  }
		 
	  }
	  
	  var notti= $(this).prev(".notiff").text();
	  if(notti=="(1)"){
		  var nottiID=$(this).parent("div").next("div").find(".nottiID").val();
		  var messageID=$(this).parent("div").next("div").find(".messageID").val();
	   	  var userID=$(this).parent("div").next("div").find(".userID").val();
	   	 $(this).prev(".notiff").text("");
			   $.post( "MessagesServlet", { 
				   nottiID: nottiID,
				   messageID:messageID,
				   userID:userID,
				   option:'updatenott'
				   })
			   .success(function( data ) {
			     if(data=="ok"){
			    	 $(this).prev(".notiff").text("");
			     }else{
			    	 alert("An error has occured");
			     }
			   });
	    }
  
    $(this).parent("div").next(".messagesdetails").slideToggle();
    if($(this).val()=='Open'){
    	$(this).val('Close');
    }else{
    	$(this).val('Open');
    }
  });
  
  	//replay the message
  $(".replay").click(function(){
	  
	   var textToReplay= $(this).parent("div").find(".replaytext").val();
	   var messageID=$(this).parent("div").find(".messageID").val();
	   var userID=$(this).parent("div").find(".userID").val();
	   var oldMessage=$(this).parent("div").find(".oldMessage").val();
	   var nottiID=$(this).parent("div").find(".nottiID").val();
	   var myname=$(this).parent("div").find(".myname").val();
	   if(textToReplay!=""){
		   textToReplay=oldMessage+">> "+myname+": "+textToReplay+"\n";

		   $.post( "MessagesServlet", { 
			   textToReplay: textToReplay,
			   messageID: messageID,
			   userID:userID,
			   nottiID:nottiID,
			   option:'update'
			   })
		   .success(function( data ) {
		     if(data=="ok"){
		    	 location.reload();
		     }else{
		    	 alert("An error has occured");
		     }
		   });
	   }else{
		   alert("Please insert text...");
	   }
	  });

  
//FriendRequest
  $(".FriendRequest").click(function(){
  	  var friendId=$(this).parent().find(".FriendRequestID").val();
  	  var myID=$(this).parent().find(".myID").val();
  	  var press= $(this).val();
  	  var ActionToDo="";
  	  if(press=="Accept"){
  		  ActionToDo="Add";
  	  }else{
  		  ActionToDo="Del";
  	  }
  	   $.post( "RequestAccDel", { 
  		   ActionToDo: ActionToDo,
  		   friendId: friendId,
  		   myID:myID
  		   })
  	   .success(function( data ) {
  	     if(data=="ok"){
  	    	 location.reload();
  	     }else{
  	    	 alert("An error has occured!!!");
  	     }
  	   });
  	
  });

  //addPostText

  $(".addInput").click(function(){
  	  var control=$(this).val();

  	  if(control=="Open"){
  		 $("#addPostText").slideToggle();
  	  }else{
  		var postText=$("#postText").val();
  		var postLink=$("#postLink").val();
  		var postPicture=$("#postPicture").val();
  		  if(postText.length>0 || postLink.length>0 || postPicture.length>0){
  			alert("You ok....");
  		  }else{
  			  alert("You must enter at least text ....");
  		  }
  	  }
  });
  
 

  //addPostText
  $(".like").click(function(){
  	  var postID=$(this).parents().eq(2).find(".postID").val();
  	  var userID=$("#userID").val();
  	  
  	$.post( "Like", { 
  		postID: postID,
  		userID: userID
		   })
	   .success(function( data ) {
	     if(data=="ok"){
	    	 location.reload();
	     }else{
	    	 alert("You already like this post!!!");
	     }
	   });
  });
  
});

