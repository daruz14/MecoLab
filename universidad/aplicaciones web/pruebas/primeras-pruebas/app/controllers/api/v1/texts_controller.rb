class Api::V1::TextsController < ApplicationController
  protect_from_forgery with: :null_session
  respond_to :json
  skip_before_filter  :verify_authenticity_token
  require 'fcm'

  # GET /texts
  # GET /texts.json
  # GET /users.json
  def index
    @texts = Text.all
    render json: @texts, status: 200
  end



  # POST /texts
  # POST /texts.json
  def create
    @text = Text.new(text_params)
    if @text.save

      notify_android(text_params[:token],text_params[:text],text_params[:from], text_params[:to])
      render json: @text, status: 201
    else
      render json: { message: 'Falló creacion', message: text, params: text_params }, status: 500
    end
  end



    # Never trust parameters from the scary internet, only allow the white list through.
  def text_params
    params.permit(:to, :from, :text, :token)
  end

  def notify_android(recipient,sms,sender,receptor)

    fcm = FCM.new("AIzaSyA7nbUR_GyRUzWcoFytMLKPU3WGuWuDS_I")
    registration_ids= [recipient]
    options = {data: {"message": sms, "sender": sender, "receptor": receptor, "token": recipient, "date": "2016/09/22"}}
    response = fcm.send(registration_ids, options)

  end
end
