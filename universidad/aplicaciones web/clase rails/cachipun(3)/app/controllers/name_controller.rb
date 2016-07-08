class NameController < ApplicationController
  
  def ask
    # si ya esta logueado, deberiamos hacer un redirect ...
  end

  def remember
    
    name = params[:username]
    password = params[:pass]
    
    
    # ###########
    # Esto no debería estar aqui:
    # la "logica de negocios" (de login)
    # pertenece al modelo
    # ###########
    
    user = User.where(username: name).first
    if name.blank? 
      @error = "Debes ingresar un usuario"
    elsif user.nil? 
      @error = "Usuario desconocido"
    elsif password != user.password
      @error = "La clave esta mal"
    end
    
    # ###########
    # Fin de cosas que no van aqui
    # ###########
    
    if @error
      # ups, hay un error. Haremos render de
      # la accion que pide login: ask
      render 'ask'
    else
      # todo bien.
      # guardamos en sesion
      session[:user_name] = name
      
      # y redirigimos al inicio del juego
      redirect_to game_start_path
    end
  end
end
