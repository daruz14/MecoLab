module Cachipun
  class Game
    
    ##
    # Determina quien gana en cachipun
    # play1 y play2 deben ser numeros
    # entre 1 y 3:
    # - 1: piedra
    # - 2: papel
    # - 3: tijera
    # retorna -1 si gana el jugador 1, 
    # 0 si hay empate
    # 1 si gana el jugador 2
    def resolv(play1,play2)
      case play1
      when 1 # piedra
        case play2
        when 1 then 0 # piedra vs piedra = empate
        when 2 then 1 # piedra vs papel  = gana 2
        else -1       # piedra vs tijera = gana 1
        end
      when 2 # papel
        case play2
        when 1 then -1 # papel vs piedra = gana 1
        when 2 then 0  # papel vs papel  = empate
        else 1         # papel vs tijera = gana 2
        end
      else # 3 = tijera
        case play2
        when 1 then 1  # tijera vs piedra = gana 2
        when 2 then -1 # tijera vs papel  = gana 1
        else 0         # tijera vs tijera = empate
        end
      end
    end
  end
end