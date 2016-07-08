# == Schema Information
#
# Table name: messages
#
#  id         :integer          not null, primary key
#  author     :string           not null
#  message    :string           not null
#  timestamp  :integer          not null
#  send_at    :datetime         not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#

require 'test_helper'

class MessageTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end
end
