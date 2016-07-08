class Api::V1::ContactosController < ApplicationController
    protect_from_forgery with: :null_session
    respond_to :json
    skip_before_filter  :verify_authenticity_token


    # GET /users.json
    def index
      @contactos = Contacto.all
      render json: @contactos, status: 200
    end

    # POST /users.json
    def create
      @contacto = Contacto.new(contact_params)
      if @contacto.save
        render json: @contacto, status: 201
      else
        render json: { message: 'Falló creacion', product: product, params: product_params }, status: 500
      end
    end

    # PATCH/PUT /users/1.json
    def update
      if @contacto.update(contact_params)
        render json: @contacto, status: 200
      else
        render json: { message: 'Falló actualización', params: contact_params }, status: 500
      end
    end

    # DELETE /users/1.json
    def destroy
      @contacto.destroy
        render json: { message: 'Contacto eliminado' }, status: 200
    end

    def contact_params
      params.require(:contacto).permit(:Nombre, :Apellido, :Telefono)
    end

end
