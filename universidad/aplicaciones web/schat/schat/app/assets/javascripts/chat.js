$(function(){

  var $btnmesssages = $('.btn_message');
  var $messsage = $('.message');
  var $chatcontainer = $('.chat-container');

  var Enviar = function(cardInfo) {
    // primero voy a eliminar si es que hay alguno
    //$('.cards').parent().find('.card-info').remove();

    //$div = $("<div class='card-info'>" +
    //        "<h1>"+ cardInfo.name +"</h1>" +
    //        "<p>"+ cardInfo.desc +"</p>" +
    //        "<a href='"+ cardInfo.url + "'>Ir a la carta</a>" +
    //    "</div>");
    //
    //     $('<div></div>');
    // $div.addClass('card-info');
    // $div.html("<h1>" + cardInfo.name + "</h1>");
    // $div.append("<p>"+ cardInfo.desc + "</p>");
    // $div.append("<a href='"+ cardInfo.url +"'>Ir a la carta</a>");

    //$('.cards').before($div);
  }


  $btnmesssages.click(function(ev){


    ev.preventDefault();
    console.log("funciona");

    var $link = $(this);

    var textmsg = $('.message').data();
    var text = $('.message').val();

    console.log(text);

    $.ajax({
      "text": textmsg,
      "success": function(data, ts, jq) {
        console.log("success: ", data)
        //showCardInfo(data);
      },
      "error": function(jq, st, er) {
        console.log("ERROR :(", jq, st, er);
      }
    });

  });


});
