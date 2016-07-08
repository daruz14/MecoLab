class NameController < ApplicationController
  def ask
  end

  def remember
    render text: "Su nombre es #{params[:name]}"
  end
end
