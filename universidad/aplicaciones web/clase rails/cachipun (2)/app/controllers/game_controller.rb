class GameController < ApplicationController
  def start
    @name = params[:user_name]
  end

  def result
    @name = params[:user_name]
    @user_result = params[:user_action].to_i rescue nil
    puts @user_result.inspect
    @cpu_result = rand(1..3)
    @game_result = Cachipun::Game.new.resolv(@user_result,@cpu_result)
  end
end
