module ApplicationHelper

  def current_user
    @current_user
  end
  def current_user?
    current_user.present?
  end
end
