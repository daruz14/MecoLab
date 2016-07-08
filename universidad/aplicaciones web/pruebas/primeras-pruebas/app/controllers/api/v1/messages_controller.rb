class Api::V1::MessagesController < ApplicationController
  protect_from_forgery with: :null_session
  respond_to :json
  skip_before_filter  :verify_authenticity_token


  # GET /users.json
  def index
    @messages = Message.all
    render json: @messages, status: 200
  end

  # POST /users.json
  def create
    @message = Message.new(message_params)
    if @message.save
      render json: @message, status: 201
    else
      render json: { message: 'Falló creacion', message: message, params: message_params }, status: 500
    end
  end

  # PATCH/PUT /users/1.json
  def update
    if @message.update(message_params)
      render json: @message, status: 200
    else
      render json: { message: 'Falló actualización', params: message_params }, status: 500
    end
  end

  # DELETE /users/1.json
  def destroy
    @message.destroy
      render json: { message: 'Contacto eliminado' }, status: 200
  end

  def message_params
    params.require(:message).permit(:text, :to, :from)
  end

end
