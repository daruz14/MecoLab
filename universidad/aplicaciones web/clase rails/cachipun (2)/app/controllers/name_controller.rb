class NameController < ApplicationController
  def ask
  end

  def remember
    
    name = params[:user_name]
    
    if name.blank?
      @error = "Debes ingresar un nombre"
      render 'ask'
    else
      redirect_to game_start_path(user_name: name)
    end
  end
end
