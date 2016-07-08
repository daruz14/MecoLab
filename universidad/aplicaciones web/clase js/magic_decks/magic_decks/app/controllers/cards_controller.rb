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
      format.json { render json: {id: @card.id, name: @card.name} }
    end
  end


  private
    # Use callbacks to share common setup or constraints between actions.
    def set_card
      @card = MtgCard.find(params[:id])
    end


end
