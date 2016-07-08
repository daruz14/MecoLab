class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  protect_from_forgery with: :exception

  before_filter :require_auth

  attr_reader :current_user

  def require_auth
    set_current_user
    if current_user.nil?
      redirect_to auth_index_url, alert: 'login required'
    end
  end

  private

  def set_current_user
    if session.has_key?('user')
      user = session['user']
      if user.present?
        @current_user = session['user']
      end
    end
  end

end
