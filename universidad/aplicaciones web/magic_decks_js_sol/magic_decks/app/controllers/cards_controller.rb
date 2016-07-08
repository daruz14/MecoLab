class CardsController < ApplicationController
  before_action :set_card, only: [:show]

  # GET /cards
  # GET /cards.json
  def index
    @cards = MtgCard.order_by_color
  end

  # GET /cards/1
  # GET /cards/1.json
  def show
    respond_to do |format|
      format.html {}
      format.json do

        hash_card = {
          name: @card.name,
          desc: @card.oracle_text,
          url: card_path(@card)
        }

        render json: hash_card
      end
    end
  end


  private
    # Use callbacks to share common setup or constraints between actions.
    def set_card
      @card = MtgCard.find(params[:id])
    end


end
