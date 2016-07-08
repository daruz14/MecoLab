class AuthController < ActionController::Base
  def index
    render 'index', layout: 'application'
  end

  def login
    if params['name'].present?
      session['user'] = params['name']
      redirect_to root_url, notice: 'login success'
    else
      flash.now['alert'] = 'missing name'
      render 'index', layout: 'application'
    end
  end

  def logout
    session.destroy
    redirect_to auth_index_url, notice: 'logout success'
  end
end
