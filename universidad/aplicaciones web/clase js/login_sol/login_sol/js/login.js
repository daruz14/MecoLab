console.log("login.js");
/// ##########################################
document.addEventListener('DOMContentLoaded', function(){
/// ##########################################
  console.log("load");
  console.log(document.getElementsByTagName('form')[0]);
  document.getElementsByTagName('form')[0].addEventListener('submit',function(ev){
    console.log("capturando el submit");
    var valid = true;
    valid = valid && document.getElementById('user').value;
    if (!valid) {
      var err = document.createElement('div');
      err.className = 'error';
      err.innerHTML = "Falta el usuario";
      document.getElementById('user').parentNode.appendChild(err);

/// ##########################################
      ev.preventDefault();
/// ##########################################
    }
  });

});

