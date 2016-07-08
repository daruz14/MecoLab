var Rectangle = function(x_element){

  // Variables publicas
  this.onElement = x_element;
  this.width = 100;
  this.height = 100;
  this.color = '#ffcc00';

  // Variables privadas
  var element = null;

  // Esto sirve para tener acceso a este objeto
  // desde los metodos privados, por un problema con el contexto
  // Explicacion: http://stackoverflow.com/a/9674288/2808440
  var that = this;

  // Metodos privados:
  // - Pueden acceder a metodos y propiedades privados y publicos
  // - No existen publicamente
  var setStyle = function(){
    element.style.width = that.width + "px";
    element.style.height = that.height + "px";
    element.style.background = that.color;
    //element.style.cssText = "width: " + that.width + "px; height: " + that.height + "px; background: " + that.color + ";"
  }

  // Metodos "privilegiados":
  // - son de acceso publico
  // - pueden acceder a metodos y propiedades privados y publicos
  this.draw = function() {
    element = document.createElement('div');
    setStyle();
    this.onElement.appendChild(element);
  };

  this.repaint = function() {
    setStyle();
  };

};

// Metodos publicos:
// - son publicos
// - pueden acceder solo a metodos y propiedades publicos (y privilegiados)
Rectangle.prototype.resizeTo = function(w, h) {
  this.width = w;
  this.height = h;
  this.repaint();
};

// Referencia util: http://javascript.crockford.com/private.html
