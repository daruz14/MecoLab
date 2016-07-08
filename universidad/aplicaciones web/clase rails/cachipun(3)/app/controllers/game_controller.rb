class GameController < ApplicationController
  
  def start
  end

  def result
    @user_result = params[:user_action].to_i rescue nil
    puts @user_result.inspect
    @cpu_result = rand(1..3)
    @game_result = Cachipun::Game.new.resolv(@user_result,@cpu_result)
  end
  
end
