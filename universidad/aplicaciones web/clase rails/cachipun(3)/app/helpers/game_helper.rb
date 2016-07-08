module GameHelper
  def cachipun_print(value)
    case value
    when 1
      "piedra"
    when 2
      "papel"
    else
      "tijera"
    end
  end
  
  def cachipun_print_result(result, player1, player2)
    if result < 0
      "#{player1} gana!"
    elsif result > 0
      "#{player2} gana!"
    else
      "un empate :("
    end
  end
end
