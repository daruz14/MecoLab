$(function(){

  var $cardLinks = $('.cards a');


  var showCardInfo = function(cardInfo) {
    // primero voy a eliminar si es que hay alguno
    $('.cards').parent().find('.card-info').remove();

    $div = $("<div class='card-info'>" +
            "<h1>"+ cardInfo.name +"</h1>" +
            "<p>"+ cardInfo.desc +"</p>" +
            "<a href='"+ cardInfo.url + "'>Ir a la carta</a>" +
        "</div>");
    //
    //     $('<div></div>');
    // $div.addClass('card-info');
    // $div.html("<h1>" + cardInfo.name + "</h1>");
    // $div.append("<p>"+ cardInfo.desc + "</p>");
    // $div.append("<a href='"+ cardInfo.url +"'>Ir a la carta</a>");

    $('.cards').before($div);
  }


  $cardLinks.on('click',function(ev){

    ev.preventDefault();

    var $link = $(this);

    var sourceUrl = $link.data('cardSourceUrl');

    $.ajax({
      "url": sourceUrl,
      "success": function(data, ts, jq) {
        console.log("success: ", data)
        showCardInfo(data);
      },
      "error": function(jq, st, er) {
        console.log("ERROR :(", jq, st, er);
      }
    });

  });


});