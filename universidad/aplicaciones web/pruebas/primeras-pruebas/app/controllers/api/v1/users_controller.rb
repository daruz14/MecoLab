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
      #When intent create a new user, first we will review if it exist
      #If the user is created or exists , then it will return its ID
      # the id will be used for edit the user in case update de token
      if User.exists?(user_params)
        #Here is revised if there
        return render json: User.where(user_params).first.id
      end
      @user = User.new(user_params)
      if @user.save
        #If the user was created , then the id is returned
        #and delivered 201 status success
        render json: @user.id, status: 201
      else
        #If I were creating failure , an error message and
        #status 500, where there was a server error is returned
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
