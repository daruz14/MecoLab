class Api::V1::UsersController < ApplicationController
    protect_from_forgery with: :null_session
    respond_to :json
    skip_before_filter  :verify_authenticity_token


    # GET /users.json
    def index
      @users = User.all
      render json: @users, status: 200
    end

    # POST /users.json
    def create
      # Ahora al crear un nuevo contacto se revisa si ya existe, en dicho caso se retorna su id correspondiente
      # Este id sera utilizado como token para las conversaciones
      # Si el contacto no existe se crea y se retorna el id correspondiente para luego ser usado
      if User.exists?(user_params)
        return render json: User.where(user_params).first.id
      end
      @user = User.new(user_params)
      if @user.save
        render json: @user.id, status: 201
      else
        render json: { message: 'Falló creacion del contacto', params: user_params }, status: 500
      end
    end

    # PATCH/PUT /users/1.json
    def update
      if @user.update(user_params)
        render json: @user, status: 200
      else
        render json: { message: 'Falló actualización', params: user_params }, status: 500
      end
    end

    # DELETE /users/1.json
    def destroy
      @user.destroy
        render json: { message: 'Contacto eliminado' }, status: 200
    end

    def user_params
      params.permit(:name, :string, :token, :number, :email)
    end

end
