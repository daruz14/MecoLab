require 'test_helper'

class NameControllerTest < ActionController::TestCase
  test "should get ask" do
    get :ask
    assert_response :success
  end

  test "should get remember" do
    get :remember
    assert_response :success
  end

end
