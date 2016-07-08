class CardsController < ApplicationController
  before_action :set_card, only: [:show]

  # GET /cards
  # GET /cards.json
  def index
    @cards = MtgCard.order_by_color
    filter_cards_by_rarity
  end

  # GET /cards/1
  # GET /cards/1.json
  def show
  end


  private
    # Use callbacks to share common setup or constraints between actions.
    def set_card
      @card = MtgCard.find(params[:id])
    end
    
    def filter_cards_by_rarity
      # la rareza viene en el params["r"].
      # pero necesitamos "traducirlo"
      # a como se guarda en base de datos:
      #
      # Comunes: Common
      # Infrecuentes: Uncommon
      # Raras: Rare
      # Míticas: Mythic Rare
      #
      # luego, si a @cards le agregamos la condicion de "rarity", estariamos
    end

end
