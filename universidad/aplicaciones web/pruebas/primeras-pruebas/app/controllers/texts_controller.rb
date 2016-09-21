class TextsController < ApplicationController
  before_action :set_text, only: [:show, :edit, :update, :destroy]
  require 'fcm'
  # GET /texts
  # GET /texts.json
  def index
    @texts = Text.all
  end

  # GET /texts/1
  # GET /texts/1.json
  def show
  end

  # GET /texts/new
  def new
    @text = Text.new
  end

  # GET /texts/1/edit
  def edit
  end

  # POST /texts
  # POST /texts.json
  def create
    @text = Text.new(text_params)
    notify_android(text_params[:token])
    respond_to do |format|
      if @text.save
        format.html { redirect_to @text, notice: 'Text was successfully created.' }
        format.json { render :show, status: :created, location: @text }
      else
        format.html { render :new }
        format.json { render json: @text.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /texts/1
  # PATCH/PUT /texts/1.json
  def update
    respond_to do |format|
      if @text.update(text_params)
        format.html { redirect_to @text, notice: 'Text was successfully updated.' }
        format.json { render :show, status: :ok, location: @text }
      else
        format.html { render :edit }
        format.json { render json: @text.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /texts/1
  # DELETE /texts/1.json
  def destroy
    @text.destroy
    respond_to do |format|
      format.html { redirect_to texts_url, notice: 'Text was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  def notify_android(recipient)

    fcm = FCM.new("AIzaSyA7nbUR_GyRUzWcoFytMLKPU3WGuWuDS_I")
    registration_ids= ["f-g78D25o9M:APA91bGQ4CRMXV_g-HAwwqYPzCq4Fdk3R0lNjAGS1lZgzSEdR81xaJt-EXKXz1dxw-bFtnShg6ntZ0SvbsVpiE8PjYDW0uCtPWuAYDG9t37PUp8ToWuAoauTEr7i1fT9EJWCQ0RhhTrt"]
    options = {data: {"message": "This is a Firebase Cloud Messaging Device Message!"}}
    response = fcm.send(registration_ids, options)
    case response[:status]
      when 200
        render json: {response: response[:body], status_code: response[:status]}, statu: :ok
      else
        render json: {response: response[:body], status_code: response[:status], headers: response[:headers]}, statu: :un_processible_entity
      end # when
  end



  private
    # Use callbacks to share common setup or constraints between actions.
    def set_text
      @text = Text.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def text_params
      params.require(:text).permit(:to, :from, :text, :token)
    end
end
