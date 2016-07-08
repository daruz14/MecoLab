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

class Message < ActiveRecord::Base

  validates :author, :message, :timestamp, :send_at, presence: true

  scope :since, ->(timestamp) { where("timestamp > ?", timestamp) }

  def self.some
    order("timestamp asc").limit(10)
  end

end
