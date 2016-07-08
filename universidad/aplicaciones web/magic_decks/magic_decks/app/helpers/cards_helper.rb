module CardsHelper
  def mana_cost_to_images(text)
    # seria ideal si podemos mostrar los iconos
    # en lugar de las marcas feas {3}{U}
    #
    # traduccion:
    #
    #  {W} es mana white
    #  {U} es mana blue
    #  {B} es mana black
    #  {R} es mana red
    #  {G} es mana green
    #  {C} es mana colorless
    #  
    #  en los numeros 0 - 9 se debe usar la imagen correspondiente
    #  por ejemplo, para {3} se debe usar la imagen mana_3.png
    
    # por ahora, no hacemos nada
    
    # OJO: ahora se cae si no tiene costo de mana
    
    text
  end
end
